package com.example.noteapp.Model

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity
data class Note(
    @PrimaryKey(autoGenerate = true)
    var id:Int,
    @ColumnInfo
    var title:String,
    @ColumnInfo
    var description:String,
    @ColumnInfo
    var importance:Int
):Parcelable{

}