package com.xander.catfacts.di

import androidx.room.Room
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.xander.catfacts.App
import com.xander.catfacts.R
import com.xander.catfacts.data.api.FactServiceApi
import com.xander.catfacts.data.api.PictureServiceApi
import com.xander.catfacts.data.db.AppDatabase
import com.xander.catfacts.data.db.dao.CatFactsDao
import com.xander.catfacts.data.model.CatFact
import com.xander.catfacts.data.repository.FactsRepositoryImpl
import com.xander.catfacts.data.repository.LocalFactsRepositoryImpl
import com.xander.catfacts.domain.usecase.FactsRepository
import com.xander.catfacts.domain.usecase.FactsUseCaseImpl
import com.xander.catfacts.domain.usecase.LocalFactsRepository
import com.xander.catfacts.presentation.getfacts.FactsUseCase
import com.xander.catfacts.presentation.getfacts.GetFactsViewModel
import com.xander.catfacts.presentation.showfacts.ShowFactsViewModel
import com.xander.catfacts.util.NetworkState
import com.xander.catfacts.util.constant.UrlConstants
import kotlinx.coroutines.CoroutineScope
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

    single { provideFactsUseCase(get(), get(), get()) }

    single { provideFactsRepository(get(), get(), get()) }

    single { provideLocalFactsRepository(get()) }

    single {
        get<AppDatabase>().catFactsDao()
    }
}

val viewModelModule: Module = module {

    viewModel { GetFactsViewModel(get()) }

    viewModel { (factsList: ArrayList<CatFact>) ->
        ShowFactsViewModel(
            factsList
        )
    }

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


private fun provideFactsUseCase(
    factsRepository: FactsRepository,
    localFactsRepository: LocalFactsRepository,
    networkState: NetworkState
): FactsUseCase =
    FactsUseCaseImpl(factsRepository, localFactsRepository, networkState)


private fun provideFactsRepository(
    pictureServiceApi: PictureServiceApi,
    factServiceApi: FactServiceApi,
    catFactsDao: CatFactsDao
): FactsRepository =
    FactsRepositoryImpl(pictureServiceApi, factServiceApi, catFactsDao)


private fun provideLocalFactsRepository(
    catFactsDao: CatFactsDao
): LocalFactsRepository =
    LocalFactsRepositoryImpl(catFactsDao)