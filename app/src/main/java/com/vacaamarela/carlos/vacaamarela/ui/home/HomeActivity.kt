package com.vacaamarela.carlos.vacaamarela.ui.home

import android.os.Bundle
import android.support.design.widget.TabLayout
import android.support.v4.view.ViewPager
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import com.vacaamarela.carlos.vacaamarela.R
import com.vacaamarela.carlos.vacaamarela.ui.adapter.HomePagerAdapter
import com.vacaamarela.carlos.vacaamarela.ui.view.ButchersFragment
import com.vacaamarela.carlos.vacaamarela.ui.view.CuponsFragment
import kotlinx.android.synthetic.main.activity_home.*

class HomeActivity : AppCompatActivity() {

    // Variable for Data Binding of home activity layout
    private lateinit var  mViewPager : ViewPager
    private lateinit var mTabLayout : TabLayout
    // Create one adapter to know which fragment should show on each page
    private val mHomeAdapter: HomePagerAdapter = HomePagerAdapter(supportFragmentManager)
    lateinit var mToolbar : Toolbar
    private val TITLE_TAB_CASAS_DE_CARNES = "Casas de Carnes"
    private val TITLE_TAB_CUPONS = "Cupons"
    private var listFragmentTitles = listOf(TITLE_TAB_CASAS_DE_CARNES,TITLE_TAB_CUPONS)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        // Set the viewpager
        mViewPager = main_viewpager
        // Set tablayout
        mTabLayout = main_tablayout
        // Set the toolbar
        mToolbar = home_toolbar
        setSupportActionBar(mToolbar)


        // Add fragments to the ViewPager
        mHomeAdapter.addFragment(ButchersFragment())
        mHomeAdapter.addFragment(CuponsFragment())
        // Set the viewpager adapter
        mViewPager.adapter = mHomeAdapter
        // Set the tablayout with the viewpager
        mTabLayout.setupWithViewPager(mViewPager)

        mViewPager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrollStateChanged(state: Int) {
            }

            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
            }

            override fun onPageSelected(position: Int) {
                mToolbar.title = listFragmentTitles[position]
            }
        })

        setupTabLayoutIcons()
    }

    /**
     * Set the TabLayout icons drawable and view.
     */
    private fun setupTabLayoutIcons() {
        mTabLayout.getTabAt(0)?.setIcon(R.drawable.tab_icon_acougues)?.setCustomView(R.layout.view_home_tab)
        mTabLayout.getTabAt(1)?.setIcon(R.drawable.tab_icon_cupons)?.setCustomView(R.layout.view_home_tab)
    }

}