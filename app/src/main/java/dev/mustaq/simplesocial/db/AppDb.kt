package dev.mustaq.simplesocial.db

import androidx.room.Database
import androidx.room.RoomDatabase
import org.koin.core.KoinComponent


/**
Created by Mustaq Sameer on 10/1/21
 **/

@Database(entities = [PostEntity::class], version = 1)
abstract class AppDb : RoomDatabase(), KoinComponent {

    abstract fun postDao(): PostDao

}