package com.example.noteapp.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Note (
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    @ColumnInfo(name = "title")
    val noteTitle: String,
    @ColumnInfo(name = "desc")
    val noteDesc: String,
    @ColumnInfo(name = "priority")
    val notePriority: String,
    @ColumnInfo(name = "date")
    val noteDate: String
        )