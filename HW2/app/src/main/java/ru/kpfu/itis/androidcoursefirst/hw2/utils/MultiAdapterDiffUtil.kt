package ru.kpfu.itis.androidcoursefirst.hw2.utils

import androidx.recyclerview.widget.DiffUtil
import ru.kpfu.itis.androidcoursefirst.hw2.model.MultipleHoldersModel
import ru.kpfu.itis.androidcoursefirst.hw2.model.PictureItemHolderModel

class MultiAdapterDiffUtil(
    private val oldList: List<MultipleHoldersModel>,
    private val newList: List<MultipleHoldersModel>
) : DiffUtil.Callback() {

    override fun getOldListSize(): Int = oldList.size

    override fun getNewListSize(): Int = newList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldItem = oldList[oldItemPosition]
        val newItem = newList[newItemPosition]
        return (oldItem.id == newItem.id)
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldItem = oldList[oldItemPosition]
        val newItem = newList[newItemPosition]

        if (oldItem is PictureItemHolderModel && newItem is PictureItemHolderModel) {
            return (oldItem.header == newItem.header &&
                    oldItem.imageURL == newItem.imageURL &&
                    oldItem.description == newItem.description)
        }

        return oldItem == newItem

    }
}