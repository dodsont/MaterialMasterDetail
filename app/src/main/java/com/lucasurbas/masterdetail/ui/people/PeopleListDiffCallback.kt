package com.lucasurbas.masterdetail.ui.people

import android.support.v7.util.DiffUtil

import com.lucasurbas.masterdetail.data.Person

/**
 * Created by Lucas on 04/01/2017.
 */

class PeopleListDiffCallback(private val oldList: List<Person>?, private val newList: List<Person>?) : DiffUtil.Callback() {

    override fun getOldListSize(): Int {
        return oldList?.size ?: 0
    }

    override fun getNewListSize(): Int {
        return newList?.size ?: 0
    }

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return newList!![newItemPosition] === oldList!![oldItemPosition]
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return newList!![newItemPosition] == oldList!![oldItemPosition]
    }
}
