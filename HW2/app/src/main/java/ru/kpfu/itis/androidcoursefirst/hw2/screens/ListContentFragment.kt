package ru.kpfu.itis.androidcoursefirst.hw2.screens

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import by.kirich1409.viewbindingdelegate.viewBinding
import com.bumptech.glide.Glide
import ru.kpfu.itis.androidcoursefirst.hw2.MainActivity
import ru.kpfu.itis.androidcoursefirst.hw2.R
import ru.kpfu.itis.androidcoursefirst.hw2.adapter.recycler.AdapterWithMultipleHolders
import ru.kpfu.itis.androidcoursefirst.hw2.dialog.ChangeDatasetBottomSheetFragment
import ru.kpfu.itis.androidcoursefirst.hw2.databinding.FragmentListContentBinding
import ru.kpfu.itis.androidcoursefirst.hw2.repository.ListContentRepository
import ru.kpfu.itis.androidcoursefirst.hw2.ui.decorators.SimpleHorizontalDecorator
import ru.kpfu.itis.androidcoursefirst.hw2.utils.NavigationAction
import ru.kpfu.itis.androidcoursefirst.hw2.utils.getValueInDp

class ListContentFragment : Fragment(R.layout.fragment_list_content) {

    private val viewBinding: FragmentListContentBinding by viewBinding(FragmentListContentBinding::bind)

    private var rvAdapter: AdapterWithMultipleHolders? = null

    private var currLayoutManagerState = "Linear"

    private var isFirstlyCreated = true


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        if (isFirstlyCreated) {
            rvAdapter = AdapterWithMultipleHolders(
                requestManager = Glide.with(requireContext()),
                items = ListContentRepository.getListOfCities(),
                onPicItemCLick = { id ->
                    (requireActivity() as? MainActivity)?.navigate(
                        destination = ItemInfoFragment.getInstance(id = id),
                        destinationTag = ItemInfoFragment.TAG,
                        action = NavigationAction.REPLACE,
                    )
                },
                onListButtonClick = ::onListButtonClick,
                onGridButtonClick = ::onGridButtonClick
            )
            isFirstlyCreated = false
        }


        viewBinding.apply {
            openBottomSheetFap.setOnClickListener {
                val dialog = ChangeDatasetBottomSheetFragment().apply {
                    isCancelable = true
                }

                dialog.show(childFragmentManager, ChangeDatasetBottomSheetFragment.TAG)
            }
        }

        viewBinding.mainRecycler.apply {
            if (currLayoutManagerState == "Linear") {
                this.layoutManager = getLinearLayoutManager()
            } else if (currLayoutManagerState == "Grid") {
                this.layoutManager = getGridLayoutManager()
            }
            adapter = rvAdapter
            addItemDecoration(
                SimpleHorizontalDecorator(
                    marginValue = getValueInDp(
                        value = 16f,
                        ctx = requireContext(),
                    ).toInt()
                )
            )
        }

    }

    fun addLinesToTheList(quantity: Int) {
        rvAdapter?.addElements(quantity)
    }

    fun removeLinesFromTheList(quantity: Int) {
        rvAdapter?.removeElements(quantity)
    }

    private fun onGridButtonClick() {
        viewBinding.mainRecycler.apply {
            if (this.layoutManager is LinearLayoutManager) {
                this.layoutManager = getGridLayoutManager()
                currLayoutManagerState = "Grid"
            }
        }
    }

    private fun onListButtonClick() {
        viewBinding.mainRecycler.apply {
            if (this.layoutManager is GridLayoutManager) {
                this.layoutManager = getLinearLayoutManager()
                currLayoutManagerState = "Linear"
            }
        }
    }

    private fun getGridLayoutManager(): GridLayoutManager {
        val layoutManager = GridLayoutManager(
            requireContext(),
            3,
            RecyclerView.VERTICAL,
            false
        )
        layoutManager.spanSizeLookup = object : GridLayoutManager.SpanSizeLookup() {
            override fun getSpanSize(position: Int): Int {
                return if (position == 0) {
                    3
                } else {
                    1
                }
            }
        }
        return layoutManager
    }

    private fun getLinearLayoutManager(): LinearLayoutManager {
        val layoutManager = LinearLayoutManager(
            requireContext(),
            RecyclerView.VERTICAL,
            false
        )
        return layoutManager
    }

    companion object {

        const val TAG = "LIST_CONTENT_FRAGMENT_TAG"

    }

    // Не знаю, как передать onPicItemClick в адаптер

//    private fun onPicItemClick(id: Int) {
//        (requireActivity() as? MainActivity)?.navigate(
//            destination = ItemInfoFragment.getInstance(id = id),
//            destinationTag = ItemInfoFragment.TAG,
//            action = NavigationAction.REPLACE,
//        )
//    }

}