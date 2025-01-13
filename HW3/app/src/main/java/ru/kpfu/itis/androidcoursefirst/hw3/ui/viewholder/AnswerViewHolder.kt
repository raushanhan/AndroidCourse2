package ru.kpfu.itis.androidcoursefirst.hw3.ui.viewholder

import android.content.Context
import android.widget.RadioButton
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import ru.kpfu.itis.androidcoursefirst.hw3.R
import ru.kpfu.itis.androidcoursefirst.hw3.databinding.ItemAnswerBinding
import ru.kpfu.itis.androidcoursefirst.hw3.model.AnswerModel

class AnswerViewHolder(
    private val onClickAction: () -> Unit,
    private val viewBinding: ItemAnswerBinding,
) : RecyclerView.ViewHolder(viewBinding.root) {

    private var itemButton: RadioButton? = null

    fun bindItem(itemData: AnswerModel) {
        itemButton = viewBinding.answerButton

        with (viewBinding.answerButton) {
            text = itemData.answerText
            isChecked = itemData.isChosen
            if (isChecked)
                setBackgroundColor(ContextCompat.getColor(viewBinding.root.context, R.color.green))
            else setBackgroundColor(ContextCompat.getColor(viewBinding.root.context, R.color.default_white))
        }
    }

    fun getItemButton(): RadioButton? {
        return itemButton
    }
}