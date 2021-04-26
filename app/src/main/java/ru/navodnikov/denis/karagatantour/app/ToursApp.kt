package ru.navodnikov.denis.karagatantour.app

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level
import ru.navodnikov.denis.karagatantour.BuildConfig
import ru.navodnikov.denis.karagatantour.di.appModule
import ru.navodnikov.denis.karagatantour.di.dataModule
import ru.navodnikov.denis.karagatantour.di.domainModule
import ru.navodnikov.denis.karagatantour.di.networkingModule

class ToursApp: Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin{
            if(BuildConfig.DEBUG) {
                androidLogger(Level.ERROR)
            } else {
                androidLogger()
            }
            androidContext(this@ToursApp)
            modules(listOf(appModule, dataModule, domainModule, networkingModule))
        }
    }
}