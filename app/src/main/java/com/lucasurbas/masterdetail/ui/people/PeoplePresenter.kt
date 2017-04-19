package com.lucasurbas.masterdetail.ui.people

import android.content.Context
import android.os.Handler
import com.lucasurbas.masterdetail.R
import com.lucasurbas.masterdetail.data.Person
import java.util.*
import javax.inject.Inject

class PeoplePresenter
@Inject
constructor(private val context: Context, private val navigator: PeopleContract.Navigator) : PeopleContract.Presenter {
    private var view: PeopleContract.View? = null

    private var peopleList: MutableList<Person>? = null
    private val names = listOf("Nolan Mcfetridge",
            "Nick Blackford",
            "Carlee Mucci",
            "Tianna Henricksen",
            "Julie Rathburn",
            "Silvana Stiner",
            "Rudolf Grate",
            "Saran Seaman",
            "Carol Pavao",
            "Karey Shatley",
            "Carlita Frye",
            "Sharita Ekberg",
            "Elvia Huitt",
            "Kesha Liebel",
            "Aleida Vincelette",
            "Stormy Rossiter",
            "Carolina Degner",
            "Ruth Slavin",
            "Delilah Hermosillo",
            "Willow Haley")

    override fun attachView(view: PeopleContract.View) {
        this.view = view
    }

    override fun detachView() {
        this.view = null
    }

    override fun getPeople() {
        peopleList = ArrayList<Person>()

        peopleList?.let { list ->
            for (i in 0..7) {
                val person = Person(id = UUID.randomUUID().toString(),
                        name = randomName,
                        description = context.getString(R.string.fragment_people__lorem_ipsum))
                peopleList?.add(person)
            }
            view?.showPeopleList(list)
        }
    }

    private val randomName: String
        get() {
            val r = Random()
            return names[r.nextInt(names.size)]
        }

    override fun clickPerson(person: Person) {
        navigator.goToPersonDetails(person)
    }

    override fun clickPersonAction(person: Person) {
        view?.showToast("Action clicked: " + person.name)
    }

    override fun loadMorePeople() {
        val handler = Handler()
        handler.postDelayed({
            view?.hideLoading()

            peopleList?.let { list ->
                val person = Person(id = UUID.randomUUID().toString(),
                        name = randomName,
                        description = context.getString(R.string.fragment_people__lorem_ipsum))

                list.add(0, person)
                view?.showPeopleList(list)
            }
        }, 2000)
    }
}
