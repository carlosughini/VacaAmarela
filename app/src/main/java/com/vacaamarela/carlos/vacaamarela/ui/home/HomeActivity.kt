package com.vacaamarela.carlos.vacaamarela.ui.home

import android.Manifest
import android.annotation.SuppressLint
import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.os.Build
import android.os.Bundle
import android.provider.Settings
import android.support.v4.app.Fragment
import android.support.v4.view.ViewPager
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.widget.Toast
import com.vacaamarela.carlos.vacaamarela.R
import com.vacaamarela.carlos.vacaamarela.ui.home.adapter.HomePagerAdapter
import com.vacaamarela.carlos.vacaamarela.ui.view.ButchersFragment
import com.vacaamarela.carlos.vacaamarela.ui.view.CuponsFragment
import kotlinx.android.synthetic.main.activity_home.*

class HomeActivity : AppCompatActivity() {

    // Variable for Data Binding of home activity layout
    //private lateinit var  mViewPager : ViewPager
    //private lateinit var mTabLayout : TabLayout
    // Create one adapter to know which fragment should show on each page
    private val homeAdapter: HomePagerAdapter = HomePagerAdapter(supportFragmentManager)
    //lateinit var mToolbar : Toolbar
    private val TITLE_TAB_CASAS_DE_CARNES = "Casas de Carnes"
    private val TITLE_TAB_CUPONS = "Cupons"
    private var listFragmentTitles = listOf(TITLE_TAB_CASAS_DE_CARNES,TITLE_TAB_CUPONS)
    private var permissions = arrayOf(Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION)
    lateinit var locationManager: LocationManager
    private var hasGps = false
    private var hasNetwork = false
    private var locationGps: Location? = null
    private var locationNetwork: Location? = null
    private lateinit var viewModel: ButchersViewModel

    companion object {
        private const val PERMISSION_REQUEST = 10
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        // Create a ViewModel the first time the system calls an activity's onCreate() method.
        // Re-created activities receive the same MyViewModel instance created by the first activity
        viewModel = ViewModelProviders.of(this).get(ButchersViewModel::class.java)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (checkPermission(permissions)) {
                //Verificar o que fazer
                getLocation()
            } else {
                requestPermissions(permissions, PERMISSION_REQUEST)
            }
        }

        // Set the support action bar
        setSupportActionBar(home_toolbar)

        // Add fragments to the ViewPager
        homeAdapter.addFragment(ButchersFragment())
        homeAdapter.addFragment(CuponsFragment())

        // Set the viewpager adapter
        main_viewpager.adapter = homeAdapter

        // Set the tablayout with the viewpager
        main_tablayout.setupWithViewPager(main_viewpager)

