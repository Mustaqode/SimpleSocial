package dev.mustaq.simplesocial.ui.main

import android.content.IntentFilter
import android.net.ConnectivityManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import dev.mustaq.simplesocial.R
import dev.mustaq.simplesocial.adapter.MainViewPagerAdapter
import dev.mustaq.simplesocial.helper.observeLiveData
import dev.mustaq.simplesocial.helper.showToast
import dev.mustaq.simplesocial.reciever.ConnectivityBroadcast
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.androidx.viewmodel.ext.android.viewModel

@Suppress("DEPRECATION")
class MainActivity : AppCompatActivity(), ConnectivityBroadcast.ConnectivityReceiverListener {

    private val mainViewModel: MainViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupUi()
        registerReceiver(ConnectivityBroadcast(), IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION))
    }

    override fun onResume() {
        super.onResume()
        ConnectivityBroadcast.connectivityReceiverListener = this
    }

    private fun setupUi() {
        mainViewModel.fragmentsList.observeLiveData(this, ::setupViewPager)
        mainViewModel.internetMessage.observeLiveData(this) { showToast(it) }
    }

    private fun setupViewPager(fragmentData: fragmentData) {
        val adapter = MainViewPagerAdapter(supportFragmentManager)
        adapter.addFragments(fragmentData.first, fragmentData.second)
        uiViewPager.adapter = adapter
        uiTabs.setupWithViewPager(uiViewPager)
    }

    override fun onNetworkConnectionChanged(isConnected: Boolean) {
        mainViewModel.handleInternetAvailability(isConnected)
    }
}