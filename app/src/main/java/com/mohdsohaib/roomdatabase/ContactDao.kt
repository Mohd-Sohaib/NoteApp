package com.mohdsohaib.roomdatabase

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface ContactDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
     fun insert(contact: Contact)

    @Delete
     fun delete(contact: Contact)

    @Query("SELECT * FROM contact_table order by id ASC")
    fun getAllContact() : LiveData<List<Contact>>
}