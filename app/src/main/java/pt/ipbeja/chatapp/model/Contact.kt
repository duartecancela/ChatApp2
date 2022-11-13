package pt.ipbeja.chatapp.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Contact(
    val name: String,
    @PrimaryKey(autoGenerate = true) val id: Long = 0
    )
