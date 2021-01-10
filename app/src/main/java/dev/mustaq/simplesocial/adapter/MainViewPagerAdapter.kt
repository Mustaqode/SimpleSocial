package dev.mustaq.simplesocial.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter


/**
Created by Mustaq Sameer on 10/1/21
 **/

class MainViewPagerAdapter(supportFragmentManager: FragmentManager) :
    FragmentStatePagerAdapter(supportFragmentManager, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    private val fragmentList: ArrayList<Fragment> = ArrayList()
    private val fragmentTitleList: ArrayList<String> = ArrayList()

    override fun getCount(): Int = fragmentList.size

    override fun getItem(position: Int): Fragment = fragmentList[position]

    fun addFragment(fragments: ArrayList<Fragment>, titles: ArrayList<String>){
        fragmentList.addAll(fragments)
        fragmentTitleList.addAll(titles)
    }
}