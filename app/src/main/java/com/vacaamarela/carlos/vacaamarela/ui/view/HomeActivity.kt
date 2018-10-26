package com.vacaamarela.carlos.vacaamarela.ui.view

import android.annotation.SuppressLint
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.design.widget.TabLayout
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
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
    private val homeAdapter: HomePagerAdapter = HomePagerAdapter(this, supportFragmentManager);

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_home)
        viewPager = binding.mainViewpager
        tabLayout = binding.mainTablayout
        viewPager.adapter = homeAdapter
        tabLayout.setupWithViewPager(viewPager)
    }

}