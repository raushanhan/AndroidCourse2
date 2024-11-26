package ru.kpfu.itis.androidcoursefirst.hw2.screens

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import by.kirich1409.viewbindingdelegate.viewBinding
import com.bumptech.glide.Glide
import ru.kpfu.itis.androidcoursefirst.hw2.R
import ru.kpfu.itis.androidcoursefirst.hw2.databinding.FragmentListContentBinding
import ru.kpfu.itis.androidcoursefirst.hw2.databinding.FragmentRvItemInfoBinding
import ru.kpfu.itis.androidcoursefirst.hw2.model.PictureItemHolderModel
import ru.kpfu.itis.androidcoursefirst.hw2.repository.ListContentRepository

class ItemInfoFragment : Fragment(R.layout.fragment_rv_item_info) {

    private val viewBinding: FragmentRvItemInfoBinding by viewBinding(FragmentRvItemInfoBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val list = ListContentRepository.getListOfCities()
        val cityId = arguments?.getInt(ARG_ID)
        val city = list.find {it.id == cityId} as? PictureItemHolderModel

        viewBinding.apply {
            Glide.with(imageIv.context)
                .load(city?.imageURL)
                .into(imageIv)

            headerTv.text = city?.header
            descriptionTv.text = city?.description
        }

    }

    companion object {

        private const val ARG_ID = "ARG_ID"
        const val TAG = "ITEM_INFO_FRAGMENT_TAG"

        fun getInstance(
            id: Int
        ): ItemInfoFragment {
            val bundle = Bundle()
            bundle.putInt(ARG_ID, id)
            return ItemInfoFragment().apply {
                arguments = bundle
            }
        }


    }
}