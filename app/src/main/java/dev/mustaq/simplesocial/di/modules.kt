package dev.mustaq.simplesocial.di

import dev.mustaq.simplesocial.ui.main.MainViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module


/**
Created by Mustaq Sameer on 10/1/21
 **/

object AppModules {

    private val viewModelModules = module {
        viewModel { MainViewModel() }
    }

    private val repoModules = module {
    }

    private val commonModules = module {

    }

    fun appModules() = viewModelModules + repoModules + commonModules


}