package com.umar.taskmate.viewmodel

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.umar.taskmate.data.Notes
import com.umar.taskmate.data.NotesDao
import com.umar.taskmate.data.NotesDataBase
import com.umar.taskmate.repository.NotesRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel(private val context: Context):ViewModel() {

    private val dbInstnace: NotesDataBase = NotesDataBase.getDB(context)
    private val notesDao: NotesDao = dbInstnace.GetNotesDao()
    private val notesRepository = NotesRepository(notesDao)

    fun getNotesView():LiveData<List<Notes>> {
       return notesRepository.getNotesR()
    }

    fun insertNotesView(notes: Notes)
    {
     viewModelScope.launch (Dispatchers.IO){
         notesRepository.InsertNotesR(notes)
     }
    }
    fun DeleteNotesView(notes: Notes)
    {
        viewModelScope.launch(Dispatchers.IO) {
            notesRepository.DeleteNotesR(notes)
        }
    }



}
