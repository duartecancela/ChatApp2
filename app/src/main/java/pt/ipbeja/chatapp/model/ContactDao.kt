package pt.ipbeja.chatapp.model

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
abstract class ContactDao {
    @Query("select * from contact")
    abstract fun getAll() : List<Contact>

    @Query("select * from contact where id = :id")
    abstract fun getTodo(id: Long) : Contact

    @Insert
    abstract fun insert(contact: Contact) : Long

    @Query("delete from contact where id = :id")
    abstract fun delete(id: Long): Int
}