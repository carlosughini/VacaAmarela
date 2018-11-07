package com.vacaamarela.carlos.vacaamarela.ui.detail

import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.vacaamarela.carlos.vacaamarela.R
import com.vacaamarela.carlos.vacaamarela.ui.home.ButchersViewModel

class DetailFragment : Fragment() {

    private var butchersViewModel = ButchersViewModel()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_butchery_detail, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        Log.d("DetailFragment","Butchery name: ${butchersViewModel.butchery?.butcheryName} latitude: ${butchersViewModel.userLatitude} e longitude: ${butchersViewModel.userLongitude}")

        Toast.makeText(context, "TESTE", Toast.LENGTH_SHORT).show()
    }
}