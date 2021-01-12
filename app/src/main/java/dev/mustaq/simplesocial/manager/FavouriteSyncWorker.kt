package dev.mustaq.simplesocial.manager

import android.content.Context
import android.util.Log
import androidx.work.Worker
import androidx.work.WorkerParameters
import dev.mustaq.simplesocial.db.AppDb
import dev.mustaq.simplesocial.db.PostEntity
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.koin.core.KoinComponent
import org.koin.core.inject


/**
Created by Mustaq Sameer on 12/1/21
 **/
class FavouriteSyncWorker(context: Context, workerParams: WorkerParameters) : Worker(context, workerParams), KoinComponent {

    private val appDb: AppDb by inject()

    /**
     * Dummy implementation of fav post sync to the server
     */
    override fun doWork(): Result {
        val postsList: ArrayList<PostEntity> = ArrayList()
        GlobalScope.launch {
            val posts = appDb.postDao().getAll()
            postsList.addAll(posts)
        }
        for (i in 0..10){
            //Upload posts to the server
            Log.i("TAG", "Syncing with the server")
        }
        return Result.success()
    }
}