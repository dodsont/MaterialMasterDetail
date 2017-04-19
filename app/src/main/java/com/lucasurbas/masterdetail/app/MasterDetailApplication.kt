package com.lucasurbas.masterdetail.app

import android.app.Application
import android.content.Context

import com.lucasurbas.masterdetail.injection.app.ApplicationComponent
import com.lucasurbas.masterdetail.injection.app.ApplicationModule
import com.lucasurbas.masterdetail.injection.app.DaggerApplicationComponent

class MasterDetailApplication : Application() {

    private lateinit var applicationComponent: ApplicationComponent

    override fun onCreate() {
        super.onCreate()

        applicationComponent = DaggerApplicationComponent.builder()
                .applicationModule(ApplicationModule(this))
                .build()
    }

    companion object {

        fun getAppComponent(context: Context): ApplicationComponent {
            return (context.applicationContext as MasterDetailApplication).applicationComponent
        }
    }
}
