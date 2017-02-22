package com.lucasurbas.masterdetail.ui.people

import android.support.v7.util.DiffUtil
import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import com.lucasurbas.masterdetail.data.Person
import java.util.*

/**
 * Created by Lucas on 04/01/2017.
 */

class PeopleAdapter : RecyclerView.Adapter<PeopleAdapter.PersonViewHolder>() {

    var selectedItemId: String? = null
    private val peopleList = ArrayList<Person>()
    private var onPersonClickListener: PersonView.OnPersonClickListener? = null

    class PersonViewHolder(var personView: PersonView) : RecyclerView.ViewHolder(personView)

    fun setOnPersonClickListener(onPersonClickListener: PersonView.OnPersonClickListener) {
        this.onPersonClickListener = onPersonClickListener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PeopleAdapter.PersonViewHolder {
        return PersonViewHolder(PersonView(parent.context))
    }

    override fun onBindViewHolder(holder: PersonViewHolder, position: Int) {
        holder.personView.isSelected = selectedItemId == peopleList[position].id

        holder.personView.setUser(peopleList[position])
        holder.personView.setOnPersonClickListener(object : PersonView.OnPersonClickListener {
            override fun onPersonClick(person: Person) {
                selectedItemId = peopleList[holder.adapterPosition].id
                notifyDataSetChanged()

                onPersonClickListener?.onPersonClick(person)
            }

            override fun onPersonActionClick(person: Person) {
                onPersonClickListener?.onPersonActionClick(person)
            }
        })
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
