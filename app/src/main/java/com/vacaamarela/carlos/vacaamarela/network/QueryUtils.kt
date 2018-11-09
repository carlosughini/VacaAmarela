package com.vacaamarela.carlos.vacaamarela.network

import android.location.Location
import android.text.TextUtils
import android.util.Log
import com.vacaamarela.carlos.vacaamarela.model.Butchery
import org.json.JSONException
import org.json.JSONObject
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStream
import java.io.InputStreamReader
import java.math.RoundingMode
import java.net.HttpURLConnection
import java.net.MalformedURLException
import java.net.URL
import java.nio.charset.Charset
import java.text.DecimalFormat
import kotlin.math.round

object QueryUtils {

    /** Tag para mensagens de log  */
    private val TAG = QueryUtils::class.java.name
    private lateinit var mUserLatitude: String
    private lateinit var mUserLongitude: String
    // Create a list of butchers
    private var butchersList = mutableListOf<Butchery>()

    fun extractButchers(stringUrl: String, latitude: String, longitude: String) : MutableList<Butchery>? {

        val url: URL? = createUrl(stringUrl)

        mUserLatitude = latitude
        mUserLongitude = longitude

        // Perform HTTP request to the URL and receive a JSON response back
        var jsonResponse = ""
        try {
            jsonResponse = makeHttpRequest(url)
        } catch (e: IOException) {
            // TODO Handle the IOException
        }

        return extractFeatureFromJson(jsonResponse)
    }

    /**
     * Returns new URL object from the given string URL.
     */
    private fun createUrl(stringUrl: String): URL? {
        val url: URL?
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
            Log.v(TAG, "Response code: $responseCode")
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
    private fun extractFeatureFromJson(responseJSON: String): MutableList<Butchery>? {

        // If the JSON string is empty or null, then return early.
        if (TextUtils.isEmpty(responseJSON)) {
            return null
        }

        try {
            val baseJsonResponse = JSONObject(responseJSON)

            // Get the sheetArray from the jsonResponse
            val sheetArray = baseJsonResponse.getJSONArray("Sheet1")

            // If there are resuls in the sheet array
            if (sheetArray.length() > 0) {
                for (i in 0 until sheetArray.length()) {
                    // Extract out the jsonObjects in each position
                    val butchers = sheetArray.getJSONObject(i)
                    // Extract out the butchery name
                    val butcheryName = butchers.getString("nome")
                    // Extract out the butchery address
                    val butcheryAddress = butchers.getString("endereco")
                    // Extract out the butchery city
                    val butcheryCity = butchers.getString("cidade")
                    // Extract out the butchery latitude
                    val butcheryLatitude = butchers.getString("lat")
                    // Extract out the butchery longitude
                    val butcheryLongitude = butchers.getString("long")
                    // Extract out the butchery elevation
                    val butcheryElevation = butchers.getString("elev")
                    // Extract out the butchery phone number
                    val butcheryPhoneNumber = butchers.getString("telefone")

                    val resultsDistanceBetweenUserAndButchery: FloatArray = FloatArray(3)
                    /**
                     * Calculate distance between two points in latitude and longitude.
                     *
                     * @return Distance in meters
                     */
                    Location.distanceBetween(
                            mUserLatitude.toDouble(),
                            mUserLongitude.toDouble(),
                            butcheryLatitude.toDouble(),
                            butcheryLongitude.toDouble(),
                            resultsDistanceBetweenUserAndButchery
                    )
                    // Instantiate a variable to store the distance in meters
                    val resultDistance = resultsDistanceBetweenUserAndButchery[0]
                    // Check if the distance is more than 10km to do not show to the user
                    if (resultDistance < 10000.00) {
                        val distanceInKm = formatDistance(resultsDistanceBetweenUserAndButchery[0])
                        /**
                         * Create a new {@link Butchery} object
                         */
                        butchersList.add(Butchery(butcheryName,
                                butcheryAddress,
                                butcheryCity,
                                butcheryLatitude.toDouble(),
                                butcheryLongitude.toDouble(),
                                butcheryElevation.toDouble(),
                                butcheryPhoneNumber,
                                distanceInKm))

                    }
                }
            }
        } catch (e: JSONException) {
            Log.e("HttpRequest", "Problem parsing the earthquake JSON results", e)
        }

        // Sort the list to show the nearest butchery
        butchersList.sortBy { it.distanceFromUser }

        // Return the array list with butchers objects
        return butchersList
    }

    /**
     * Receive the distance in meters and format it
     * to show in km.
     *
     * @param distanceDouble - Distance in meters
     */
    fun formatDistance(distanceDouble: Float) : String {
        val distanceInMeters = round(distanceDouble)
        val decimalFormat = DecimalFormat("#.##")
        decimalFormat.roundingMode = RoundingMode.CEILING
        return decimalFormat.format(distanceInMeters / 1000).toString().replace(",",".")
    }
}