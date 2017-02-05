package com.lucasurbas.masterdetail.injection.app

import android.app.Application
import dagger.Component
import javax.inject.Singleton

/**
 * Created by Lucas on 19/06/16.
 */

@Singleton
@Component(modules = arrayOf(ApplicationModule::class))
interface ApplicationComponent {

    val application: Application

}
