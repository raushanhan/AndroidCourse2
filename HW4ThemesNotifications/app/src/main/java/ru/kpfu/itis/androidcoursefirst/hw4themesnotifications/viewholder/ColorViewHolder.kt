package ru.kpfu.itis.androidcoursefirst.hw4themesnotifications.viewholder

import androidx.recyclerview.widget.RecyclerView
import ru.kpfu.itis.androidcoursefirst.hw4themesnotifications.databinding.ItemColorBinding
import ru.kpfu.itis.androidcoursefirst.hw4themesnotifications.model.Theme

class ColorViewHolder(
    private val viewBinding: ItemColorBinding,
    private val onClick: (Theme) -> Unit
) : RecyclerView.ViewHolder(viewBinding.root) {

    private var itemData: Theme? = null;

    init {
        with (viewBinding) {
            root.setOnClickListener {
                itemData?.let { data ->
                    onClick.invoke(data)
                }
            }
        }
    }

    fun bindItem(itemData: Theme) {
        this.itemData = itemData
        with (viewBinding) {
            root.setBackgroundColor(itemData.color)
        }
    }
}