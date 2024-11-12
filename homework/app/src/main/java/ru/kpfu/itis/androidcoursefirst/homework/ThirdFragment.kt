package ru.kpfu.itis.androidcoursefirst.homework

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat.getString
import androidx.core.os.bundleOf
import by.kirich1409.viewbindingdelegate.viewBinding
import ru.kpfu.itis.androidcoursefirst.homework.SecondFragment.Companion
import ru.kpfu.itis.androidcoursefirst.homework.databinding.FragmentThirdBinding

class ThirdFragment : Fragment(R.layout.fragment_third) {

    private val viewBinding: FragmentThirdBinding by viewBinding(FragmentThirdBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewBinding.thirdFragTv.text = arguments?.getString(ENTERED_TEXT_KEY)
    }

    companion object {
        private const val ENTERED_TEXT_KEY = "ENTERED_TEXT"

        fun getInstance(
            enteredText: String,
            context: Context
        ): ThirdFragment {
            val text: String = enteredText.ifEmpty {
                getString(context, R.string.standard_text)
            }
            return ThirdFragment().apply {
                arguments = bundleOf(ENTERED_TEXT_KEY to text)
            }
        }
    }
}