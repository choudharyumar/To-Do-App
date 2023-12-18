package com.umar.taskmate

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface NotesDao {
    @Insert
    suspend fun Insert(notes: Notes)
    @Delete
    suspend fun Delete(notes: Notes)
    @Query("SELECT * FROM notes")
    fun getAllNotes():LiveData<List<Notes>>
}