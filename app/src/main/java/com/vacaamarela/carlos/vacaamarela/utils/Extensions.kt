package com.vacaamarela.carlos.vacaamarela.utils

import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

/**
 * Here we create extensions to make more intuitive ways to instantiate different things.
 */

/**
 * Extension to avoid duplicate code to add fragments.
 */
fun AppCompatActivity.addFragmentTo(containerId: Int, fragment: Fragment, tag: String = "") {
    supportFragmentManager.beginTransaction().add(containerId, fragment, tag).commit()
}

/**
 * Trick way to inflate fragments. It is like a viewgroup it is able to inflate by itself.
 * We set the third paramter (attachToRoot) to false, because the FragmentManager will take
 * care of it for us.
 */
fun ViewGroup.inflate(layoutId: Int): View {
    return LayoutInflater.from(context).inflate(layoutId, this, false)
}