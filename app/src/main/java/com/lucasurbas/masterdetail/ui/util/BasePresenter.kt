package com.lucasurbas.masterdetail.ui.util

interface BasePresenter<in V : BaseView> {

    fun attachView(view: V)

    fun detachView()
}
