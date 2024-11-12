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
import ru.kpfu.itis.androidcoursefirst.homework.databinding.FragmentSecondBinding


class SecondFragment : Fragment(R.layout.fragment_second) {

    private val viewBinding: FragmentSecondBinding by viewBinding(FragmentSecondBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
        viewBinding.secondFragTv.text = arguments?.getString(ENTERED_TEXT_KEY)
    }

    private fun initViews() {

        with(viewBinding) {
            secondFragBtn.setOnClickListener {
                (requireActivity() as? MainActivity)?.getToThirdScreen(secondFragTv.text.toString())
            }
        }
    }

    companion object {
        private const val ENTERED_TEXT_KEY = "ENTERED_TEXT"

        fun getInstance(
            enteredText: String,
            context: Context
        ): SecondFragment {
            val text: String = enteredText.ifEmpty {
                getString(context, R.string.standard_text)
            }
            return SecondFragment().apply {
                arguments = bundleOf(ENTERED_TEXT_KEY to text)
            }
        }
    }
}