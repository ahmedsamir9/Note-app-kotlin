package com.example.noteapp.modules

import com.example.noteapp.ViewModel.AddNoteViewModel
import com.example.noteapp.ViewModel.NoteViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module


val  viewModelModule= module {
    viewModel { NoteViewModel(get())}
    viewModel { AddNoteViewModel(get())}
}