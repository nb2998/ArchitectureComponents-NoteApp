package com.apps.nb2998.architectureexample

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    lateinit var noteViewModel: NoteViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val adapter = NoteAdapter()

        recView.layoutManager = LinearLayoutManager(this)
        recView.adapter = adapter
        recView.setHasFixedSize(true)
        noteViewModel = ViewModelProviders.of(this).get(NoteViewModel::class.java)

        noteViewModel.getAllNotes().observe(this, Observer<MutableList<Note>>{
            // onChanged - update recycler view here
            Toast.makeText(applicationContext, "on Changed", Toast.LENGTH_SHORT).show()

            adapter.setNoteList(it!!)
        })
    }
}
