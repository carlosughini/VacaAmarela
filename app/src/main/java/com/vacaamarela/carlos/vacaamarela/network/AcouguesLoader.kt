package com.vacaamarela.carlos.vacaamarela.network

import android.content.Context
import android.support.v4.content.AsyncTaskLoader
import com.vacaamarela.carlos.vacaamarela.model.Acougue

/**
 * Loads a list of butcherys by using an AsyncTask to perform the
 * network request to the given URL.
 */
class AcouguesLoader(context: Context?, url : String) : AsyncTaskLoader<MutableList<Acougue>>(context) {

    /** Tag for log messages  */
    private val TAG = AcouguesLoader::class.java.name

    /** search URL  */
    private val mUrl: String = url

    override fun onStartLoading() = forceLoad()

    override fun loadInBackground(): MutableList<Acougue>? {
        // Realiza requisição de rede, decodifica a resposta e extrai uma lista de Acougue
        return QueryUtils.extractButchers(mUrl)
    }

}