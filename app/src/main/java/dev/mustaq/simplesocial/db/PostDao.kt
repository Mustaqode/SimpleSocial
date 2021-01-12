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

    @Delete
    suspend fun deletePostFromDb(post: PostEntity)

    @Query("SELECT EXISTS (SELECT 1 FROM posts WHERE id = :id)")
    suspend fun checkWhetherTheObjectIsInDb(id: Int) : Boolean

}