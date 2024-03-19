import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Note(
    @PrimaryKey(autoGenerate = true)
    val id :Int = 0,
    val titie:String,
    val discripction :String,
    val dateadded:Long
)