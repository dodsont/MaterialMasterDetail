package com.lucasurbas.masterdetail.ui.main

import android.os.Bundle
import android.support.design.widget.NavigationView
import android.support.v4.view.GravityCompat
import android.support.v4.view.ViewCompat
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import com.lucasurbas.masterdetail.R
import com.lucasurbas.masterdetail.injection.app.ApplicationComponent
import com.lucasurbas.masterdetail.injection.main.DaggerMainComponent
import com.lucasurbas.masterdetail.injection.main.MainComponent
import com.lucasurbas.masterdetail.injection.main.MainModule
import com.lucasurbas.masterdetail.ui.util.BaseActivity
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.view_main_containers.view.*
import javax.inject.Inject

class MainActivity : BaseActivity(), MainContract.View, NavigationView.OnNavigationItemSelectedListener {

    @Inject lateinit var presenter: MainContract.Presenter
    @Inject lateinit var navigator: MainContract.Navigator
        internal set

    var mainComponent: MainComponent? = null
        private set

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (insets != null && nav_side != null) {
            ViewCompat.setOnApplyWindowInsetsListener(insets) { v, listener ->
                (insets.layoutParams as ViewGroup.MarginLayoutParams).topMargin = listener.systemWindowInsetTop
                (insets.layoutParams as ViewGroup.MarginLayoutParams).bottomMargin = listener.systemWindowInsetBottom
                insets.requestLayout()
                (nav_side.layoutParams as ViewGroup.MarginLayoutParams).topMargin = -listener.systemWindowInsetTop
                nav_side.requestLayout()
                listener.consumeSystemWindowInsets()
            }
            nav_side.setNavigationItemSelectedListener(this)
        }

        nav.setNavigationItemSelectedListener(this)
        containers_layout.custom_appbar.setOnNavigationClickListener(View.OnClickListener { toggleDrawer() })

        presenter.attachView(this)
        if (savedInstanceState == null) {
            presenter.clickPeople()
        }
    }

    override fun setupActivityComponent(applicationComponent: ApplicationComponent) {
        mainComponent = DaggerMainComponent.builder()
                .applicationComponent(applicationComponent)
                .mainModule(MainModule(this))
                .build()

        mainComponent!!.inject(this)
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.detachView()
    }

    override fun closeDrawer() {
        if (drawer != null && drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.postDelayed({ drawer.closeDrawer(GravityCompat.START) }, 100)
        }
    }

    override fun openDrawer() {
        if (drawer != null && !drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.openDrawer(GravityCompat.START)
        }
    }

    fun toggleDrawer() {
        if (drawer != null && drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START)
        } else if (drawer != null && !drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.openDrawer(GravityCompat.START)
        }
    }

    override fun highlightHomeFeed() {
        nav.setCheckedItem(R.id.menu_main_nav__home_feed)
        if (nav_side != null) {
            nav_side.setCheckedItem(R.id.menu_main_nav__home_feed)
        }
    }

    override fun highlightPeople() {
        nav.setCheckedItem(R.id.menu_main_nav__people)
        if (nav_side != null) {
            nav_side.setCheckedItem(R.id.menu_main_nav__people)
        }
    }

    override fun highlightFavorites() {
        nav.setCheckedItem(R.id.menu_main_nav__favorites)
        if (nav_side != null) {
            nav_side.setCheckedItem(R.id.menu_main_nav__favorites)
        }
    }

    override fun highlightMap() {
        nav.setCheckedItem(R.id.menu_main_nav__map)
        if (nav_side != null) {
            nav_side.setCheckedItem(R.id.menu_main_nav__map)
        }
    }

    override fun highlightSettings() {
        //empty
    }

    override fun highlightFeedback() {
        //empty
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.menu_main_nav__people -> presenter.clickPeople()

            R.id.menu_main_nav__home_feed -> presenter.clickHomeFeed()

            R.id.menu_main_nav__favorites -> presenter.clickFavorites()

            R.id.menu_main_nav__map -> presenter.clickMap()

            R.id.menu_main_nav__settings -> presenter.clickSettings()

            R.id.menu_main_nav__feedback -> presenter.clickFeedback()

            else -> return false
        }
        return true
    }

    override fun onBackPressed() {
        if (drawer != null && drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START)
        } else if (!navigator.onBackPressed()) {
            super.onBackPressed()
        }
    }

}
