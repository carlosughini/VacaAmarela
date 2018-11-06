package com.vacaamarela.carlos.vacaamarela.utils

import android.support.annotation.LayoutRes
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup


/**
 * Trick way to inflate fragments. It is like a viewgroup it is able to inflate by itself.
 * We set the third paramter (attachToRoot) to false, because the FragmentManager will take
 * care of it for us.
 */
fun ViewGroup.inflate(layoutId: Int): View {
    return LayoutInflater.from(context).inflate(layoutId, this, false)
}