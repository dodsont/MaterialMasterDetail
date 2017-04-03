package com.lucasurbas.masterdetail.ui.util

/**
 * Created by Lucas on 12/06/16.
 */
interface BasePresenter<in V : BaseView> {

    fun attachView(view: V)

    fun detachView()
}
