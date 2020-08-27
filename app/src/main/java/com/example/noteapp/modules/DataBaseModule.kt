package com.example.noteapp.modules

import com.example.noteapp.Persistence.NoteDataBase
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val databaseModule = module {
    single { NoteDataBase.invoke(androidContext()) }
    single { get<NoteDataBase>().noteDao() }

}