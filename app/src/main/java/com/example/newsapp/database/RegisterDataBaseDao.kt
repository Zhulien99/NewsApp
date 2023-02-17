package com.example.newsapp.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface RegisterDatabaseDao {

    @Insert
    suspend fun insert(register: RegisterEntity)

    //@Delete
    //suspend  fun deleteSubscriber(register: RegisterEntity):Int

    @Query("SELECT * FROM Register_users_table ORDER BY userId DESC")
    fun getAllUsers(): LiveData<List<RegisterEntity>>

    @Query("DELETE FROM Register_users_table")
    suspend fun deleteAll(): Int

    @Query("SELECT * FROM Register_users_table WHERE user_name LIKE :userName")
    suspend fun getUsername(userName: String): RegisterEntity?

    @Query("UPDATE Register_users_table SET first_name=:userName WHERE userId = :id")
    suspend fun updateUserName(userName: String, id: Int)

    @Query("UPDATE Register_users_table SET password_text=:password WHERE userId = :id")
    suspend fun updatePassword(password: String, id: Int)
}