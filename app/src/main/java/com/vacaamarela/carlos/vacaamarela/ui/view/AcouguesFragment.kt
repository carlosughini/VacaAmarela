package com.vacaamarela.carlos.vacaamarela.ui.view

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Adapter
import com.vacaamarela.carlos.vacaamarela.R
import com.vacaamarela.carlos.vacaamarela.utils.inflate
import kotlinx.android.synthetic.main.fragment_acougues.*

class AcouguesFragment : Fragment() {

    /**
     * Creates a companion object (static method) to instantiate a new fragment.
     */
    companion object {
        fun newInstanceCharactersFragment() : AcouguesFragment {
            return AcouguesFragment()
        }
    }

    private val acouguesList by lazy {
        rv_acougues
    }

    val animals: ArrayList<String> = ArrayList()

    private lateinit var viewAdapter: Adapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflates layout for the fragment using extension
        return container?.inflate(R.layout.fragment_acougues)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        /**
         * Improve performance of the recycler view by not requesting a new layout when
         * whenever items are inserted, moved or removed the size.
         */
        acouguesList.setHasFixedSize(true)

        /**
         * Set the layout manager responsible for measuring and positioning item views within
         * a Recycler View as well as determining the policy for when to recycle item views
         * that are no longer visible to the user.
         */
        acouguesList.layoutManager = LinearLayoutManager(context)

        //viewAdapter = CharactersAdapter
    }

}