package com.lucasurbas.masterdetail.ui.utils

interface BasePresenter<in V : BaseView> {

    fun attachView(view: V)

    fun detachView()
}
