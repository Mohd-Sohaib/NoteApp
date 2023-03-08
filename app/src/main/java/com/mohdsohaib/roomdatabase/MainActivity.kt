package com.mohdsohaib.roomdatabase

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.mohdsohaib.roomdatabase.databinding.ActivityMainBinding
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.item_contact.view.*

class MainActivity : AppCompatActivity(), IContactRVAdapter {

    lateinit var binding : ActivityMainBinding
    lateinit var viewModel: ContactViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        val adapter = ContactsRVAdapter(this,this)
        binding.recyclerView.adapter = adapter

        viewModel = ViewModelProvider(this,
            ViewModelProvider.AndroidViewModelFactory.getInstance(application))[ContactViewModel::class.java]
        viewModel.allContact.observe(this, Observer {list->
            list?.let{
                adapter.updateContact(it)
            }
        })

        binding.edtButton.setOnClickListener {
            val contactName = edtName.text.toString()
            val contactNumber = edtNumber.text.toString()
            if (contactName.isNotEmpty() && contactNumber.isNotEmpty()){
                viewModel.insertContact(Contact(contactName,contactNumber.toDouble()))
                Toast.makeText(this,"$contactName , $contactNumber ADDED",Toast.LENGTH_SHORT).show()
            }
            edtName.text.clear()
            edtNumber.text.clear()
        }
    }

    override fun onItemClicked(contact: Contact) {
        viewModel.deleteContact(contact)
        Toast.makeText(this,"${contact.name} , ${contact.number} Deleted",Toast.LENGTH_SHORT).show()
    }
}