package com.example.noteapp.viewmodel

import android.app.Application
import com.example.noteapp.data.NoteRoomDatabase

class InventoryApplication: Application() {
    val database: NoteRoomDatabase by lazy { NoteRoomDatabase.getDatabase(this)}
}