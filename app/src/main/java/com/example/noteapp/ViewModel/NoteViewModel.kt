package com.example.noteapp.ViewModel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.noteapp.Model.Note
import com.example.noteapp.repo.NoteRepo
import kotlinx.coroutines.*

class NoteViewModel(application: Application) : AndroidViewModel(application)
{
    private val noteRepo = NoteRepo(application)
    private val coroutineScope = CoroutineScope(Dispatchers.IO)
    private var job :Job? = null
    val notes  = MutableLiveData<MutableList<Note?>>()
    fun loadNotes(){
        job=coroutineScope.launch {
            val data = noteRepo.getNotes()
            withContext(Dispatchers.Main){
                notes.value = data
            }
        }


    }

    fun deleteNOte(note:Note){
        job=coroutineScope.launch {
          noteRepo.deleteNote(note)
            loadNotes()

        }
    }

    override fun onCleared() {
        super.onCleared()
        job?.cancel()
    }
}