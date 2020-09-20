package com.xander.catfacts.ui.viewmodel

import android.widget.Toast
import androidx.databinding.Bindable
import androidx.databinding.library.baseAdapters.BR
import com.xander.catfacts.App
import com.xander.catfacts.api.FactServiceApi
import com.xander.catfacts.api.PictureServiceApi
import com.xander.catfacts.dao.CatFactsDao
import com.xander.catfacts.model.CatFact
import com.xander.catfacts.ui.livedata.SingleLiveEvent
import com.xander.catfacts.ui.viewmodel.base.BaseViewModel
import com.xander.catfacts.util.NetworkState
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch

class GetFactsViewModel(
    private val pictureServiceApi: PictureServiceApi,
    private val factServiceApi: FactServiceApi,
    private val networkState: NetworkState,
    private val catFactsDao: CatFactsDao,
    private val coroutineScope: CoroutineScope = CoroutineScope(Dispatchers.IO)
) : BaseViewModel() {

    private var catFactsList = arrayListOf<CatFact>()
    var liveDataShowFacts = SingleLiveEvent<ArrayList<CatFact>>()


    @Bindable
    var enabledButton = true
        set(value) {
            field = value
            notifyPropertyChanged(BR.enabledButton)
        }

    fun getFacts() {
        catFactsList.clear()
        enabledButton = false
        if(networkState.isNetworkAvailable()) {
            coroutineScope.launch {
                while (catFactsList.size != 10) {
                    val catFact = CatFact()
                    try {
                        val responsePicture = pictureServiceApi.getPicture().await()
                        catFact.picture = responsePicture.picture

                        val responseFact = factServiceApi.getFact().await()
                        catFact.fact = responseFact.fact

                        catFactsList.add(catFact)

                        checkIsListFull()
                    } catch (e: Exception) {
                        //handle error
                        enabledButton = true
                    }
                }
            }
        } else {
            enabledButton = true
            Toast.makeText(App.getInstance(), "No internet. Got data from cache", Toast.LENGTH_LONG).show()
            coroutineScope.launch {
                liveDataShowFacts.postValue(catFactsDao.getLast().reversed() as ArrayList<CatFact>)
            }
        }
    }

    private fun checkIsListFull(){
        if(catFactsList.size == 10) {
            coroutineScope.launch { catFactsDao.add(catFactsList) }
            liveDataShowFacts.postValue(catFactsList)
            enabledButton = true
        }
    }

    override fun onCleared() {
        super.onCleared()
        coroutineScope.coroutineContext.cancel()
    }

}