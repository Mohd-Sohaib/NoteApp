package com.mohdsohaib.roomdatabase

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "contact_table")
data class Contact(
    @ColumnInfo(name = "contact_name") var name : String,
    @ColumnInfo(name = "contact_number") var number : Double,){
    @PrimaryKey(autoGenerate = true) var id: Int = 0
}
