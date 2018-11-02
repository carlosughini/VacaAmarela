package com.vacaamarela.carlos.vacaamarela

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.TabLayout
import android.support.v4.view.ViewPager
import com.vacaamarela.carlos.vacaamarela.model.Acougue
import com.vacaamarela.carlos.vacaamarela.ui.adapter.HomePagerAdapter

class ButcheryDetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.butchery_view_pager)

        // Get the butchery object passed by intent extras
        val butchery = intent.extras.get("Acougue") as Acougue

        // Get the actionBar
        val actionBar = supportActionBar
        // Change actionBar title
        actionBar?.title = butchery.nome
        // Get a support ActionBar corresponding to this toolbar and enable the Up button
        actionBar?.setDisplayHomeAsUpEnabled(true)

        // Find the view pager
        val viewPager = findViewById<ViewPager>(R.id.butchery_viewPager)

        // Create one adapter to know which fragment should show on each page
        val homeAdapter: HomePagerAdapter = HomePagerAdapter(supportFragmentManager);

        // Set adapter on view pager
        viewPager.adapter = homeAdapter

        // Give to the TabLayout the ViewPager
        val tabLayout = findViewById<TabLayout>(R.id.butchery_tabLayout)

        // Set up the TabLayout with the viewPager
        tabLayout.setupWithViewPager(viewPager)

    }
}
