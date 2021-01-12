package dev.mustaq.simplesocial.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey


/**
Created by Mustaq Sameer on 10/1/21
 **/

@Entity(tableName = "posts")
data class PostEntity(
    @ColumnInfo(name = "id")
    @PrimaryKey
    val id: Int,

    @ColumnInfo(name = "title") val title: String?,
    @ColumnInfo(name = "body") val body: String?
)