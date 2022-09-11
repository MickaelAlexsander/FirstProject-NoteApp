package com.example.noteapp.data

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface NoteDao {
    @Query("SELECT * FROM note ORDER BY date DESC")
    fun getNotes(): Flow<List<Note>>

    @Query("SELECT * FROM note WHERE priority = 'high'")
    fun getHighPriority(): Flow<List<Note>>

    @Query("SELECT * FROM note WHERE priority = 'medium'")
    fun getMediumPriority(): Flow<List<Note>>

    @Query("SELECT * FROM note WHERE priority = 'low'")
    fun getLowPriority(): Flow<List<Note>>

    @Query("SELECT * from note WHERE id = :id")
    fun getNote(id: Int): Flow<Note>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(note: Note)

    @Update
    suspend fun update(note: Note)

    @Query("DELETE from note where id = :id")
    fun deleteNote(id: Int)

    @Delete
    suspend fun delete(note: Note)
}