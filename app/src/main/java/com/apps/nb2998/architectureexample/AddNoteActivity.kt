package com.apps.nb2998.architectureexample

import android.support.v7.app.AppCompatActivity
import android.os.Bundle

class AddNoteActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_note)

        supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_closenote)
        title = "Add note"
    }

    // TODO Save note
}
