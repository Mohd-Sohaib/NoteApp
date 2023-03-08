package com.mohdsohaib.roomdatabase

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ContactsRVAdapter(private val context: Context, private val listerner: IContactRVAdapter) : RecyclerView.Adapter<ContactsRVAdapter.ContactViewHolder>() {

    private val allContacts = ArrayList<Contact>()

    inner class ContactViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val contactName = itemView.findViewById<TextView>(R.id.txtName)
        val contactNumber = itemView.findViewById<TextView>(R.id.txtNumber)
        val delete = itemView.findViewById<Button>(R.id.btnDelete)

    }

    @SuppressLint("SuspiciousIndentation")
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactViewHolder {
      val viewHolder = ContactViewHolder(LayoutInflater.from(context).inflate(R.layout.item_contact,parent,false))
        viewHolder.delete.setOnClickListener {
            listerner.onItemClicked(allContacts[viewHolder.adapterPosition])
        }
        return viewHolder
    }

    override fun onBindViewHolder(holder: ContactViewHolder, position: Int) {
        val currentContact = allContacts[position]
        holder.contactName.text = currentContact.name
        holder.contactNumber.text = currentContact.number.toString()
    }

    override fun getItemCount(): Int {
       return allContacts.size
    }

    fun updateContact(newList : List<Contact>){
        allContacts.clear()
        allContacts.addAll(newList)

        notifyDataSetChanged()
    }
}

interface IContactRVAdapter{
    fun onItemClicked(contact: Contact)
}