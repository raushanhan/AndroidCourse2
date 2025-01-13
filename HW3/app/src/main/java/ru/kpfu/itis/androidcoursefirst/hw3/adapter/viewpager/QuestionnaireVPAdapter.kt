package ru.kpfu.itis.androidcoursefirst.hw3.adapter.viewpager

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import ru.kpfu.itis.androidcoursefirst.hw3.fragments.vpquestionlist.QuestionFragment
import ru.kpfu.itis.androidcoursefirst.hw3.model.QuestionModel

class QuestionnaireVPAdapter(
    manager: FragmentManager,
    lifecycle: Lifecycle,
    private var questionList: List<QuestionModel>
) : FragmentStateAdapter(manager, lifecycle) {

    override fun getItemCount(): Int = questionList.size

    override fun createFragment(position: Int): Fragment {
        return QuestionFragment.getInstance(position)
    }
}