package ru.kpfu.itis.androidcoursefirst.hw2.dialog

import android.os.Bundle
import android.view.View
import androidx.core.widget.addTextChangedListener
import by.kirich1409.viewbindingdelegate.viewBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import ru.kpfu.itis.androidcoursefirst.hw2.R
import ru.kpfu.itis.androidcoursefirst.hw2.databinding.DialogChangeDatasetBottomSheetBinding
import ru.kpfu.itis.androidcoursefirst.hw2.screens.ListContentFragment

class ChangeDatasetBottomSheetFragment : BottomSheetDialogFragment(R.layout.dialog_change_dataset_bottom_sheet) {

    val viewBinding: DialogChangeDatasetBottomSheetBinding by viewBinding(DialogChangeDatasetBottomSheetBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewBinding.apply {

            textInputEt.addTextChangedListener {
                for (btn in listOf(addSeveralBtn, removeSeveralBtn)) {
                    btn.isEnabled = textInputEt.text.toString().isNotEmpty()
                }
            }

            addSeveralBtn.setOnClickListener {
                val quantity = textInputEt.text.toString().toInt()
                (parentFragment as ListContentFragment).addLinesToTheList(quantity)
            }

            removeSeveralBtn.setOnClickListener {
                val quantity = textInputEt.text.toString().toInt()
                (parentFragment as ListContentFragment).removeLinesFromTheList(quantity)
            }

            addSingleBtn.setOnClickListener {
                (parentFragment as ListContentFragment).addLinesToTheList(1)
            }

            removeSingleBtn.setOnClickListener {
                (parentFragment as ListContentFragment).removeLinesFromTheList(1)
            }
        }
    }

    companion object {
        const val TAG = "CHANGE_DATASET_BOTTOM_SHEET_TAG"
    }
}