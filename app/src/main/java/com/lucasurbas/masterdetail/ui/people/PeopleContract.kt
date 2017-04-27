package com.lucasurbas.masterdetail.ui.people

import com.lucasurbas.masterdetail.data.Person
import com.lucasurbas.masterdetail.ui.utils.BaseNavigator
import com.lucasurbas.masterdetail.ui.utils.BasePresenter
import com.lucasurbas.masterdetail.ui.utils.BaseView

interface PeopleContract {

    interface Navigator : BaseNavigator {

        fun goToPersonDetails(person: Person)
    }

    interface View : BaseView {

        fun showLoading()

        fun hideLoading()

        fun showPeopleList(peopleList: List<Person>)

        fun showToast(message: String)
    }

    interface Presenter : BasePresenter<PeopleContract.View> {

        fun getPeople()

        fun clickPerson(person: Person)

        fun clickPersonAction(person: Person)

        fun loadMorePeople()
    }
}
