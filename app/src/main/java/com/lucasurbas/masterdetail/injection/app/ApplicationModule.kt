package com.lucasurbas.masterdetail.injection.app

import android.app.Application
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Created by Lucas on 19/06/16.
 */
@Module
class ApplicationModule(private val application: Application) {

    @Provides
    @Singleton
    internal fun provideApplication(): Application {
        return application
    }

}
