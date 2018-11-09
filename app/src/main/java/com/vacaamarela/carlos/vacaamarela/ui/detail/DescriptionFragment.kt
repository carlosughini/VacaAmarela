package com.vacaamarela.carlos.vacaamarela.ui.detail

import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.vacaamarela.carlos.vacaamarela.R
import com.vacaamarela.carlos.vacaamarela.ui.home.ButchersViewModel

class DescriptionFragment : Fragment() {

    private var butchersViewModel = ButchersViewModel()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_description, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        Log.d("DescriptionFragment","Butchery name: ${butchersViewModel.butchery?.name} latitude: ${butchersViewModel.userLatitude} e longitude: ${butchersViewModel.userLongitude}")
    }
}