package com.xander.catfacts.di

import androidx.room.Room
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.xander.catfacts.App
import com.xander.catfacts.R
import com.xander.catfacts.api.FactServiceApi
import com.xander.catfacts.api.PictureServiceApi
import com.xander.catfacts.constant.UrlConstants
import com.xander.catfacts.dao.AppDatabase
import com.xander.catfacts.dao.CatFactsDao
import com.xander.catfacts.model.CatFact
import com.xander.catfacts.ui.viewmodel.GetFactsViewModel
import com.xander.catfacts.ui.viewmodel.ShowFactsViewModel
import com.xander.catfacts.util.NetworkState
import org.koin.android.viewmodel.ext.koin.viewModel
import org.koin.dsl.module.Module
import org.koin.dsl.module.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val appModule: Module = module {

    single { NetworkState(get()) }

    single { providePicture() }

    single { provideFact() }

    single { provideRoom() }

    single {
        get<AppDatabase>().catFactsDao()
    }
}

val viewModelModule: Module = module {

    viewModel { GetFactsViewModel(get(), get(), get(), get()) }

    viewModel { (factsList: ArrayList<CatFact>) -> ShowFactsViewModel(factsList) }

}

private fun provideRoom(): AppDatabase =
    Room.databaseBuilder(
        App.getInstance(),
        AppDatabase::class.java, App.getInstance().getString(R.string.db_name)
    ).build()

private fun providePicture(): PictureServiceApi =
    Retrofit.Builder()
        .baseUrl(UrlConstants.PICTURE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(CoroutineCallAdapterFactory())
        .build()
        .create(PictureServiceApi::class.java)


private fun provideFact(): FactServiceApi =
    Retrofit.Builder()
        .baseUrl(UrlConstants.FACT_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(CoroutineCallAdapterFactory())
        .build()
        .create(FactServiceApi::class.java)


