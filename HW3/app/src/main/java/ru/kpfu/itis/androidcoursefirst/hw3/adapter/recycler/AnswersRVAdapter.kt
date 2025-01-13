package ru.kpfu.itis.androidcoursefirst.hw3.adapter.recycler

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ru.kpfu.itis.androidcoursefirst.hw3.databinding.ItemAnswerBinding
import ru.kpfu.itis.androidcoursefirst.hw3.model.AnswerModel
import ru.kpfu.itis.androidcoursefirst.hw3.ui.viewholder.AnswerViewHolder

class AnswersRVAdapter(
    private val onClickAction: () -> Unit,
    answers: List<AnswerModel>,
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var selectedPosition: Int = 0

    private val answerList = mutableListOf<AnswerModel>()
    init {
        answerList.addAll(answers)
        notifyDataSetChanged()
    }



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return AnswerViewHolder(
            onClickAction = onClickAction,
            viewBinding = ItemAnswerBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            ),
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as? AnswerViewHolder)?.bindItem(answerList[position])

        (holder as? AnswerViewHolder)?.getItemButton()?.setOnClickListener {
            val prevPos = selectedPosition
            selectedPosition = holder.adapterPosition

            answerList[prevPos].isChosen = false
            answerList[position].isChosen = true
            notifyItemChanged(prevPos)
            notifyItemChanged(selectedPosition)
        }

    }

    override fun getItemCount(): Int = answerList.size
}