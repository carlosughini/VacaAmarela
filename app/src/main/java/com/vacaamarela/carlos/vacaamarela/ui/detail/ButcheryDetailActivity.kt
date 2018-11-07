package com.vacaamarela.carlos.vacaamarela.ui.detail

import android.arch.lifecycle.ViewModelProviders
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.TabLayout
import android.support.v4.app.NavUtils
import android.support.v4.view.ViewPager
import android.util.Log
import android.view.MenuItem
import com.vacaamarela.carlos.vacaamarela.Butcheryyy
import com.vacaamarela.carlos.vacaamarela.MainActivity
import com.vacaamarela.carlos.vacaamarela.R
import com.vacaamarela.carlos.vacaamarela.ui.home.ButchersViewModel
import kotlinx.android.synthetic.main.activity_butchery_detail.*

class ButcheryDetailActivity : AppCompatActivity() {

    private var butchersViewModel = ButchersViewModel()
    /** Tag for the log messages  */
    private val TAG = ButcheryDetailActivity::class.java.simpleName

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_butchery_detail)

        // Get the butcheryyy object passed by intent extras

        Log.d(TAG,"Butchery name: ${butchersViewModel.butchery?.butcheryName} latitude: ${butchersViewModel.userLatitude} e longitude: ${butchersViewModel.userLongitude}")
        //val butchery = intent.extras.get("Butcheryyy") as Butcheryyy

        // Get a support ActionBar corresponding to this toolbar and enable the Up button
        actionBar?.setDisplayHomeAsUpEnabled(true)
        // Change actionBar title
        actionBar?.title = butchersViewModel.butchery?.butcheryName

        // Create one adapter to know which fragment should show on each page
        //val butcheryAdapter: ButcheryPagerAdapter = ButcheryPagerAdapter(this,supportFragmentManager);

        // Set adapter on view pager
        //viewPager.adapter = butcheryAdapter

        // Give to the TabLayout the ViewPager
        val tabLayout = findViewById<TabLayout>(R.id.butchery_tabLayout)

        // Set up the TabLayout with the viewPager
        tabLayout.setupWithViewPager(butchery_viewPager)

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                // Respond to the action bar's Up/Home button
                NavUtils.navigateUpFromSameTask(this)
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }
}
