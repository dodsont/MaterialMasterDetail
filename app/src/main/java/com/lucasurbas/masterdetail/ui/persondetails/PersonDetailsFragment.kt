package com.lucasurbas.masterdetail.ui.persondetails

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.lucasurbas.masterdetail.R
import com.lucasurbas.masterdetail.data.Person
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_person_details.*

class PersonDetailsFragment : Fragment() {

    private var person: Person? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_person_details, container, false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        person = arguments.getParcelable<Person>(ARG_PERSON)

        setupToolbar()
        person?.let {
            setPerson(it)
        }
    }

    private fun setupToolbar() {
        toolbar.inflateMenu(R.menu.person_details)

        if (!activity.containers_layout.hasTwoColumns()) {
            toolbar.setNavigationIcon(R.drawable.ic_back_24dp)
            toolbar.setNavigationOnClickListener { activity.onBackPressed() }
        }
    }

    private fun setPerson(person: Person) {
        toolbar.title = person.name
        description.text = person.description
    }

    companion object {

        private val ARG_PERSON = "ARG_PERSON"

        fun newInstance(person: Person): PersonDetailsFragment {
            val fragment = PersonDetailsFragment()
            val bundle = Bundle()
            bundle.putParcelable(ARG_PERSON, person)
            fragment.arguments = bundle
            return fragment
        }
    }
}
