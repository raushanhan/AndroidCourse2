package ru.kpfu.itis.androidcoursefirst.hw2.ui.ViewHolder

import androidx.recyclerview.widget.RecyclerView
import ru.kpfu.itis.androidcoursefirst.hw2.databinding.ItemRvWithButtonsBinding

class ButtonsViewHolder(
    private val onGridBtnClickAction: () -> Unit,
    private val onListBtnClickAction: () -> Unit,
    val viewBinding: ItemRvWithButtonsBinding,
) : RecyclerView.ViewHolder(viewBinding.root) {

    init {
        viewBinding.rvGridBtn.setOnClickListener {
            onGridBtnClickAction()
        }
        viewBinding.rvListBtn.setOnClickListener {
            onListBtnClickAction()
        }
    }

    fun bindItem() {

    }

}