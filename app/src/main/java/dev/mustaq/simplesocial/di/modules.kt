package dev.mustaq.simplesocial.di

import android.content.Context
import androidx.room.Room
import dev.mustaq.simplesocial.db.AppDb
import dev.mustaq.simplesocial.model.CommentsDataModel
import dev.mustaq.simplesocial.network.ApiProvider
import dev.mustaq.simplesocial.repository.MiscRepository
import dev.mustaq.simplesocial.repository.PostRepository
import dev.mustaq.simplesocial.ui.comment.CommentsViewModel
import dev.mustaq.simplesocial.ui.favourites.FavouritesViewModel
import dev.mustaq.simplesocial.ui.main.MainViewModel
import dev.mustaq.simplesocial.ui.post.PostViewModel
import dev.mustaq.simplesocial.ui.splash.SplashViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module


/**
Created by Mustaq Sameer on 10/1/21
 **/

object AppModules {

    private val viewModelModules = module {
        viewModel { SplashViewModel() }
        viewModel { MainViewModel(get(), get()) }
        viewModel { PostViewModel(get()) }
        viewModel { FavouritesViewModel(get()) }
        viewModel { CommentsViewModel(get()) }
    }

    private val repoModules = module {
        single { MiscRepository() }
        single { PostRepository(get(), get()) }
    }

    private val commonModules = module {
        single { getDatabase(androidContext()) }
        single { ApiProvider.client }
    }

    fun appModules() = viewModelModules + repoModules + commonModules

    private fun getDatabase(context: Context) = Room.databaseBuilder(
        context,
        AppDb::class.java,
        "posts.db"
    ).build()


}