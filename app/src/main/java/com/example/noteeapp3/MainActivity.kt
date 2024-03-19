package com.example.noteeapp3
import Noteshowscreen
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.room.Room
import com.example.noteapp2.ui.theme.Database.NoteDatabase
import com.example.noteapp2.ui.theme.Uistate.NoteViewMOdel
import com.example.noteapp2.ui.theme.Uistate.Sccreens.NoteAddScreen
import com.example.noteeapp3.ui.theme.NoteeApp3Theme

class MainActivity : ComponentActivity() {
    private val database by lazy {
        Room.databaseBuilder(
            applicationContext,
            NoteDatabase::class.java,
            "note.db"
        ).build()
    }

    private val viewModel by viewModels<NoteViewMOdel> (
        factoryProducer = {
            object : ViewModelProvider.Factory{
                override fun <T : ViewModel> create(modelClass: Class<T>): T {
                    return NoteViewMOdel(database.dao) as T
                }
            }
        }
    )
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NoteeApp3Theme {

                    val state by viewModel.state.collectAsState()
                    val NavController = rememberNavController()

                    NavHost(navController = NavController, startDestination = "Noteshowscreen" ){
                        composable("Noteshowscreen"){
                            Noteshowscreen(state = state, navController =NavController, onEvents = viewModel::Onevent )
                        }
                        composable("NoteAddScreen"){
                            NoteAddScreen(state = state, navController = NavController, onEvents = viewModel::Onevent)

                    }
                }
            }
        }
    }
}

