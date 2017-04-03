package com.lucasurbas.masterdetail.ui.people

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.lucasurbas.masterdetail.R
import com.lucasurbas.masterdetail.data.Person
import com.lucasurbas.masterdetail.injection.people.PeopleModule
import com.lucasurbas.masterdetail.ui.main.MainActivity
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_people.*
import kotlinx.android.synthetic.main.view_main_containers.view.*
import javax.inject.Inject

class PeopleFragment : Fragment(), PeopleContract.View {

    @Inject lateinit var presenter: PeopleContract.Presenter

    private var adapter: PeopleAdapter? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_people, container, false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setupToolbar()
        setupRecyclerView()
        setupSwipeRefresh()

        if (savedInstanceState != null) {
            adapter?.selectedItemId = savedInstanceState.getString(STATE_SELECTED_ITEM_ID)
        }

        inject()
        presenter.attachView(this)
        presenter.getPeople()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.putString(STATE_SELECTED_ITEM_ID, adapter?.selectedItemId)

        super.onSaveInstanceState(outState)
    }

    private fun setupRecyclerView() {
        recycler_view.setHasFixedSize(true)

        val layoutManager = LinearLayoutManager(activity)
        recycler_view.layoutManager = layoutManager
        adapter = PeopleAdapter()
        adapter?.setOnPersonClickListener(object : PersonView.OnPersonClickListener {
            override fun onPersonClick(person: Person) {
                presenter.clickPerson(person)
            }

            override fun onPersonActionClick(person: Person) {
                presenter.clickPersonAction(person)
            }
        })
        recycler_view.adapter = adapter
    }

    private fun setupToolbar() {
        val appBar = (activity as MainActivity).containers_layout.custom_appbar
        appBar.setTitle(getString(R.string.fragment_people__title))
        appBar.setMenuRes(R.menu.people_general, R.menu.people_specific, R.menu.people_merged)
    }

    private fun setupSwipeRefresh() {
        swipe_refresh.setOnRefreshListener { presenter.loadMorePeople() }
    }

    private fun inject() {
        (activity as MainActivity).mainComponent.plus(PeopleModule()).inject(this)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        presenter.detachView()
        //swipeRefreshLayout bug
        if (swipe_refresh != null) {
            swipe_refresh.isRefreshing = false
            swipe_refresh.destroyDrawingCache()
            swipe_refresh.clearAnimation()
        }
    }

    override fun showLoading() {
        swipe_refresh.isRefreshing = true
    }

    override fun hideLoading() {
        swipe_refresh.isRefreshing = false
    }

    override fun showPeopleList(peopleList: List<Person>) {
        adapter?.setPeopleList(peopleList)
        recycler_view.scrollToPosition(0)
    }

    override fun showToast(message: String) {
        Toast.makeText(activity, message, Toast.LENGTH_SHORT).show()
    }

    companion object {

        private val STATE_SELECTED_ITEM_ID = "state_selected_item_id"

        fun newInstance(): PeopleFragment {
            val fragment = PeopleFragment()
            val bundle = Bundle()
            fragment.arguments = bundle
            return fragment
        }
    }
}
