package com.lucasurbas.masterdetail.ui.main

import android.support.v4.app.FragmentTransaction
import com.lucasurbas.masterdetail.R
import com.lucasurbas.masterdetail.data.Person
import com.lucasurbas.masterdetail.ui.favorites.FavoritesFragment
import com.lucasurbas.masterdetail.ui.homefeed.HomeFeedFragment
import com.lucasurbas.masterdetail.ui.map.MapFragment
import com.lucasurbas.masterdetail.ui.people.PeopleFragment
import com.lucasurbas.masterdetail.ui.persondetails.PersonDetailsFragment
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.view_main_containers.view.*
import javax.inject.Inject

/**
 * Created by Lucas on 02/01/2017.
 */

class MainNavigator
@Inject
constructor(private val mainActivity: MainActivity) : MainContract.Navigator {

    enum class State {
        SINGLE_COLUMN_MASTER, SINGLE_COLUMN_DETAILS, TWO_COLUMNS_EMPTY, TWO_COLUMNS_WITH_DETAILS
    }

    private fun clearDetails(): Boolean {
        val details = mainActivity.supportFragmentManager.findFragmentByTag(TAG_DETAILS)
        if (details != null) {
            mainActivity.supportFragmentManager
                    .beginTransaction()
                    .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                    .remove(details)
                    .commitNow()
            return true
        }
        return false
    }

    private fun clearMaster() {
        val master = mainActivity.supportFragmentManager.findFragmentByTag(TAG_MASTER)
        if (master != null) {
            mainActivity.supportFragmentManager.beginTransaction().remove(master).commitNow()
        }
    }

    override fun goToHomeFeed() {
        clearDetails()
        mainActivity.containers_layout.custom_appbar.setState(State.SINGLE_COLUMN_MASTER)
        mainActivity.containers_layout.state = State.SINGLE_COLUMN_MASTER
        val fragment = HomeFeedFragment.newInstance()
        mainActivity.supportFragmentManager.beginTransaction().replace(R.id.frame_master, fragment, TAG_MASTER).commitNow()
    }

    override fun goToPeople() {
        clearDetails()
        mainActivity.containers_layout.custom_appbar.setState(State.TWO_COLUMNS_EMPTY)
        mainActivity.containers_layout.state = State.TWO_COLUMNS_EMPTY
        val master = PeopleFragment.newInstance()
        mainActivity.supportFragmentManager.beginTransaction().replace(R.id.frame_master, master, TAG_MASTER).commitNow()
    }

    override fun goToFavorites() {
        clearDetails()
        mainActivity.containers_layout.custom_appbar.setState(State.SINGLE_COLUMN_MASTER)
        mainActivity.containers_layout.state = State.SINGLE_COLUMN_MASTER
        val fragment = FavoritesFragment.newInstance()
        mainActivity.supportFragmentManager.beginTransaction().replace(R.id.frame_master, fragment, TAG_MASTER).commitNow()
    }

    override fun goToMap() {
        clearMaster()
        mainActivity.containers_layout.custom_appbar.setState(State.SINGLE_COLUMN_DETAILS)
        mainActivity.containers_layout.state = State.SINGLE_COLUMN_DETAILS
        val fragment = MapFragment.newInstance()
        mainActivity.supportFragmentManager.beginTransaction().replace(R.id.frame_details, fragment, TAG_DETAILS).commitNow()
    }

    override fun goToPersonDetails(person: Person) {
        mainActivity.containers_layout.custom_appbar.setState(State.TWO_COLUMNS_WITH_DETAILS)
        mainActivity.containers_layout.state = State.TWO_COLUMNS_WITH_DETAILS
        val fragment = PersonDetailsFragment.newInstance(person)
        mainActivity.supportFragmentManager
                .beginTransaction()
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                .replace(R.id.frame_details, fragment, TAG_DETAILS)
                .commitNow()
    }

    override fun goToSettings() {
        //start new activity
    }

    override fun goToFeedback() {
        //start new activity
    }

    override fun onBackPressed(): Boolean {
        val state = mainActivity.containers_layout.state
        if (state == State.TWO_COLUMNS_WITH_DETAILS && !mainActivity.containers_layout.hasTwoColumns()) {
            if (clearDetails()) {
                mainActivity.containers_layout.state = State.TWO_COLUMNS_EMPTY
                return true
            }
        }
        return false
    }

    companion object {

        private val TAG_DETAILS = "tag_details"
        private val TAG_MASTER = "tag_master"
    }
}
