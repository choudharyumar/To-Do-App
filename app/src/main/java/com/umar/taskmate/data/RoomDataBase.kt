package com.umar.taskmate.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Notes::class], version = 1)
abstract class NotesDataBase : RoomDatabase() {
    abstract fun GetNotesDao(): NotesDao

    companion object {
        @Volatile
        private var INSTANCE: NotesDataBase? = null
        fun getDB(context: Context): NotesDataBase {
            synchronized(this){
                if (INSTANCE ==null){
                    INSTANCE = Room.databaseBuilder(
                        context,
                      NotesDataBase::class.java,
                        "notes"
                    ).build()
                }
            }
            return INSTANCE!!
        }
    }

}