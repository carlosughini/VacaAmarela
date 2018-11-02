package com.vacaamarela.carlos.vacaamarela.ui.view

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.design.widget.TabLayout
import android.support.v4.view.ViewPager
import android.support.v7.app.AppCompatActivity
import com.vacaamarela.carlos.vacaamarela.R
import com.vacaamarela.carlos.vacaamarela.databinding.ActivityHomeBinding
import com.vacaamarela.carlos.vacaamarela.ui.adapter.HomePagerAdapter

class HomeActivity : AppCompatActivity() {

    lateinit var binding: ActivityHomeBinding
    private lateinit var  viewPager : ViewPager
    private lateinit var tabLayout : TabLayout
    // Create one adapter to know which fragment should show on each page
    private val homeAdapter: HomePagerAdapter = HomePagerAdapter(supportFragmentManager)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_home)
        viewPager = binding.mainViewpager
        tabLayout = binding.mainTablayout
        homeAdapter.addFragment(AcouguesFragment())
        homeAdapter.addFragment(CuponsFragment())
        viewPager.adapter = homeAdapter
        tabLayout.setupWithViewPager(viewPager)

        setupTabLayoutIcons()
    }

    private fun setupTabLayoutIcons() {
        tabLayout.getTabAt(0)?.setIcon(R.drawable.tab_icon_acougues)?.setCustomView(R.layout.view_home_tab)
        tabLayout.getTabAt(1)?.setIcon(R.drawable.tab_icon_cupons)?.setCustomView(R.layout.view_home_tab)
    }

}