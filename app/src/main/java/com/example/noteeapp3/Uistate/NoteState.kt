package com.example.noteapp2.ui.theme.Uistate


import Note
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf

data class NoteState (
    var notes :List<Note> = emptyList(),
    val tititee: MutableState<String> = mutableStateOf(""),
    val discrip:MutableState<String> = mutableStateOf("")
)