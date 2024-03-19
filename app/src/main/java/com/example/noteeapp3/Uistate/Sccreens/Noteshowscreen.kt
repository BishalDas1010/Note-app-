import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material.icons.rounded.Delete
import androidx.compose.material.icons.rounded.List
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.noteapp2.ui.theme.Uistate.NoteEvents
import com.example.noteapp2.ui.theme.Uistate.NoteState

@Composable
fun Noteshowscreen(
    state: NoteState,
    navController: NavController,
    onEvents:(NoteEvents)->Unit
){
    Scaffold(
        topBar = {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(55.dp)
                    .background(MaterialTheme.colorScheme.inverseSurface)
                    .padding(16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(text = "NOteefy",
                    modifier = Modifier.weight(1f),
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                    )
                IconButton(onClick = {
                    onEvents(NoteEvents.SortNote)
                }) {
                    Icon(imageVector = Icons.Rounded.List, contentDescription = null,
                        modifier = Modifier.size(35.dp)
                        )
                }
            }
            
        },
        floatingActionButton = {
            FloatingActionButton(onClick = {
                state.tititee.value =""
                state.discrip.value =""
                navController.navigate("NoteAddScreen")
            }) {
                Icon(imageVector = Icons.Rounded.Add, contentDescription = null)
            }
        }
    ) {paddingValues ->
        LazyColumn(contentPadding = paddingValues,
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
            items(state.notes.size){
                Noteiteam(state = state, index = it, onEvents = onEvents)
            }
        }
    }

}

@Composable
fun Noteiteam(state: NoteState, index: Int, onEvents: (NoteEvents) -> Unit) {
Row (
    modifier = Modifier
        .fillMaxWidth()
        .padding(12.dp)
        .clip(
            RoundedCornerShape(10.dp)
        )
){
    Row {
        Column(modifier = Modifier.weight(1f)) {
            Text(text = state.notes.get(index = index).titie)
            Spacer(modifier = Modifier.height(10.dp))
            Text(text = state.notes.get(index = index).discripction)
        }
        Icon(imageVector = Icons.Rounded.Delete, contentDescription = null,
            modifier = Modifier.size(35.dp)
            )
    }
}
}
