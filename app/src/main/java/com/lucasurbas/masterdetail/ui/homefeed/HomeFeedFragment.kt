package com.lucasurbas.masterdetail.ui.homefeed

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.lucasurbas.masterdetail.R
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_empty.*
import kotlinx.android.synthetic.main.view_main_containers.view.*

class HomeFeedFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_empty, container, false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        title.text = getString(R.string.fragment_homefeed__title)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setupToolbar()
    }

    private fun setupToolbar() {
        val appBar = activity.containers_layout.custom_appbar
        appBar.setTitle(R.string.fragment_homefeed__title)
        appBar.setMenuRes(R.menu.homefeed_general, R.menu.homefeed_specific, R.menu.homefeed_merged)
    }

    companion object {

        fun newInstance(): HomeFeedFragment {
            return HomeFeedFragment()
        }
    }
}