        // Change the toolbar title according to the tab
        main_viewpager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrollStateChanged(state: Int) {
            }

            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
            }

            override fun onPageSelected(position: Int) {
                home_toolbar.title = listFragmentTitles[position]
            }
        })

        // Define the icons for the tab layout
        setupTabLayoutIcons()
    }

    /**
     * Set the TabLayout icons drawable and view.
     */
    private fun setupTabLayoutIcons() {
        main_tablayout.getTabAt(0)?.setIcon(R.drawable.tab_icon_acougues)?.setCustomView(R.layout.view_home_tab)
        main_tablayout.getTabAt(1)?.setIcon(R.drawable.tab_icon_cupons)?.setCustomView(R.layout.view_home_tab)
    }

    fun switchContent(id: Int, fragment: Fragment) {
        val ft = supportFragmentManager.beginTransaction()
        ft.replace(id, fragment, fragment.toString())
        ft.addToBackStack(null)
        ft.commit()
    }

    @SuppressLint("MissingPermission")
    private fun getLocation() {
        locationManager = getSystemService(Context.LOCATION_SERVICE) as LocationManager
        hasGps = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)
        hasNetwork = locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER)
        if (hasGps || hasNetwork) {

            if (hasGps) {
                Log.d("CodeAndroidLocation", "hasGps")
                locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 5000, 0F, object : LocationListener {
                    override fun onLocationChanged(location: Location?) {
                        if (location != null) {
                            locationGps = location
                            Log.d("CodeAndroidLocation", " GPS Latitude : " + locationGps!!.latitude)
                            Log.d("CodeAndroidLocation", " GPS Longitude : " + locationGps!!.longitude)
                            viewModel.updateUserGeoLocation(locationGps!!.latitude, locationGps!!.longitude)
                            Log.d("CodeAndroidLocation","User latitude: ${viewModel.userLatitude.value} longitude: ${viewModel.userLongitude.value}")
                        }
                    }

                    override fun onStatusChanged(provider: String?, status: Int, extras: Bundle?) {

                    }

                    override fun onProviderEnabled(provider: String?) {
                        Log.d("CodeAndroidLocation", " ATIVOUUUUUUUUU")

                    }

                    override fun onProviderDisabled(provider: String?) {

                    }

                })

                val localGpsLocation = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER)
                if (localGpsLocation != null)
                    locationGps = localGpsLocation
            }
            if (hasNetwork) {
                Log.d("CodeAndroidLocation", "hasGps")
                locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 5000, 0F, object : LocationListener {
                    override fun onLocationChanged(location: Location?) {
                        if (location != null) {
                            locationNetwork = location
                            viewModel.userLatitude.value = locationNetwork!!.latitude
                            viewModel.userLatitude.value = locationNetwork!!.longitude
                            Log.d("CodeAndroidLocation", " Network Latitude : " + locationNetwork!!.latitude)
                            Log.d("CodeAndroidLocation", " Network Longitude : " + locationNetwork!!.longitude)
                            viewModel.updateUserGeoLocation(locationNetwork!!.latitude, locationNetwork!!.longitude)
                            Log.d("CodeAndroidLocation","User latitude: ${viewModel.userLatitude.value} longitude: ${viewModel.userLongitude.value}")
                        }
                    }

                    override fun onStatusChanged(provider: String?, status: Int, extras: Bundle?) {

                    }

                    override fun onProviderEnabled(provider: String?) {
                        Log.d("CodeAndroidLocation", " ATIVOUUUUUUUUU")

                    }

                    override fun onProviderDisabled(provider: String?) {

                    }

                })

                val localNetworkLocation = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER)
                if (localNetworkLocation != null)
                    locationNetwork = localNetworkLocation
            }

            if(locationGps!= null && locationNetwork!= null){
                if(locationGps!!.accuracy > locationNetwork!!.accuracy){

                    Log.d("CodeAndroidLocation", " Network Latitude : " + locationNetwork!!.latitude)
                    Log.d("CodeAndroidLocation", " Network Longitude : " + locationNetwork!!.longitude)
                    viewModel.updateUserGeoLocation(locationGps!!.latitude, locationGps!!.longitude)
                }else{
                    Log.d("CodeAndroidLocation", " GPS Latitude : " + locationGps!!.latitude)
                    Log.d("CodeAndroidLocation", " GPS Longitude : " + locationGps!!.longitude)
                    viewModel.updateUserGeoLocation(locationGps!!.latitude, locationGps!!.longitude)
                }
            }

        } else {
            startActivity(Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS))
        }
    }

    private fun checkPermission(permissionArray: Array<String>): Boolean {
        var allSuccess = true
        for (i in permissionArray.indices) {
            if (checkCallingOrSelfPermission(permissionArray[i]) == PackageManager.PERMISSION_DENIED)
                allSuccess = false
        }
        return allSuccess
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == PERMISSION_REQUEST) {
            for (i in permissions.indices) {
                if (grantResults[i] == PackageManager.PERMISSION_DENIED) {
                    val requestAgain = Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && shouldShowRequestPermissionRationale(permissions[i])
                    if (requestAgain) {
                        Toast.makeText(this, "Permission denied", Toast.LENGTH_SHORT).show()
                    } else {
                        Toast.makeText(this, "Go to settings and enable the permission", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }
    }


}