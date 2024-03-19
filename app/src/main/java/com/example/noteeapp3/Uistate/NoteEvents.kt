package com.example.noteapp2.ui.theme.Uistate

import Note

sealed interface NoteEvents{

    data class DeleteNote(var note : Note): NoteEvents
    data class savenote (
        var titlee :String,
        val disp :String
    ): NoteEvents
    data object SortNote : NoteEvents

}