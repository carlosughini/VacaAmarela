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

    var mGoogleMap: GoogleMap? = null
    var mMapView: MapView? = null
    var mView: View? = null

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

        val sydney = LatLng(-34.0, 151.0)
        mGoogleMap!!.addMarker(MarkerOptions().position(sydney))
        mGoogleMap!!.moveCamera(CameraUpdateFactory.newLatLng(sydney))
        mGoogleMap!!.animateCamera(CameraUpdateFactory.newLatLngZoom(sydney, 15.0F))

    }
}
