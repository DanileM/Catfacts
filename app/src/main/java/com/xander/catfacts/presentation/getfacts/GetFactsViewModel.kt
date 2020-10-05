package com.xander.catfacts.presentation.getfacts

import androidx.databinding.Bindable
import androidx.databinding.library.baseAdapters.BR
import com.xander.catfacts.data.model.CatFact
import com.xander.catfacts.presentation.base.BaseViewModel
import com.xander.catfacts.util.livedata.SingleLiveEvent
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch

class GetFactsViewModel(
    private val factsUseCase: FactsUseCase,
    private val coroutineScope: CoroutineScope = CoroutineScope(Dispatchers.IO)
) : BaseViewModel() {

    var liveDataShowFacts = SingleLiveEvent<ArrayList<CatFact>>()


    @Bindable
    var enabledButton = true
        set(value) {
            field = value
            notifyPropertyChanged(BR.enabledButton)
        }

    fun getFacts() {
        coroutineScope.launch {
            liveDataShowFacts.postValue(factsUseCase.getCatFacts() as ArrayList<CatFact>)
        }
    }

    override fun onCleared() {
        super.onCleared()
        coroutineScope.coroutineContext.cancel()
    }

}