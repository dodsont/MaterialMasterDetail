package com.lucasurbas.masterdetail.injection.app

import android.app.Application
import dagger.Component
import javax.inject.Singleton


@Singleton
@Component(modules = arrayOf(ApplicationModule::class))
interface ApplicationComponent {

    val application: Application

}
