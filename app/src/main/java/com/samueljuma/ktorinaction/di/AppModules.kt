package com.samueljuma.ktorinaction.di

import com.samueljuma.ktorinaction.network.CommentsAPIService
import com.samueljuma.ktorinaction.repository.CommentsRepository
import com.samueljuma.ktorinaction.repository.CommentsRepositoryImpl
import com.samueljuma.ktorinaction.viewmodel.CommentsViewModel
import kotlinx.coroutines.Dispatchers
import org.koin.dsl.module

val appModules = module {
    //API Service dependency
    single <CommentsAPIService> { CommentsAPIService() }
    //coroutine dispatcher dependency
    single { Dispatchers.IO }

    //repository dependency
    single <CommentsRepository>{
        CommentsRepositoryImpl(
            apiService = get(),
            dispatcher = get()
        )
    }
    //viewmodel dependency
    single { CommentsViewModel(repository = get()) }

}
