package dev.mustaq.simplesocial.ui.splash

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import dev.mustaq.simplesocial.R
import dev.mustaq.simplesocial.helper.observeLiveData
import dev.mustaq.simplesocial.helper.startActivity
import org.koin.androidx.viewmodel.ext.android.viewModel


/**
Created by Mustaq Sameer on 12/1/21
 **/

class SplashActivity : AppCompatActivity() {

    private val splashViewModel : SplashViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        setupUi()
    }

    private fun setupUi() {
        splashViewModel.navigation.observeLiveData(this) { startActivity(it) }
    }

}