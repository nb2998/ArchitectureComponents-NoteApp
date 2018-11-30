package com.apps.nb2998.architectureexample

import android.app.Activity
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_add_note.*

class AddNoteActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_note)

        supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_closenote)
        title = "Add note"
        btnSave.setOnClickListener { saveNote() }
    }

    private fun saveNote() {
        val title = etTitle.text.toString()
        val description = etDescription.text.toString()
        val priority = numPickerPriority.value

        if (title.trim().isEmpty() || description.trim().isEmpty()) {
            Toast.makeText(this@AddNoteActivity, "Empty fields", Toast.LENGTH_SHORT)
            return
        }

        val intent = Intent()
        intent.putExtra(getString(R.string.intentTitle), title)
        intent.putExtra(getString(R.string.intentDesc), description)
        intent.putExtra(getString(R.string.intentPriority), priority)

        setResult(Activity.RESULT_OK, intent)
        finish()
    }
}
