package com.apps.nb2998.architectureexample

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.LiveData

class NoteViewModel(application: Application) : AndroidViewModel(application) {
    var repository: NoteRepository
    var listOfNotes: LiveData<MutableList<Note>>

    init {
        repository = NoteRepository(application)
        listOfNotes = repository.getAllNotes()
    }

    fun insert(note: Note) {
        repository.insert(note)
    }

    fun update(note: Note) {
        repository.update(note)
    }

    fun delete(note: Note) {
        repository.delete(note)
    }

    fun deleteAllNotes() {
        repository.deleteAllNotes()
    }

    fun getAllNotes(): LiveData<MutableList<Note>> {
        return listOfNotes
    }
}