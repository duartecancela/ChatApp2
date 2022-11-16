package pt.ipbeja.chatapp.db

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Contact(
    val name: String,
    @PrimaryKey(autoGenerate = true) val id: Long = 0
)

data class Message(
    val contactId: Long,
    val text: String,
    val direction: Direction = Direction.OUT,
    @PrimaryKey(autoGenerate = true) val id: Long = 0
)

enum class Direction {
    IN, OUT
}