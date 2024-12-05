package ru.kpfu.itis.androidcoursefirst.hw2.adapter.recycler

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.RequestManager
import ru.kpfu.itis.androidcoursefirst.hw2.R
import ru.kpfu.itis.androidcoursefirst.hw2.databinding.ItemRvWithButtonsBinding
import ru.kpfu.itis.androidcoursefirst.hw2.databinding.ItemRvWithPicBinding
import ru.kpfu.itis.androidcoursefirst.hw2.model.ButtonsItemHolderModel
import ru.kpfu.itis.androidcoursefirst.hw2.model.MultipleHoldersModel
import ru.kpfu.itis.androidcoursefirst.hw2.model.PictureItemHolderModel
import ru.kpfu.itis.androidcoursefirst.hw2.repository.ListContentRepository
import ru.kpfu.itis.androidcoursefirst.hw2.ui.ViewHolder.ButtonsViewHolder
import ru.kpfu.itis.androidcoursefirst.hw2.ui.ViewHolder.ImageViewHolder
import ru.kpfu.itis.androidcoursefirst.hw2.utils.MultiAdapterDiffUtil
import kotlin.random.Random

class AdapterWithMultipleHolders(
    private val requestManager: RequestManager,
    private val onPicItemCLick: (Int) -> Unit,
    private val onGridButtonClick: () -> Unit,
    private val onListButtonClick: () -> Unit,
    items: List<MultipleHoldersModel>,
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val dataList = mutableListOf<MultipleHoldersModel>()


    init {
        dataList.addAll(items)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            R.layout.item_rv_with_buttons -> {
                ButtonsViewHolder(
                    onGridBtnClickAction = onGridButtonClick,
                    onListBtnClickAction = onListButtonClick,
                    viewBinding = ItemRvWithButtonsBinding.inflate(
                        LayoutInflater.from(parent.context),
                        parent,
                        false
                    )
                )
            }

            R.layout.item_rv_with_pic -> {
                ImageViewHolder(
                    viewBinding = ItemRvWithPicBinding.inflate(
                        LayoutInflater.from(parent.context),
                        parent,
                        false
                    ),
                    requestManager = requestManager,
                    action = onPicItemCLick
                )
            }

            else -> throw IllegalStateException("Unknown holder")

        }

    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

        when (dataList[position]) {
            is ButtonsItemHolderModel -> {
                (holder as? ButtonsViewHolder)?.bindItem()
            }

            is PictureItemHolderModel -> {
                (holder as? ImageViewHolder)?.bindItem(itemData = dataList[position] as PictureItemHolderModel)
            }
        }
    }

    override fun getItemCount(): Int = dataList.size

    override fun getItemViewType(position: Int): Int {
        val item = dataList[position]
        return when (item) {
            is ButtonsItemHolderModel -> {
                R.layout.item_rv_with_buttons
            }

            is PictureItemHolderModel -> {
                R.layout.item_rv_with_pic
            }

            else -> throw IllegalStateException("Incorrect holder type")
        }
    }

    fun addElements(quantity: Int) {
        val newDataList = mutableListOf<MultipleHoldersModel>()
        newDataList.addAll(dataList)
        for (i in 1..quantity) {
            if (newDataList.size <= 2) {
                newDataList.add(
                    ListContentRepository.getRandomCity()
                )
            } else {
                newDataList.add(
                    Random.nextInt(1, newDataList.size + 1),
                    ListContentRepository.getRandomCity()
                )
            }
        }
        updateData(newDataList)
    }

    fun removeElements(quantity: Int) {
        val newDataList = mutableListOf<MultipleHoldersModel>()
        newDataList.addAll(dataList)
        if (newDataList.size - 1 <= quantity) {
            val buttonsHolder = newDataList[0]
            newDataList.clear()
            newDataList.add(buttonsHolder)
        } else {
            for (i in 1..quantity) {
                newDataList.removeAt(Random.nextInt(1, newDataList.size))
            }
        }
        updateData(newDataList)
    }

    private fun updateData(newList: List<MultipleHoldersModel>) {
        val diffCallback = MultiAdapterDiffUtil(
            oldList = dataList,
            newList = newList
        )

        val diffResult = DiffUtil.calculateDiff(diffCallback)

        diffResult.dispatchUpdatesTo(this)
        dataList.clear()
        dataList.addAll(newList)
    }
}