package com.example.noteapp.ViewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import com.example.noteapp.Model.Note
import com.example.noteapp.repo.NoteRepo
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class AddNoteViewModel(noteRepo: NoteRepo) : ViewModel() {
    private val _noteRepo = noteRepo
    private val coroutineScope = CoroutineScope(Dispatchers.IO)
    private var job : Job? = null
    fun addNote(item: Note){
        job =coroutineScope.launch {
            _noteRepo.insertNote(item)

        }
    }
    override fun onCleared() {
        super.onCleared()
        job?.cancel()
    }
}
