package dev.mustaq.simplesocial.repository

import dev.mustaq.simplesocial.constants.NAME_FAVORITES_FRAGMENT
import dev.mustaq.simplesocial.constants.NAME_POST_FRAGMENT
import dev.mustaq.simplesocial.ui.favourites.FavoritesFragment
import dev.mustaq.simplesocial.ui.post.PostFragment


/**
Created by Mustaq Sameer on 10/1/21
 **/
class MiscRepository {

    fun getListOfFragments() =
        arrayListOf(PostFragment.newInstance(), FavoritesFragment.newInstance()) to
                arrayListOf(NAME_POST_FRAGMENT, NAME_FAVORITES_FRAGMENT)

}