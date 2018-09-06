package com.vacaamarela.carlos.vacaamarela

import android.Manifest
import android.annotation.SuppressLint
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.os.AsyncTask
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.ActivityCompat
import android.support.v4.content.ContextCompat
import android.text.TextUtils
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ListView
import com.github.lzyzsd.circleprogress.DonutProgress
import org.json.JSONException
import org.json.JSONObject
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStream
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.MalformedURLException
import java.net.URL
import java.nio.charset.Charset

class MainActivity : AppCompatActivity() {

    /** Tag for the log messages  */
    val TAG = MainActivity::class.java.simpleName

    // Instantiate progress bar
    var progressBar: DonutProgress? = null

    /** URL for the butchers data from the google spreadsheet */
    private val SPREADSHEET_URL: String = "https://script.google.com/macros/s/AKfycbxOLElujQcy1-ZUer1KgEvK16gkTLUqYftApjNCM_IRTL3HSuDk/exec?id=1yr5mwaIbA9puTIUfMnrfoKeovZ3rpuCpqIIqOsSZtEs"


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Get the progressbar view
        progressBar = findViewById(R.id.progressBar)

        // Get the actionBar
        val actionBar = supportActionBar
        // Change actionBar title
        actionBar!!.title = "Açougues"

        /**
         * Create a {@link AsyncTask} to perform the HTTP request to the given URL
         * on a background thread. When the result is received on the main UI thread,
         * then update the UI.
         * Get the butchers data from google spreadsheet.
         */
        val asyncTask: ButcheryAsyncTask = ButcheryAsyncTask()
        asyncTask.execute(SPREADSHEET_URL)

