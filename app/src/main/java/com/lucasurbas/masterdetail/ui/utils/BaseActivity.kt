package com.lucasurbas.masterdetail.ui.utils

import android.os.Bundle
import android.support.v7.app.AppCompatActivity

import com.lucasurbas.masterdetail.app.MasterDetailApplication
import com.lucasurbas.masterdetail.injection.app.ApplicationComponent

abstract class BaseActivity : AppCompatActivity() {

    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupActivityComponent(MasterDetailApplication.getAppComponent(this))
    }

    protected abstract fun setupActivityComponent(applicationComponent: ApplicationComponent)
}
