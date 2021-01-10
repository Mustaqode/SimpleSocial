package dev.mustaq.simplesocial.ui.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import dev.mustaq.simplesocial.R
import dev.mustaq.simplesocial.adapter.MainViewPagerAdapter
import dev.mustaq.simplesocial.helper.observeLiveData
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    private val mainViewModel : MainViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupUi()
        setListeners()
    }

    private fun setupUi() {
        mainViewModel.fragmentsList.observeLiveData(this, ::setupViewPager)
    }

    private fun setListeners() {

    }

    private fun setupViewPager(fragmentData: fragmentData) {
        val adapter = MainViewPagerAdapter(supportFragmentManager)
        adapter.addFragments(fragmentData.first, fragmentData.second)
        uiViewPager.adapter = adapter
        uiTabs.setupWithViewPager(uiViewPager)
    }
}