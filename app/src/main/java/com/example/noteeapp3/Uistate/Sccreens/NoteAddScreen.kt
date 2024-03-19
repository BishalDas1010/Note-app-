package com.example.noteapp2.ui.theme.Uistate.Sccreens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.noteapp2.ui.theme.Uistate.NoteEvents
import com.example.noteapp2.ui.theme.Uistate.NoteState

@Composable
fun NoteAddScreen(
    state: NoteState,
    navController: NavController,
    onEvents: (NoteEvents) ->Unit
) {


    Scaffold (
        floatingActionButton = {
            FloatingActionButton(onClick = {
                onEvents(
                NoteEvents.savenote(
                    titlee = state.tititee.value,
                    disp = state.discrip.value,
                )
                )
                navController.popBackStack()
            }) {
                Icon(imageVector = Icons.Rounded.Add, contentDescription = "")
            }
        }
    ){paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize()
        ) {

            OutlinedTextField(value = state.tititee.value, onValueChange = {

                state.tititee.value = it
            },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                placeholder = {
                    Text(text = "Title")
                }
                )
            OutlinedTextField(value = state.discrip.value, onValueChange = {

                state.discrip.value = it
            },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                placeholder = {
                    Text(text = "DESCRIPTOPN")
                }
            )
            
        }

    }

}



