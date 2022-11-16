package pt.ipbeja.chatapp.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface ContactDao : BaseDao<Contact>{

    @Query("select * from contact")
    fun getAll() : List<Contact>

    @Query("select * from contact where id = :id")
    fun getTodo(id: Long) : Contact

    @Insert
    fun insert(contact: Contact) : Long

    @Query("delete from contact where id = :id")
    fun delete(id: Long): Int
}