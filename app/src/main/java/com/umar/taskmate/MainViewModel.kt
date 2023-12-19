package com.umar.taskmate

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
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
