package com.samueljuma.ktorinaction

import android.app.Application
import com.samueljuma.ktorinaction.di.appModules
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class KtorInActionApplication: Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@KtorInActionApplication)
            modules(appModules)
        }

    }
}