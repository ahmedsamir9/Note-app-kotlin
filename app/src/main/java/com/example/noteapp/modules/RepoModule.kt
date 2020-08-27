package com.example.noteapp.modules

import com.example.noteapp.repo.NoteRepo
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val repoModule = module {
    single { NoteRepo(get()) }
}