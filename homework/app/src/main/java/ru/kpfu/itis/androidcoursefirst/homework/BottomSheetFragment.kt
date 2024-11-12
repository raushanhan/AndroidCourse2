package ru.kpfu.itis.androidcoursefirst.homework

import android.os.Bundle
import android.view.View
import androidx.core.widget.addTextChangedListener
import by.kirich1409.viewbindingdelegate.viewBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import ru.kpfu.itis.androidcoursefirst.homework.databinding.FragmentBottomSheetBinding

class BottomSheetFragment : BottomSheetDialogFragment(R.layout.fragment_bottom_sheet) {

    private val viewBinding: FragmentBottomSheetBinding by viewBinding(FragmentBottomSheetBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
    }

    private fun initViews() {
        with(viewBinding) {
            btmShtTextInputEt.addTextChangedListener {
                btmShtBtn.isEnabled = btmShtTextInputEt.text.toString().isNotEmpty()
            }

            btmShtBtn.setOnClickListener {
                val text = btmShtTextInputEt.text.toString()
                (requireActivity() as? MainActivity)?.passTextToFirstFragment(btmShtTextInputEt.text.toString())
            }
        }
    }

}