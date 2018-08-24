/*
 * Developed By Shudipto Trafder
 * on 8/17/18 12:41 PM
 * Copyright (c) 2018 Shudipto Trafder.
 */

package com.iamsdt.pssd

import android.app.Application
import com.iamsdt.pssd.di.appModule
import com.iamsdt.pssd.di.dbModule
import com.iamsdt.pssd.di.vmModule
import com.iamsdt.pssd.ext.DebugLogTree
import com.iamsdt.pssd.ext.ReleaseLogTree
import com.rohitss.uceh.UCEHandler
import org.koin.android.ext.android.startKoin
import timber.log.Timber

class MyApp : Application() {

    override fun onCreate() {
        super.onCreate()

        UCEHandler.Builder(applicationContext).build()

        if (BuildConfig.DEBUG) Timber.plant(DebugLogTree())
        else Timber.plant(ReleaseLogTree())

        startKoin(this, listOf(dbModule, appModule, vmModule))
    }

}