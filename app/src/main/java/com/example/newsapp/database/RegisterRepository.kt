package com.example.newsapp.database

import android.util.Log

class RegisterRepository(private val dao: RegisterDatabaseDao) {

    val users = dao.getAllUsers()
    suspend fun insert(user: RegisterEntity) {
        return dao.insert(user)
    }

    suspend fun getUserName(userName: String):RegisterEntity?{
        Log.i("MYTAG", "inside Repository Getusers fun ")
        return dao.getUsername(userName)
    }

    suspend fun updateUserName(userName: String,id:Int){
        return dao.updateUserName(userName,id)
    }

    suspend fun updatePassword(password: String,id:Int){
        return dao.updatePassword(password,id)
    }
    //suspend fun deleteAll(): Int {
    //    return dao.deleteAll()
    //}

}