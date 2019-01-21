package com.mario.desafiouds.data.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.mario.desafiouds.data.entity.User

@Dao
interface UserDao {

    /**
     * Get a user by id.
     */
    @Query("SELECT * FROM user WHERE id = :id")
    fun getUserById(id: String): LiveData<User>

    /**
     * Get password by email
     */
    @Query("SELECT password FROM user WHERE email = :email")
    fun getPasswordByEmail(email: String): String?

    /**
     * Find Login and Password
     */
    @Query("SELECT * FROM user WHERE email = :email and password = :password")
    fun login(email: String, password: String): Boolean

    /**
     * Insert a user in the database. If the user already exists, replace it.
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertUser(user: User): Long

    /**
     * Delete all users.
     */
    @Query("DELETE FROM user")
    fun deleteAllUsers()
}