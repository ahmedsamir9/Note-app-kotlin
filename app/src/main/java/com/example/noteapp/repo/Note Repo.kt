package com.example.noteapp.repo

import android.content.Context
import com.example.noteapp.Model.Note
import com.example.noteapp.Persistence.NoteDao
import com.example.noteapp.Persistence.NoteDataBase

class NoteRepo(val db :NoteDao) {
   // private val db =NoteDataBase.invoke(context).noteDao()
    suspend fun insertNote(note:Note) = db.insertNote(note)
    suspend fun deleteNote(note:Note) = db.deleteNote(note)
    suspend fun getNotes() = db.getNotes()
}