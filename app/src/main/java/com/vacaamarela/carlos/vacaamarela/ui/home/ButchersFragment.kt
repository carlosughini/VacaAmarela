package com.vacaamarela.carlos.vacaamarela.ui.view

import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.LoaderManager
import android.support.v4.content.Loader
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.vacaamarela.carlos.vacaamarela.R
import com.vacaamarela.carlos.vacaamarela.model.Butchery
import com.vacaamarela.carlos.vacaamarela.MainActivity
import com.vacaamarela.carlos.vacaamarela.network.AcouguesLoader
import com.vacaamarela.carlos.vacaamarela.ui.adapter.ButchersRecyclerViewAdapter
import com.vacaamarela.carlos.vacaamarela.ui.home.ButchersViewModel
import com.vacaamarela.carlos.vacaamarela.utils.BASE_URL
import kotlinx.android.synthetic.main.fragment_acougues.*

class ButchersFragment : Fragment(),
        LoaderManager.LoaderCallbacks<MutableList<Butchery>> {

    /**
     * Creates a companion object (static method) to instantiate a new fragment.
     */
    companion object {
        fun newInstanceCharactersFragment() : ButchersFragment {
            return ButchersFragment()
        }
    }

    /** Tag for the log messages  */
    private val TAG = MainActivity::class.java.simpleName

    private lateinit var mAdapter: ButchersRecyclerViewAdapter
    private lateinit var linearLayoutManager: LinearLayoutManager
    private var listaAcougues = mutableListOf<Butchery>()
    private lateinit var viewModel: ButchersViewModel

    /**
     * Valor constante para o ID do loader de earthquake. Podemos escolher qualquer inteiro.
     * Isto só importa realmente se você estiver usando múltiplos loaders.
     */
    private val ACOUGUES_LOADER_ID = 1


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflates layout for the fragment
        return inflater.inflate(R.layout.fragment_acougues, container, false);
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        /**
         * Improve performance of the recycler view by not requesting a new layout when
         * whenever items are inserted, moved or removed the size.
         */
        recycler_view.setHasFixedSize(true)

        /**
         * Set the layout manager responsible for measuring and positioning item views within
         * a Recycler View as well as determining the policy for when to recycle item views
         * that are no longer visible to the user.
         */
        recycler_view.layoutManager = LinearLayoutManager(context)
        mAdapter = ButchersRecyclerViewAdapter(listaAcougues, context)
        recycler_view.adapter = mAdapter

        viewModel = ViewModelProviders.of(this).get(ButchersViewModel::class.java)
    }

    override fun onStart() {
        super.onStart()
        // Get a reference to the LoaderManager, in order to interact with loaders.
        val loaderManager = loaderManager
        // Initialize the loader. Pass in the int ID constant defined above and pass in null for
        // the bundle. Pass in this activity for the LoaderCallbacks parameter (which is valid
        // because this activity implements the LoaderCallbacks interface).
        loaderManager.initLoader(ACOUGUES_LOADER_ID, null, this)
    }

    override fun onCreateLoader(id: Int, args: Bundle?): Loader<MutableList<Butchery>> {
        progress_bar.visibility = View.VISIBLE
        return AcouguesLoader(context, BASE_URL)
    }

    override fun onLoadFinished(loader: Loader<MutableList<Butchery>>?, listaButcheries: MutableList<Butchery>?) {
        progress_bar.visibility = View.INVISIBLE

        /**
         * Se há uma lista válida de {@link Earthquake} então os adiciona ao data set do adapter.
         * Isto ativará a atualização da ListView.
         */
        if (listaButcheries != null && !listaButcheries.isEmpty()) {
            mAdapter.replaceData(listaButcheries)
        } else {
            empty_text.setText(R.string.acougues_nao_localizados)
        }
    }

    override fun onLoaderReset(loader: Loader<MutableList<Butchery>>?) {
        Log.v(TAG, "OnLoaderReset")
        mAdapter.clearData()
    }

}