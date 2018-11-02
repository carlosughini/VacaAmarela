package com.vacaamarela.carlos.vacaamarela.ui.adapter

import android.content.Context
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import com.vacaamarela.carlos.vacaamarela.R
import com.vacaamarela.carlos.vacaamarela.ui.view.AcouguesFragment
import com.vacaamarela.carlos.vacaamarela.ui.view.CuponsFragment

class HomePagerAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm) {

    private var listOfFragments = arrayListOf<Fragment>()

    override fun getItem(position: Int): Fragment? {
        return listOfFragments[position]

    }

    override fun getCount(): Int {
        return 2
    }

    fun addFragment(fragment: Fragment) {
        listOfFragments.add(fragment)
    }

}