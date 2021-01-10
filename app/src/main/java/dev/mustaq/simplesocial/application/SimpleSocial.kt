package dev.mustaq.simplesocial.application

import android.app.Application
import dev.mustaq.simplesocial.di.AppModules
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin


/**
Created by Mustaq Sameer on 10/1/21
 **/
class SimpleSocial : Application() {

    override fun onCreate() {
        super.onCreate()
        configKoin()
    }

    private fun configKoin() {
        startKoin {
            androidContext(this@SimpleSocial)
            modules(AppModules.appModules())
        }
    }
}