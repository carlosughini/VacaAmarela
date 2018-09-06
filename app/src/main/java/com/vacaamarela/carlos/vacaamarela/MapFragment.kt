package com.vacaamarela.carlos.vacaamarela


import android.os.Bundle
import android.support.v4.app.ActivityCompat
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.gms.maps.*
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import kotlinx.android.synthetic.*

/**
 * A simple [Fragment] subclass that shows a map with the butchery location.
 *
 */
class MapFragment : Fragment(), OnMapReadyCallback  {

    private var mGoogleMap: GoogleMap? = null
    private var mMapView: MapView? = null
    private var mView: View? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        mView = inflater.inflate(R.layout.fragment_map, container, false)
        return mView
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mMapView = mView?.findViewById(R.id.map)
        if (mMapView != null) {
            mMapView!!.onCreate(null)
            mMapView!!.onResume()
            mMapView!!.getMapAsync(this)
        }
    }

    override fun onMapReady(googleMap: GoogleMap) {

        MapsInitializer.initialize(context)

        mGoogleMap = googleMap

        val sydney = LatLng(-30.0205258, -51.2030004)
        mGoogleMap!!.addMarker(MarkerOptions().position(sydney))
        mGoogleMap!!.moveCamera(CameraUpdateFactory.newLatLng(sydney))
        mGoogleMap!!.mapType = GoogleMap.MAP_TYPE_NORMAL
        mGoogleMap!!.animateCamera(CameraUpdateFactory.newLatLngZoom(sydney, 18.0F))

    }
}
