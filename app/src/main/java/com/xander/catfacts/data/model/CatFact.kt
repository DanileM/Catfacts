package com.xander.catfacts.data.model

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Entity(tableName = "CatFacts")
@Parcelize
data class CatFact(@PrimaryKey val id: Int? = null,
                   @ColumnInfo(name = "picture") var picture: String = "",
                   @ColumnInfo(name = "fact") var fact: String = "") : Parcelable