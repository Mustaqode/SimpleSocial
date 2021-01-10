package dev.mustaq.simplesocial.di

import dev.mustaq.simplesocial.ui.favourites.FavouritesViewModel
import dev.mustaq.simplesocial.ui.main.MainViewModel
import dev.mustaq.simplesocial.ui.post.PostViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module


/**
Created by Mustaq Sameer on 10/1/21
 **/

object AppModules {

    private val viewModelModules = module {
        viewModel { MainViewModel() }
        viewModel { PostViewModel() }
        viewModel { FavouritesViewModel() }
    }

    private val repoModules = module {
    }

    private val commonModules = module {

    }

    fun appModules() = viewModelModules + repoModules + commonModules


}