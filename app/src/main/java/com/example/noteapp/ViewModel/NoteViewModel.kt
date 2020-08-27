package com.example.noteapp.ViewModel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.noteapp.Model.Note
import com.example.noteapp.repo.NoteRepo
import kotlinx.coroutines.*

class NoteViewModel(noteRepo: NoteRepo) : ViewModel()
{
   val _noteRepo = noteRepo
    private val coroutineScope = CoroutineScope(Dispatchers.IO)
    private var job :Job? = null
    val notes  = MutableLiveData<MutableList<Note?>>()
    fun loadNotes(){
        job=coroutineScope.launch {
            val data = _noteRepo.getNotes()
            withContext(Dispatchers.Main){
                notes.value = data
            }
        }


    }

    fun deleteNOte(note:Note){
        job=coroutineScope.launch {
          _noteRepo.deleteNote(note)
            loadNotes()

        }
    }

    override fun onCleared() {
        super.onCleared()
        job?.cancel()
    }
}