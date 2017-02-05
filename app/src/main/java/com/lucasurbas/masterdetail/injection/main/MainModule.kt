package com.lucasurbas.masterdetail.injection.main

import android.content.Context

import com.lucasurbas.masterdetail.injection.ActivityScope
import com.lucasurbas.masterdetail.ui.main.MainActivity
import com.lucasurbas.masterdetail.ui.main.MainContract
import com.lucasurbas.masterdetail.ui.main.MainNavigator
import com.lucasurbas.masterdetail.ui.main.MainPresenter

import dagger.Module
import dagger.Provides

/**
 * Created by Lucas on 12/06/16.
 */
@Module
class MainModule(private val mainActivity: MainActivity) {

    @Provides
    @ActivityScope
    internal fun provideMainNavigation(navigation: MainNavigator): MainContract.Navigator {
        return navigation
    }

    @Provides
    @ActivityScope
    internal fun provideMainPresenter(presenter: MainPresenter): MainContract.Presenter {
        return presenter
    }

    @Provides
    @ActivityScope
    internal fun provideContext(): Context {
        return mainActivity
    }

    @Provides
    @ActivityScope
    internal fun provideMainActivity(): MainActivity {
        return mainActivity
    }
}
