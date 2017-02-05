package com.lucasurbas.masterdetail.data

import android.os.Parcel
import android.os.Parcelable

/**
 * Created by Lucas on 04/01/2017.
 */

class Person : Parcelable {

    var id: String? = null
        private set
    var name: String? = null
    var description: String? = null

    constructor(id: String) {
        this.id = id
    }

    protected constructor(`in`: Parcel) {
        id = `in`.readString()
        name = `in`.readString()
        description = `in`.readString()
    }

    override fun equals(o: Any?): Boolean {
        if (this === o) return true
        if (o == null || javaClass != o.javaClass) return false

        val person = o as Person?

        return if (id != null) id == person!!.id else person!!.id == null

    }

    override fun hashCode(): Int {
        return if (id != null) id!!.hashCode() else 0
    }

    override fun describeContents(): Int {
        return 0
    }

    override fun writeToParcel(parcel: Parcel, i: Int) {
        parcel.writeString(id)
        parcel.writeString(name)
        parcel.writeString(description)
    }

    companion object {

        val CREATOR: Parcelable.Creator<Person> = object : Parcelable.Creator<Person> {
            override fun createFromParcel(`in`: Parcel): Person {
                return Person(`in`)
            }

            override fun newArray(size: Int): Array<Person?> {
                return arrayOfNulls(size)
            }
        }
    }
}
