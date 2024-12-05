package ru.kpfu.itis.androidcoursefirst.hw2.model

import java.net.URL

sealed class MultipleHoldersModel(
    open val id: Int,
)

class PictureItemHolderModel(
    override val id: Int,
    val header: String,
    val imageURL: String,
    val description: String
) : MultipleHoldersModel(id)

class ButtonsItemHolderModel(
    override val id: Int,
) : MultipleHoldersModel(id)