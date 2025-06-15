package ru.kpfu.itis.customview

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import by.kirich1409.viewbindingdelegate.viewBinding
import ru.kpfu.itis.springpractice.customview.R
import ru.kpfu.itis.springpractice.customview.databinding.FragmentMainBinding

class MainFragment : Fragment() {

    private val viewBinding: FragmentMainBinding by viewBinding(
        FragmentMainBinding::bind
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    companion object {
        const val TAG = "NOTIFICATION_MAKER_FRAGMENT"
    }
}