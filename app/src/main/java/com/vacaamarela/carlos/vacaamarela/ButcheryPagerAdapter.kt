package com.vacaamarela.carlos.vacaamarela

import android.content.Context
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter

class ButcheryPagerAdapter : FragmentPagerAdapter {

    var mContext: Context

    constructor(context: Context, fm: FragmentManager) : super(fm) {
        mContext = context
    }

    override fun getItem(position: Int): Fragment? {
        return when (position) {
            0 -> PromotionsFragment()
            1 -> MapFragment()
            else -> {
                null
            }
        }
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return when (position) {
            0 -> mContext.getString(R.string.fragment_promocoes)
            2 -> mContext.getString(R.string.fragment_map)
            else -> {
                null
            }
        }
    }

    override fun getCount(): Int {
        return 4
    }

}