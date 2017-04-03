package com.lucasurbas.masterdetail.ui.main

import javax.inject.Inject

class MainPresenter
@Inject
constructor(private val navigator: MainContract.Navigator) : MainContract.Presenter {

    private var view: MainContract.View? = null

    override fun attachView(view: MainContract.View) {
        this.view = view
    }

    override fun detachView() {
        this.view = null
    }

    override fun clickHomeFeed() {
        view?.highlightHomeFeed()
        view?.closeDrawer()

        navigator.goToHomeFeed()
    }

    override fun clickPeople() {
        view?.highlightPeople()
        view?.closeDrawer()

        navigator.goToPeople()
    }

    override fun clickFavorites() {
        view?.highlightFavorites()
        view?.closeDrawer()

        navigator.goToFavorites()
    }

    override fun clickMap() {
        view?.highlightMap()
        view?.closeDrawer()

        navigator.goToMap()
    }

    override fun clickSettings() {
        //            view?.highlightSettings();
        view?.closeDrawer()

        navigator.goToSettings()
    }

    override fun clickFeedback() {
        //            view?.highlightHomeFeed();
        view?.closeDrawer()

        navigator.goToFeedback()
    }
}
