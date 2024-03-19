package com.example.noteapp2.ui.theme.Database

import Note
import androidx.room.Database
import androidx.room.RoomDatabase


@Database(entities = [Note::class], version = 1)
abstract class NoteDatabase:RoomDatabase() {
    abstract val dao:NoteDAo
}