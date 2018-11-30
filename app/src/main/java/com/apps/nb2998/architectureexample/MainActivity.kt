package com.apps.nb2998.architectureexample

import android.app.Activity
import android.arch.lifecycle.LiveData
import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    lateinit var noteViewModel: NoteViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

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

        addNoteFab.setOnClickListener {
            val intent = Intent(this@MainActivity, AddNoteActivity::class.java)
            startActivityForResult(intent, ADD_NOTE_CODE)
        }
    }

    companion object {
        const val ADD_NOTE_CODE = 123
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if(requestCode == ADD_NOTE_CODE && resultCode == Activity.RESULT_OK) {
            val newNote = Note(data!!.getStringExtra(getString(R.string.intentTitle)), data.getStringExtra(getString(R.string.intentDesc)), data.getIntExtra(getString(R.string.intentPriority), 0))
            noteViewModel.insert(newNote)
        }
    }
}
