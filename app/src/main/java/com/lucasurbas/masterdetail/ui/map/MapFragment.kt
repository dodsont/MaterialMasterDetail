package com.lucasurbas.masterdetail.ui.map

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.lucasurbas.masterdetail.R
import com.lucasurbas.masterdetail.ui.main.MainActivity
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_map.*
import kotlinx.android.synthetic.main.view_main_containers.view.*

/**
 * Created by Lucas on 04/01/2017.
 */

class MapFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater!!.inflate(R.layout.fragment_map, container, false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        title.text = getString(R.string.fragment_map__title)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setupToolbar()
    }

    private fun setupToolbar() {
        val appBar = (activity as MainActivity).containers_layout.custom_appbar
        appBar.setTitle("")
        appBar.clearMenu()

        if (!appBar.hasGeneralToolbar()) {
            main_icon.setImageResource(R.drawable.ic_menu_24dp)
            main_icon.setOnClickListener { (activity as MainActivity).toggleDrawer() }
        }
    }

    companion object {

        fun newInstance(): MapFragment {
            val fragment = MapFragment()
            val bundle = Bundle()
            fragment.arguments = bundle
            return fragment
        }
    }
}
