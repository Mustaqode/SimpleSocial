package dev.mustaq.simplesocial.mapper

import dev.mustaq.simplesocial.db.PostEntity
import dev.mustaq.simplesocial.helper.defaultValue
import dev.mustaq.simplesocial.model.PostDataModel


/**
Created by Mustaq Sameer on 11/1/21
 **/

fun PostDataModel.mapToPostEntity() =
    PostEntity(
        this.id,
        this.title,
        this.body
    )

fun PostEntity.mapToPostDataModel() =
    PostDataModel(
        this.id,
        this.title.defaultValue(),
        this.body.defaultValue()
    )
