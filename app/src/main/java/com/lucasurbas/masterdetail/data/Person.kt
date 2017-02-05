package com.lucasurbas.masterdetail.data

import android.os.Parcel
import android.os.Parcelable
import paperparcel.PaperParcel

/**
 * Created by Lucas on 04/01/2017.
 */

@PaperParcel
data class Person(
        val id: String,
        val name: String,
        val description: String) : Parcelable {
    companion object {
        @JvmField val CREATOR = PaperParcelPerson.CREATOR
    }

    override fun describeContents() = 0

    override fun writeToParcel(dest: Parcel, flags: Int) {
        PaperParcelPerson.writeToParcel(this, dest, flags)
    }
}