        // Get the user location in latitude and longitude
        getLocation()
    }

    /**
     * Update the screen to display information from the given [Event].
     */
    fun updateUi(butchers: ArrayList<Butchery>) {
        // Find the ListView object in the view hierarchy of the Activity.
        val listView: ListView = findViewById(R.id.listView)

        /**
         * Create an ButcheryAdapter, whose data source is a list of Butcherys.
         * The adapter knows how to create list items for each item in the list.
         */
        val butcheryAdapter = EstabelecimentoAdapter(this, butchers)

        /**
         * Make the listView use the butcheryAdapter created above, so that the
         * listView will display list items for each Butchery in the list
         */
        listView.adapter = butcheryAdapter

        listView.onItemClickListener = object : AdapterView.OnItemClickListener {
            override fun onItemClick(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                var butcheryDetailActivity = Intent(applicationContext,ButcheryDetailActivity::class.java)
                startActivity(butcheryDetailActivity)
            }
        }
    }


    /**
     * [AsyncTask] to perform the network request on a background thread, and then
     * update the UI with the first earthquake in the response.
     */
    @SuppressLint("StaticFieldLeak")
    private inner class ButcheryAsyncTask : AsyncTask<String, Int, ArrayList<Butchery>>() {

        override fun onPreExecute() {
            super.onPreExecute()
            progressBar?.visibility = View.VISIBLE
        }

        override fun doInBackground(vararg urls: String): ArrayList<Butchery>? {

            // Do not execute the task if the URL is null
            if (urls[0].isEmpty()) {
                return null
            }

            publishProgress(10)
            // Create URL object
            val url = createUrl(urls[0])

            // Perform HTTP request to the URL and receive a JSON response back
            var jsonResponse = ""
            try {
                publishProgress(40)
                jsonResponse = makeHttpRequest(url)
                publishProgress(70)
            } catch (e: IOException) {
                // TODO Handle the IOException
            }

            // Extract relevant fields from the JSON response and create an {@link Event} object
            publishProgress(100)

            // Return the {@link Event} object as the result fo the {@link TsunamiAsyncTask}
            return extractFeatureFromJson(jsonResponse)
        }

        /**
         * Update the screen with the given earthquake (which was the result of the
         * [TsunamiAsyncTask]).
         */
        override fun onPostExecute(butchers: ArrayList<Butchery>?) {
            // Set the progressbar invisible
            progressBar?.visibility = View.INVISIBLE

            if (butchers == null) {
                return
            }

            // Update the listview
            updateUi(butchers)
        }

        /**
         * Returns new URL object from the given string URL.
         */
        private fun createUrl(stringUrl: String): URL? {
            var url: URL?
            try {
                url = URL(stringUrl)
            } catch (exception: MalformedURLException) {
                Log.e("HttpRequest", "Error with creating URL", exception)
                return null
            }

            return url
        }

        /**
         * Make an HTTP request to the given URL and return a String as the response.
         */
        @Throws(IOException::class)
        private fun makeHttpRequest(url: URL?): String {
            var jsonResponse = ""

            // If the URL is null, then return early.
            if (url == null) {
                return jsonResponse
            }
            var urlConnection: HttpURLConnection? = null
            var inputStream: InputStream? = null
            try {
                urlConnection = url.openConnection() as HttpURLConnection
                urlConnection.requestMethod = "GET"
                urlConnection.readTimeout = 10000
                urlConnection.connectTimeout = 15000
                urlConnection.connect()

                // If the request was successful (response code 200),
                // then read the inpu stream and parse the response.
                val responseCode = urlConnection.responseCode
                Log.v("MainActivity", "Response code: $responseCode")
                if (responseCode == 200) {
                    inputStream = urlConnection.inputStream
                    jsonResponse = readFromStream(inputStream)
                }
            } catch (e: IOException) {
                // TODO: Handle the exception
            } finally {
                urlConnection?.disconnect()
                inputStream?.close()
            }
            return jsonResponse
        }

        /**
         * Convert the [InputStream] into a String which contains the
         * whole JSON response from the server.
         */
        @Throws(IOException::class)
        private fun readFromStream(inputStream: InputStream?): String {
            val output = StringBuilder()
            if (inputStream != null) {
                val inputStreamReader = InputStreamReader(inputStream, Charset.forName("UTF-8"))
                val reader = BufferedReader(inputStreamReader)
                var line: String? = reader.readLine()
                while (line != null) {
                    output.append(line)
                    line = reader.readLine()
                }
            }
            return output.toString()
        }

        /**
         * Return an [Event] object by parsing out information
         * about the first earthquake from the input earthquakeJSON string.
         */
        private fun extractFeatureFromJson(responseJSON: String): ArrayList<Butchery>? {
            // If the JSON string is empty or null, then return early.
            if (TextUtils.isEmpty(responseJSON)) {
                return null
            }

            // Create a list of butchers
            val butchersList = ArrayList<Butchery>()

            try {
                val baseJsonResponse = JSONObject(responseJSON)

                // Get the sheetArray from the jsonResponse
                val sheetArray = baseJsonResponse.getJSONArray("Sheet1")

                // If there are resuls in the sheet array
                if (sheetArray.length() > 0) {
                    for (i in 0 until sheetArray.length()) {
                        // Extract out the jsonObjects in each position
                        val butchers = sheetArray.getJSONObject(i)
                        // Extract out the name, address and city of the butchers
                        val butcheryName = butchers.getString("Nome")
                        val butcheryAddress = butchers.getString("Endereco")
                        val butcheryCity = butchers.getString("Cidade")
                        /**
                         * Create a new {@link Butchery} object
                         */
                        butchersList.add(Butchery(butcheryName, butcheryAddress, butcheryCity))
                    }
                }
            } catch (e: JSONException) {
                Log.e("HttpRequest", "Problem parsing the earthquake JSON results", e)
            }

            // Return the array list with butchers objects
            return butchersList
        }
    }

    fun getLocation() {

        val locationManager = getSystemService(LOCATION_SERVICE) as LocationManager?

        val locationListener = object : LocationListener {
            override fun onStatusChanged(provider: String?, status: Int, extras: Bundle?) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onProviderEnabled(provider: String?) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onProviderDisabled(provider: String?) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onLocationChanged(location: Location?) {
                val latitute = location!!.latitude
                val longitute = location.longitude

                Log.v(TAG, "Latitute: $latitute ; Longitute: $longitute")

            }
        }

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(
                    this,
                    arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
                    PERMISSION_REQUEST_ACCESS_FINE_LOCATION)
            return
        }
        locationManager!!.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0L, 0f, locationListener)
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == PERMISSION_REQUEST_ACCESS_FINE_LOCATION) {
            when (grantResults[0]) {
                PackageManager.PERMISSION_GRANTED -> getLocation()
                PackageManager.PERMISSION_DENIED -> Log.v(TAG,"Negada")//Tell to user the need of grant permission
            }
        }
    }

    companion object {
        private const val PERMISSION_REQUEST_ACCESS_FINE_LOCATION = 100
    }
}
