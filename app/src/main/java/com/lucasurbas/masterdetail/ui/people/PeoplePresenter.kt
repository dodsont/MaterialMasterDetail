package com.lucasurbas.masterdetail.ui.people

import android.content.Context
import android.os.Handler
import com.lucasurbas.masterdetail.R
import com.lucasurbas.masterdetail.data.Person
import java.util.*
import javax.inject.Inject

/**
 * Created by Lucas on 04/01/2017.
 */

class PeoplePresenter
@Inject
constructor(private val context: Context, private val navigator: PeopleContract.Navigator) : PeopleContract.Presenter {
    private var view: PeopleContract.View? = null

    private var peopleList: MutableList<Person>? = null
    private val names: MutableList<String>

    init {

        names = ArrayList<String>()
        names.add("Nolan Mcfetridge")
        names.add("Nick Blackford")
        names.add("Carlee Mucci")
        names.add("Tianna Henricksen")
        names.add("Julie Rathburn")
        names.add("Silvana Stiner")
        names.add("Rudolf Grate")
        names.add("Saran Seaman")
        names.add("Carol Pavao")
        names.add("Karey Shatley")
        names.add("Carlita Frye")
        names.add("Sharita Ekberg")
        names.add("Elvia Huitt")
        names.add("Kesha Liebel")
        names.add("Aleida Vincelette")
        names.add("Stormy Rossiter")
        names.add("Carolina Degner")
        names.add("Ruth Slavin")
        names.add("Delilah Hermosillo")
        names.add("Willow Haley")
    }

    override fun attachView(view: PeopleContract.View) {
        this.view = view
    }

    override fun detachView() {
        this.view = null
    }

    override fun getPeople() {
        peopleList = ArrayList<Person>()
        for (i in 0..7) {
            val person = Person(UUID.randomUUID().toString())
            person.name = randomName
            person.description = context.getString(R.string.fragment_people__lorem_ipsum)
            peopleList!!.add(person)
        }
        if (view != null) {
            view!!.showPeopleList(peopleList!!)
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
        view!!.showToast("Action clicked: " + person.name)
    }

    override fun loadMorePeople() {
        val handler = Handler()
        handler.postDelayed({
            if (view != null) {
                view!!.hideLoading()
                val person = Person(UUID.randomUUID().toString())
                person.name = randomName
                person.description = context.getString(R.string.fragment_people__lorem_ipsum)
                peopleList!!.add(0, person)
                view!!.showPeopleList(peopleList!!)
            }
        }, 2000)
    }
}
