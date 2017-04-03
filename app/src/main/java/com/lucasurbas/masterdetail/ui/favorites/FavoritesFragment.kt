package com.lucasurbas.masterdetail.ui.favorites

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.lucasurbas.masterdetail.R
import com.lucasurbas.masterdetail.ui.main.MainActivity
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_empty.*
import kotlinx.android.synthetic.main.view_main_containers.view.*

class FavoritesFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_empty, container, false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        title.text = getString(R.string.fragment_favorites__title)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setupToolbar()
    }

    private fun setupToolbar() {
        val appBar = (activity as MainActivity).containers_layout.custom_appbar
        appBar.setTitle(getString(R.string.fragment_favorites__title))
        appBar.setMenuRes(R.menu.favorites_general, R.menu.favorites_specific, R.menu.favorites_merged)
    }

    companion object {

        fun newInstance(): FavoritesFragment {
            val fragment = FavoritesFragment()
            val bundle = Bundle()
            fragment.arguments = bundle
            return fragment
        }
    }
}
