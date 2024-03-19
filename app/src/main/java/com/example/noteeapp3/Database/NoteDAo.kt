package com.example.noteapp2.ui.theme.Database

import Note
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import kotlinx.coroutines.flow.Flow

@Dao
interface NoteDAo {
    @Upsert //Upsert = update+Insert
    suspend fun ubsertNote(note: Note)

    @Delete
    suspend fun deleteIteam(note: Note)

    @Query("SELECT * FROM note ORDER By titie ASC")
    fun getOrderbyTitle(): Flow<List<Note>>

    @Query("SELECT * FROM note ORDER By dateAdded ASC")
     fun getOrderbyDateAdded(): Flow<List<Note>>
}