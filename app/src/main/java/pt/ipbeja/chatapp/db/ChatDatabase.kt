package pt.ipbeja.chatapp.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Contact::class], version = 1)
abstract class ChatDatabase : RoomDatabase() {

    abstract fun contactDao() : ContactDao

    companion object {
        @Volatile private var instance: ChatDatabase? = null
        private val LOCK = Any()

        operator fun invoke(context: Context) = instance ?: synchronized(LOCK) {
            instance ?: buildDatabase(context.applicationContext).also { instance = it}}

        private fun buildDatabase(context: Context) =
            Room.databaseBuilder(context.applicationContext, ChatDatabase::class.java, "contacts.db")
                .allowMainThreadQueries() // for now :)
                .build()
    }

}