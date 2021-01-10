package dev.mustaq.simplesocial.helper

import android.app.Activity
import android.content.Intent
import android.widget.ImageView
import androidx.annotation.DrawableRes
import com.bumptech.glide.Glide
import dev.mustaq.simplesocial.R
import dev.mustaq.simplesocial.model.NavigationModel


/**
Created by Mustaq Sameer on 10/01/21
 **/

fun Activity.insertImageIntoView(
    image: String?,
    target: ImageView,
    @DrawableRes placeHolder: Int = R.drawable.ic_image_placeholder
) {
    Glide.with(this)
        .load(image.defaultValue())
        .placeholder(placeHolder)
        .into(target)
}

fun Activity.startActivity(navigationModel: NavigationModel) {
    val intent = Intent(this, navigationModel.clazz)
    if (navigationModel.extras != null)
        intent.putExtras(navigationModel.extras)
    startActivity(intent)
    if (navigationModel.finishCurrent)
        finish()
}

