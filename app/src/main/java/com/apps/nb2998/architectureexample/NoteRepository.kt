package com.apps.nb2998.architectureexample

import android.app.Application
import android.arch.lifecycle.LiveData
import android.os.AsyncTask

class NoteRepository(application: Application) {

    init {
        val noteDatabase = NoteDatabase.getInstance(application)
        noteDao = noteDatabase.noteDao()
        notesList = noteDao.getAllNotes()
    }

    fun insert(note: Note) {
        InsertAsyncTask().execute(note)
    }

    fun update(note: Note) {
        UpdateAsyncTask().execute(note)
    }

    fun delete(note: Note) {
        DeleteAsyncTask().execute(note)
    }

    fun deleteAllNotes() {
        DeleteAllNotesAsyncTask().execute()
    }

    fun getAllNotes(): LiveData<List<Note>> {
        return notesList
    }

    companion object {
        lateinit var noteDao: NoteDao
        lateinit var notesList: LiveData<List<Note>>

        class InsertAsyncTask : AsyncTask<Note, Void, Void>() {
            private var noteDaoAsync: NoteDao = noteDao

            override fun doInBackground(vararg params: Note): Void? {
                noteDaoAsync.insert(params[0])
                return null
            }

        }

        class UpdateAsyncTask : AsyncTask<Note, Void, Void>() {
            private var noteDaoAsync: NoteDao = noteDao

            override fun doInBackground(vararg params: Note): Void? {
                noteDaoAsync.update(params[0])
                return null
            }
        }

        class DeleteAsyncTask : AsyncTask<Note, Void, Void>() {
            private var noteDaoAsync: NoteDao = noteDao

            override fun doInBackground(vararg params: Note): Void? {
                noteDaoAsync.delete(params[0])
                return null
            }

        }

        class DeleteAllNotesAsyncTask : AsyncTask<Note, Void, Void>() {
            private var noteDaoAsync: NoteDao = noteDao

            override fun doInBackground(vararg params: Note): Void? {
                noteDaoAsync.deleteAllNotes()
                return null
            }

        }
    }
}