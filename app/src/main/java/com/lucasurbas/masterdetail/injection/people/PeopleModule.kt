package com.lucasurbas.masterdetail.injection.people

import com.lucasurbas.masterdetail.injection.FragmentScope
import com.lucasurbas.masterdetail.ui.people.PeopleContract
import com.lucasurbas.masterdetail.ui.people.PeopleNavigator
import com.lucasurbas.masterdetail.ui.people.PeoplePresenter

import dagger.Module
import dagger.Provides
@Module
class PeopleModule {

    @Provides
    internal fun providePeopleNavigator(navigator: PeopleNavigator): PeopleContract.Navigator {
        return navigator
    }

    @Provides
    @FragmentScope
    internal fun providePeoplePresenter(presenter: PeoplePresenter): PeopleContract.Presenter {
        return presenter
    }
}
