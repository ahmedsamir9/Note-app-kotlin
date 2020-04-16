package com.example.noteapp.Persistence

import androidx.room.*
import com.example.noteapp.Model.Note

@Dao
interface NoteDao {
    @Insert (onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertNote(note:Note)
    @Delete
    suspend fun deleteNote(note: Note)
    @Query("select * from Note order by importance desc ")
    suspend fun getNotes():MutableList<Note?>?
}