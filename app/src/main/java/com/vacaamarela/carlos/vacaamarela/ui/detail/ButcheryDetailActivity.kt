package com.vacaamarela.carlos.vacaamarela.ui.detail

import android.arch.lifecycle.ViewModelProviders
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.NavUtils
import android.util.Log
import android.view.MenuItem
import com.vacaamarela.carlos.vacaamarela.R
import com.vacaamarela.carlos.vacaamarela.model.Butchery
import com.vacaamarela.carlos.vacaamarela.ui.detail.adapter.DetailPagerAdapter
import kotlinx.android.synthetic.main.activity_butchery_detail.*

class ButcheryDetailActivity : AppCompatActivity() {

    private lateinit var butcheryViewModel: ButcheryViewModel
    // Tag for the log messages
    private val TAG = ButcheryDetailActivity::class.java.simpleName
    // Create one adapter to know which fragment should show on each page
    private val detailPagerAdapter: DetailPagerAdapter = DetailPagerAdapter(supportFragmentManager)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_butchery_detail)

        // Create a ViewModel the first time the system calls an activity's onCreate() method.
        // Re-created activities receive the same MyViewModel instance created by the first activity
        butcheryViewModel = ViewModelProviders.of(this).get(ButcheryViewModel::class.java)

        // Get the butchery object passed by intent extras
        val butchery = intent?.extras?.getSerializable("a") as? Butchery

        Log.d(TAG,"Butchery name: ${butchery?.name} latitude: ${butchery?.latitude} e longitude: ${butchery?.longitude}")

        // Get a support ActionBar corresponding to this toolbar and enable the Up button
        actionBar?.setDisplayHomeAsUpEnabled(true)
        // Change actionBar title
        actionBar?.title = butchery?.name

        // Add fragments to the ViewPager
        detailPagerAdapter.addFragment(DescriptionFragment())
        detailPagerAdapter.addFragment(RegulationFragment())

        // Set the ViewPager adapter
        butchery_viewPager.adapter = detailPagerAdapter

        // Set the tablayout with the ViewPager
        butchery_tabLayout.setupWithViewPager(butchery_viewPager)
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
