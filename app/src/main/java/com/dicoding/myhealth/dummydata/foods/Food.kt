package com.dicoding.myhealth.dummydata.foods

import android.os.Parcel
import android.os.Parcelable
import androidx.versionedparcelable.VersionedParcelize

@VersionedParcelize
data class Food (
    val image: String?,
    val name: String?,
    val kalori: String?,
    val lemak: String?,
    val protein: String?
    ):Parcelable{
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString())

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(image)
        parcel.writeString(name)
        parcel.writeString(kalori)
        parcel.writeString(lemak)
        parcel.writeString(protein)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Food> {
        override fun createFromParcel(parcel: Parcel): Food {
            return Food(parcel)
        }

        override fun newArray(size: Int): Array<Food?> {
            return arrayOfNulls(size)
        }
    }
}