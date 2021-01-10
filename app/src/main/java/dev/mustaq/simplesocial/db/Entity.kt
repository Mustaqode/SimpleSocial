package dev.mustaq.simplesocial.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


/**
Created by Mustaq Sameer on 10/1/21
 **/

@Entity(tableName = "posts")
data class PostEntity(
    @PrimaryKey
    val postId: Int,

    @ColumnInfo(name = "id") var id: Int,
    @ColumnInfo(name = "title") var title: String,
    @ColumnInfo(name = "body") var body: String,
)