package dev.mustaq.simplesocial.di

import android.content.Context
import androidx.room.Room
import dev.mustaq.simplesocial.db.AppDb
import dev.mustaq.simplesocial.repository.MiscRepository
import dev.mustaq.simplesocial.repository.PostRepository
import dev.mustaq.simplesocial.ui.favourites.FavouritesViewModel
import dev.mustaq.simplesocial.ui.main.MainViewModel
import dev.mustaq.simplesocial.ui.post.PostViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module


/**
Created by Mustaq Sameer on 10/1/21
 **/

object AppModules {

    private val viewModelModules = module {
        viewModel { MainViewModel(get()) }
        viewModel { PostViewModel(get()) }
        viewModel { FavouritesViewModel() }
    }

    private val repoModules = module {
        single { MiscRepository() }
        single { PostRepository(get(), get()) }
    }

    private val commonModules = module {
        single { getDatabase(androidContext()) }
    }

    fun appModules() = viewModelModules + repoModules + commonModules

    private fun getDatabase(context: Context) = Room.databaseBuilder(
        context,
        AppDb::class.java,
        "posts.db"
    ).build()


}