package com.example.testapp

import androidx.room.*

@Dao
interface UserDao {
    @Query("SELECT * FROM user")
    fun getAll(): List<User>

    @Query("SELECT * FROM user WHERE uid IN (:userIds)")
    fun loadAllByIds(userIds: IntArray): List<User>
    @Query("SELECT * from user where uid = :id LIMIT 1")
    fun loadUserById(id: Int): User?

    @Query("SELECT * FROM user WHERE user_name LIKE :userName LIMIT 1")
    fun findByName(userName: String): User?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(vararg users: User)

    @Delete
    fun delete(user: User)
}