package com.lucasurbas.masterdetail.injection.people

import com.lucasurbas.masterdetail.injection.FragmentScope
import com.lucasurbas.masterdetail.ui.people.PeopleFragment

import dagger.Subcomponent
@FragmentScope
@Subcomponent(modules = arrayOf(PeopleModule::class))
interface PeopleComponent {

    fun inject(fragment: PeopleFragment)
}
