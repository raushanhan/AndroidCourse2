package ru.kpfu.itis.androidcoursefirst.homework

import android.os.Bundle
import android.text.Editable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import by.kirich1409.viewbindingdelegate.viewBinding
import ru.kpfu.itis.androidcoursefirst.homework.databinding.FragmentFirstBinding

class FirstFragment: Fragment(R.layout.fragment_first) {

    private val viewBinding: FragmentFirstBinding by viewBinding(FragmentFirstBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
        viewBinding.tv.text = arguments?.getString(ENTERED_TEXT_KEY)
    }

    private fun initViews() {
        with(viewBinding) {
            firstButton.setOnClickListener {
                (requireActivity() as? MainActivity)?.getToSecondScreen(textInputEt.text.toString())
            }
            secondButton.setOnClickListener {
                (requireActivity() as? MainActivity)?.getToThirdThroughSecondScreen(textInputEt.text.toString())
            }
            thirdButton.setOnClickListener {
                (requireActivity() as? MainActivity)?.showBottomSheet()
            }

        }
    }

    companion object {
        private const val ENTERED_TEXT_KEY = "ENTERED_TEXT"

        fun getInstance(
            enteredText: String
        ): FirstFragment {
            return FirstFragment().apply {
                arguments = bundleOf(ENTERED_TEXT_KEY to enteredText)
            }
        }
    }
}