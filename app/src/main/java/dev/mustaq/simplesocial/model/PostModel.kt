package dev.mustaq.simplesocial.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize


/**
Created by Mustaq Sameer on 10/1/21
 **/

@Parcelize
data class PostDataModel(
    val id: Int,
    val title: String,
    val body: String
) : Parcelable
