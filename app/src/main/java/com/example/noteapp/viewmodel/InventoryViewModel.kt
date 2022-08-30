package com.example.noteapp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.noteapp.data.Note
import com.example.noteapp.data.NoteDao
import kotlinx.coroutines.launch
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class InventoryViewModel(private val noteDao: NoteDao): ViewModel() {

    /**
    THAT FUNCTION INSERT IN YOUR DATABASE ONE NEW NOTE
    */
    private fun insertNote(note: Note){
        viewModelScope.launch {
            noteDao.insert(note)
        }
    }
    /**
    THAT FUNCTION WILL TAKE A USER ENTRY AND TRANSFORM IN A NOTE OBJECT
    */
    private fun getNewEntry(noteTitle: String, noteDesc: String, notePriority: String): Note{
        //HERE YOU TAKE AUTOMATIC THE CURRENT DATE
        val current = LocalDateTime.now()
        val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
        val currentDate = current.format(formatter)
        return Note(
            noteTitle = noteTitle,
            noteDesc = noteDesc,
            notePriority = notePriority,
            noteDate = currentDate
        )
    }

    /**
    THAT FUNCTION RECEIVE THE USER ENTRY AND TRANSMITS TO getNewEntry()
    AND AFTER TO RECEIVE A NOTE OBJECT RETURN TRANSMITS TO insertNote()
    */
    fun addNewNote(noteTitle: String, noteDesc: String, notePriority: String){
        val newNote = getNewEntry(noteTitle, noteDesc, notePriority)
        insertNote(newNote)
    }

    /**
    THAT FUNCTION CHECK IF THE USER ENTRY IS RIGHT
    */
    fun isEntryValid(noteTitle: String, noteDesc: String, notePriority: String): Boolean {
        if (noteTitle.isBlank() || noteDesc.isBlank() || notePriority.isBlank()) {
            return false
        }
        return true
    }
}
class InventoryViewModelFactory(private val noteDao: NoteDao): ViewModelProvider.Factory{
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(InventoryViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return InventoryViewModel(noteDao) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}