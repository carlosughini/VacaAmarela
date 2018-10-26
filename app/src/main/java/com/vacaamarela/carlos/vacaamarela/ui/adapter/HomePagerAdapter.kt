package com.vacaamarela.carlos.vacaamarela.ui.adapter

import android.content.Context
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import com.vacaamarela.carlos.vacaamarela.R
import com.vacaamarela.carlos.vacaamarela.ui.view.AcouguesFragment
import com.vacaamarela.carlos.vacaamarela.ui.view.CuponsFragment

class HomePagerAdapter(context: Context, fm: FragmentManager) : FragmentPagerAdapter(fm) {

    var mContext: Context = context

    override fun getItem(position: Int): Fragment? {
        return when (position) {
            0 -> AcouguesFragment()
            1 -> CuponsFragment()
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
        return 2
    }

}