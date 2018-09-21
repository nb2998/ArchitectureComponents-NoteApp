package com.apps.nb2998.architectureexample

import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.content.Context

@Database(entities = arrayOf(Note::class), version = 1)
abstract class NoteDatabase : RoomDatabase() {

    abstract fun noteDao(): NoteDao

    companion object {
        var instance: NoteDatabase? = null
    }

    fun getInstance(context: Context): NoteDatabase{
        if(instance==null){
            instance = Room.databaseBuilder(context.applicationContext,
                    NoteDatabase::class.java, context.getString(R.string.note_database_name))
                    .fallbackToDestructiveMigration()
                    .build()
        }
        return instance as NoteDatabase
    }
}
