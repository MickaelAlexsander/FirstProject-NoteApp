package com.example.noteapp.data

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface NoteDao {
    @Query("SELECT * FROM note ORDER BY date DESC")
    fun getAllNotes(): Flow<List<Note>>

    @Query("SELECT * from note WHERE id = :id")
    fun getItem(id: Int): Flow<Note>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(note: Note)

    @Update
    suspend fun update(note: Note)

    @Delete
    suspend fun delete(note: Note)
}