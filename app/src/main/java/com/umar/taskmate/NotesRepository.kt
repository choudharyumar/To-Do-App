package com.umar.taskmate

import androidx.lifecycle.LiveData

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