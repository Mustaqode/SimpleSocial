package dev.mustaq.simplesocial.helper

import android.app.Activity
import android.content.Intent
import android.widget.ImageView
import android.widget.Toast
import androidx.annotation.DrawableRes
import androidx.fragment.app.Fragment
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

fun Fragment.startActivity(navigationModel: NavigationModel) {
    val intent = Intent(this.requireContext(), navigationModel.clazz)
    if (navigationModel.extras != null)
        intent.putExtras(navigationModel.extras)
    startActivity(intent)
    if (navigationModel.finishCurrent)
        requireActivity().finish()
}

fun Fragment.showToast(text: String) {
    Toast.makeText(this.requireContext(), text, Toast.LENGTH_SHORT).show()
}

fun Activity.showToast(text: String) {
    Toast.makeText(this, text, Toast.LENGTH_SHORT).show()
}



