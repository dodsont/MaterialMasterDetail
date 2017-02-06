package com.lucasurbas.masterdetail.ui.people

import android.support.v7.util.DiffUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.lucasurbas.masterdetail.R
import com.lucasurbas.masterdetail.data.Person
import java.util.*

/**
 * Created by Lucas on 04/01/2017.
 */

class PeopleAdapter : RecyclerView.Adapter<PeopleAdapter.PersonViewHolder>() {

    private val peopleList: MutableList<Person>
    private var onPersonClickListener: PersonView.OnPersonClickListener? = null


    class PersonViewHolder(var personView: PersonView) : RecyclerView.ViewHolder(personView)

    init {
        this.peopleList = ArrayList<Person>()
    }

    fun setOnPersonClickListener(onPersonClickListener: PersonView.OnPersonClickListener) {
        this.onPersonClickListener = onPersonClickListener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PeopleAdapter.PersonViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_view_user, parent, false) as PersonView
        return PersonViewHolder(view)
    }

    override fun onBindViewHolder(holder: PersonViewHolder, position: Int) {
        holder.personView.setUser(peopleList[position])
        holder.personView.setonPersonClickListener(onPersonClickListener)
    }

    fun setPeopleList(peopleList: List<Person>) {
        val diffResult = DiffUtil.calculateDiff(PeopleListDiffCallback(this.peopleList, peopleList))
        this.peopleList.clear()
        this.peopleList.addAll(peopleList)
        diffResult.dispatchUpdatesTo(this)
    }

    override fun getItemCount(): Int {
        return peopleList.size
    }
}