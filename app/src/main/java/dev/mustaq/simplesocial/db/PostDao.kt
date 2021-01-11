package dev.mustaq.simplesocial.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query


/**
Created by Mustaq Sameer on 10/1/21
 **/

@Dao
interface PostDao {

    @Query("SELECT * FROM posts")
    suspend fun getAll() : List<PostEntity>

    @Insert
    suspend fun savePostToDb(vararg post: PostEntity)

//    @Query("SELECT * FROM posts WHERE id = :id")
//    suspend fun deletePostFromDb(id: Int)
}