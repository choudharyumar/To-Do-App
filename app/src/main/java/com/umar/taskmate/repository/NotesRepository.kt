package com.umar.taskmate.repository

import androidx.lifecycle.LiveData
import com.umar.taskmate.data.Notes
import com.umar.taskmate.data.NotesDao

class NotesRepository(val notesDao: NotesDao) {

    suspend fun InsertNotesR(notes: Notes){
        notesDao.Insert(notes)
    }
    suspend fun DeleteNotesR(notes: Notes)
    {
        notesDao.Delete(notes)
    }
    fun getNotesR():LiveData<List<Notes>> {
        return notesDao.getAllNotes()
    }
}