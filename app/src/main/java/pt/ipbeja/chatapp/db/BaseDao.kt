package pt.ipbeja.chatapp.db

import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Update

interface BaseDao<T> {

    @Insert
    fun add(t: T) : Long

    @Insert
    fun add(t: Collection<T>): Array<Long>

    @Update
    fun update(t: T) : Int

    @Update
    fun update(t: Collection<T>) : Int

    @Delete
    fun delete(t: T) : Int

    @Delete
    fun delete(t: Collection<T>) : Int
}