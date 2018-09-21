package com.apps.nb2998.architectureexample

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.*

@Dao
interface NoteDao {

    @Insert
    fun insert(note: Note)

    @Delete
    fun delete(note: Note)

    @Update
    fun update()

    @Query("DELETE FROM note_table")
    fun deleteAllNotes()

    @Query("SELECT * FROM note_table ORDER BY priority DESC")
    fun getAllNotes(): LiveData<List<Note>>
}