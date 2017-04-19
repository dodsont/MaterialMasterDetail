package com.lucasurbas.masterdetail.ui.people

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import com.lucasurbas.masterdetail.R
import com.lucasurbas.masterdetail.data.Person
import kotlinx.android.synthetic.main.item_view_user.view.*

class PersonView : FrameLayout {

    private var person: Person? = null
    private var onPersonClickListener: PersonView.OnPersonClickListener? = null

    interface OnPersonClickListener {

        fun onPersonClick(person: Person)

        fun onPersonActionClick(person: Person)
    }

    constructor(context: Context) : super(context) {
        init()
    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        init()
    }

    private fun init() {
        LayoutInflater.from(context).inflate(R.layout.item_view_user, this, true)
    }

    fun setUser(person: Person) {
        this.person = person
        name.text = person.name
        description.text = person.description
    }

    fun setOnPersonClickListener(onPersonClickListener: OnPersonClickListener?) {
        this.onPersonClickListener = onPersonClickListener
        if (onPersonClickListener != null) {
            person?.let { p ->
                row.setOnClickListener { onPersonClickListener.onPersonClick(p) }
                action.setOnClickListener { onPersonClickListener.onPersonActionClick(p) }
            }
        } else {
            row.setOnClickListener(null)
            action.setOnClickListener(null)
        }
    }
}
