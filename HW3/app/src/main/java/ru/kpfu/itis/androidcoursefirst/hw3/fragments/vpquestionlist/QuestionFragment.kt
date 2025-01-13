package ru.kpfu.itis.androidcoursefirst.hw3.fragments.vpquestionlist

import android.os.Bundle
import android.view.View
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import by.kirich1409.viewbindingdelegate.viewBinding
import ru.kpfu.itis.androidcoursefirst.hw3.R
import ru.kpfu.itis.androidcoursefirst.hw3.adapter.recycler.AnswersRVAdapter
import ru.kpfu.itis.androidcoursefirst.hw3.databinding.FragmentQuestionBinding
import ru.kpfu.itis.androidcoursefirst.hw3.model.AnswerModel
import ru.kpfu.itis.androidcoursefirst.hw3.repository.QuestionnaireRepository.getQuestionByPosition

class QuestionFragment : Fragment(R.layout.fragment_question) {

    private val viewBinding: FragmentQuestionBinding by viewBinding(FragmentQuestionBinding::bind)

    private var rvAdapter: AnswersRVAdapter? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)



        arguments?.getInt(POSITION_KEY)?.let { position ->
            val answers: List<AnswerModel>
            val questionText: String

            val question = getQuestionByPosition(position)

            answers = question.answers
            questionText = question.questionText

            rvAdapter = AnswersRVAdapter(
                answers = answers,
                onClickAction = ::onAnswerClickAction,
            )

            viewBinding.apply {
                questionTv.text = questionText

                mainRecycler.adapter = rvAdapter
            }
        }
    }

    private fun onAnswerClickAction() {
    }

    companion object {

        const val POSITION_KEY = "POSITION"

        fun getInstance(position: Int) = QuestionFragment().apply {
            arguments = bundleOf(POSITION_KEY to position)
        }
    }
}