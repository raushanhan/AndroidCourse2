package ru.kpfu.itis.androidcoursefirst.hw2.ui.ViewHolder

import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.RequestManager
import ru.kpfu.itis.androidcoursefirst.hw2.databinding.ItemRvWithPicBinding
import ru.kpfu.itis.androidcoursefirst.hw2.model.PictureItemHolderModel

class ImageViewHolder(
    private val viewBinding: ItemRvWithPicBinding,
    private val requestManager: RequestManager,
    private val action: (Int) -> Unit,
    ) : RecyclerView.ViewHolder(viewBinding.root) {

    fun bindItem(itemData: PictureItemHolderModel) {
        requestManager.load(itemData.imageURL)
            .into(viewBinding.pictureIv)

        viewBinding.headerTv.text = itemData.header

        viewBinding.root.setOnClickListener {
            action.invoke(itemData.id)
        }
    }

}