package com.example.noteapp2.ui.theme.Uistate

import Note
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.noteapp2.ui.theme.Database.NoteDAo
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class NoteViewMOdel(
    private val dao:NoteDAo
) :ViewModel(){

    private val isSortetedBYdate_Added = MutableStateFlow(true)
    private val notes = isSortetedBYdate_Added.flatMapLatest {
        if (it){
            dao.getOrderbyDateAdded()
        }else{
            dao.getOrderbyTitle()
        }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(), emptyList())

    }
    val _state = MutableStateFlow(NoteState())
    val state = combine(_state,isSortetedBYdate_Added,notes){
            state,isSortetedBYdate_Added,notes->
        state.copy(
         notes=notes
        )
    }.stateIn(viewModelScope,SharingStarted.WhileSubscribed(5000),NoteState())


    fun Onevent(events: NoteEvents){
        when(events){
            is NoteEvents.DeleteNote -> {

                viewModelScope.launch {
                    dao.deleteIteam(events.note)
                }
            }
            NoteEvents.SortNote -> {
                isSortetedBYdate_Added.value = !isSortetedBYdate_Added.value
            }
            is NoteEvents.savenote -> {

//                val note = Note(
//                    title = state.value.tititee.value,
//                    Descripction = state.value.discrip.value,
//                    dateAdded = System.currentTimeMillis()
//                )
                val note =Note(
                    titie = state.value.tititee.value,
                    discripction = state.value.discrip.value,
                    dateadded = System.currentTimeMillis()
                )
                viewModelScope.launch {
                    dao.ubsertNote(note = note)
                }
                _state.update {
                    it.copy(
                        tititee = mutableStateOf(" "),
                        discrip = mutableStateOf(" ")
                    )
                }
            }
        }
    }
}