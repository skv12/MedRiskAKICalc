package com.example.diploma

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface AppDao {
    @Insert
    fun insertAll(vararg db: AppEntity)

    @Query("SELECT * FROM result_db")
    fun getAll(): List<AppEntity>

    @Query("SELECT MAX(id) FROM result_db")
    fun lastId(): Int

    @Query("DELETE FROM result_db")
    fun deleteAll()

    @Query("DELETE FROM result_db WHERE id = :Id")
    fun deleteItem(Id: Int)

    @Query("SELECT * FROM result_db WHERE id = :Id")
    fun selectItem(Id: Int): List<AppEntity>
}