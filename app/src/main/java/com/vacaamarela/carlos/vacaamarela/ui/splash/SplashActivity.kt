package com.vacaamarela.carlos.vacaamarela.ui.splash

import android.app.Activity
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.support.v4.view.accessibility.AccessibilityNodeInfoCompat
import android.view.Window
import android.view.WindowManager
import com.vacaamarela.carlos.vacaamarela.R
import com.vacaamarela.carlos.vacaamarela.ui.home.HomeActivity

class SplashActivity : Activity() {

    private var mDelayHandler: Handler? = null
    private val SPLASH_DELAY: Long = 3000 // 3 seconds

    private val mRunnable = Runnable {
        if (!isFinishing) {

            val listActivity = Intent(applicationContext, HomeActivity::class.java)
            startActivity(listActivity)
            finish()

        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        hideStatusBar()
        setContentView(R.layout.activity_splash)

        // Initialize the Handler
        mDelayHandler = Handler()

        // Navigate with delay
        mDelayHandler!!.postDelayed(mRunnable, SPLASH_DELAY)
    }

    override fun onDestroy() {
        if (mDelayHandler != null) {
            mDelayHandler!!.removeCallbacks(mRunnable)
        }

        super.onDestroy()
    }

    private fun hideStatusBar(){
        if (Build.VERSION.SDK_INT >= 21) {
            getWindow().setFlags(AccessibilityNodeInfoCompat.ACTION_NEXT_HTML_ELEMENT, AccessibilityNodeInfoCompat.ACTION_NEXT_HTML_ELEMENT);
            getWindow().getDecorView().setSystemUiVisibility(3328);
        }else{
            requestWindowFeature(Window.FEATURE_NO_TITLE);
            this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        }
    }
}