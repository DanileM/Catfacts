package com.xander.catfacts.util.extension.bindcontract

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.xander.catfacts.data.model.CatFact
import com.xander.catfacts.presentation.base.BaseDataBindAdapter


@BindingAdapter("setAdapterBindData")
fun RecyclerView.setAdapterBindData(dataBindAdapter: BaseDataBindAdapter<CatFact>?) {
    if (adapter == null) {
        layoutManager = LinearLayoutManager(context)
        setHasFixedSize(true)
        addItemDecoration(
            DividerItemDecoration(
                context,
                DividerItemDecoration.VERTICAL
            )
        )
        adapter = dataBindAdapter
    }
}

@BindingAdapter("setImage")
fun ImageView.setImage(imageUrl: String) {
    Glide.with(this)
        .load(imageUrl)
        .into(this)
}