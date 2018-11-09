package com.vacaamarela.carlos.vacaamarela.ui.detail.adapter

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter

class DetailPagerAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm) {

    // Create a list to store fragments to show on ButcheryDetailActivity
    private var listOfFragments = arrayListOf<Fragment>()

    /**
     * Return the Fragment associated with a specified position.
     */
    override fun getItem(position: Int): Fragment {
        return listOfFragments[position]
    }

    /**
     * Return the number of views available.
     */
    override fun getCount(): Int {
        return 2
    }

    /**
     * Add fragments to the listOfFragments.
     */
    fun addFragment(fragment: Fragment) {
        listOfFragments.add(fragment)
    }
}