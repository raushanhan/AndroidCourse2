package ru.kpfu.itis.androidcoursefirst.hw4themesnotifications.adapter

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ru.kpfu.itis.androidcoursefirst.hw4themesnotifications.databinding.ItemColorBinding
import ru.kpfu.itis.androidcoursefirst.hw4themesnotifications.model.Theme
import ru.kpfu.itis.androidcoursefirst.hw4themesnotifications.viewholder.ColorViewHolder

class ColorsRVAdapter(
    private val onClickAction: (Theme) -> Unit,
    themes: List<Theme>
) : RecyclerView.Adapter<ColorViewHolder>() {

    private val itemList = mutableListOf<Theme>()

    init {
        itemList.addAll(themes)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ColorViewHolder {
        val viewHolder = ColorViewHolder(
            viewBinding = ItemColorBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            ), onClick = onClickAction
        )

        return viewHolder
    }

    override fun onBindViewHolder(holder: ColorViewHolder, position: Int) {
        holder.bindItem(itemList[position])
    }

    override fun getItemCount(): Int = itemList.size
}