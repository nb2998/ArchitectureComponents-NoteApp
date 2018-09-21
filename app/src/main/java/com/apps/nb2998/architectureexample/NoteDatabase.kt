package com.apps.nb2998.architectureexample

import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.content.Context
import android.arch.persistence.db.SupportSQLiteDatabase
import android.os.AsyncTask

@Database(entities = [Note::class], version = 1)
abstract class NoteDatabase : RoomDatabase() {

    abstract fun noteDao(): NoteDao

    companion object {
        private var instance: NoteDatabase? = null
        private var roomCallback = object: RoomDatabase.Callback(){
            override fun onCreate(db: SupportSQLiteDatabase) {
                PopulateDB(instance).execute()
                super.onCreate(db)
            }
        }

        @Synchronized
        fun getInstance(context: Context): NoteDatabase{
            if(instance==null){
                instance = Room.databaseBuilder(context.applicationContext,
                        NoteDatabase::class.java, context.getString(R.string.note_database_name))
                        .fallbackToDestructiveMigration()
                        .addCallback(roomCallback)
                        .build()
            }
            return instance as NoteDatabase
        }

        private class PopulateDB(var noteDatabase: NoteDatabase?): AsyncTask<Void, Void, Void>(){
            override fun doInBackground(vararg params: Void?): Void? {
                noteDatabase!!.noteDao().insert(Note("Note 1", "Description 1", 1))
                noteDatabase!!.noteDao().insert(Note("Note 2", "Description 2", 2))
                noteDatabase!!.noteDao().insert(Note("Note 3", "Description 3", 3))
                return null
            }
        }
    }
}