package com.example.noteapp.viewmodel

import android.app.Application
import com.example.noteapp.data.NoteRoomDatabase

class NoteApplication: Application() {
    val database: NoteRoomDatabase by lazy { NoteRoomDatabase.getDatabase(this)}
}