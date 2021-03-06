package com.vacaamarela.carlos.vacaamarela.network

import android.content.Context
import android.support.v4.content.AsyncTaskLoader
import com.vacaamarela.carlos.vacaamarela.model.Butchery

/**
 * Loads a list of butcherys by using an AsyncTask to perform the
 * network request to the given URL.
 */
class AcouguesLoader(context: Context?, url : String, latitude: String, longitude: String) : AsyncTaskLoader<MutableList<Butchery>>(context) {

    /** Tag for log messages  */
    private val TAG = AcouguesLoader::class.java.name

    /** Search URL  */
    private val mUrl: String = url

    private val mLatitude: String = latitude

    private val mLongitude: String = longitude

    override fun onStartLoading() = forceLoad()

    override fun loadInBackground(): MutableList<Butchery>? {
        // Realiza requisição de rede, decodifica a resposta e extrai uma lista de Butchery
        return QueryUtils.extractButchers(mUrl, mLatitude, mLongitude)
    }

}