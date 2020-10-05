package com.xander.catfacts.presentation.showfacts

import androidx.databinding.library.baseAdapters.BR
import androidx.lifecycle.MutableLiveData
import com.xander.catfacts.R
import com.xander.catfacts.data.model.CatFact
import com.xander.catfacts.presentation.base.BaseDataBindAdapter
import com.xander.catfacts.presentation.base.BaseViewModel

class ShowFactsViewModel(catFactsList: ArrayList<CatFact>) : BaseViewModel() {

    var liveDataShowDialogWithFact = MutableLiveData<CatFact>()

    val adapter = BaseDataBindAdapter(
        R.layout.item_cat_fact, BR.dataChoiceItem, catFactsList, this
    )

    init {
        adapter.onItemClick = {
            liveDataShowDialogWithFact.postValue(it)
        }
    }

}