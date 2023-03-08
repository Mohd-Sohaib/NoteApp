package com.mohdsohaib.roomdatabase

import androidx.lifecycle.LiveData

class ContactRepository( private val contactDao: ContactDao) {

    val allContact : LiveData<List<Contact>> = contactDao.getAllContact()

    suspend fun insert(contact: Contact){
        contactDao.insert(contact)
    }

    suspend fun delete(contact: Contact){
        contactDao.delete(contact)
    }
}