package com.lucasurbas.masterdetail.injection.main

import com.lucasurbas.masterdetail.injection.ActivityScope
import com.lucasurbas.masterdetail.injection.app.ApplicationComponent
import com.lucasurbas.masterdetail.injection.people.PeopleComponent
import com.lucasurbas.masterdetail.injection.people.PeopleModule
import com.lucasurbas.masterdetail.ui.main.MainActivity

import dagger.Component

/**
 * Created by Lucas on 12/06/16.
 */
@ActivityScope
@Component(dependencies = arrayOf(ApplicationComponent::class), modules = arrayOf(MainModule::class))
interface MainComponent {

    fun inject(activity: MainActivity)

    operator fun plus(peopleModule: PeopleModule): PeopleComponent

}
