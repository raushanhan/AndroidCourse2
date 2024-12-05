package ru.kpfu.itis.androidcoursefirst.hw3.fragments.viewpager

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import by.kirich1409.viewbindingdelegate.viewBinding
import com.google.android.material.snackbar.Snackbar
import ru.kpfu.itis.androidcoursefirst.hw3.R
import ru.kpfu.itis.androidcoursefirst.hw3.adapter.viewpager.QuestionnaireVPAdapter
import ru.kpfu.itis.androidcoursefirst.hw3.databinding.FragmentViewPagerBinding
import ru.kpfu.itis.androidcoursefirst.hw3.model.QuestionModel
import ru.kpfu.itis.androidcoursefirst.hw3.repository.QuestionnaireRepository

class ViewPagerFragment : Fragment(R.layout.fragment_view_pager) {

    private val viewBinding: FragmentViewPagerBinding by viewBinding(FragmentViewPagerBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //initAdapter()
        val vpAdapter = QuestionnaireVPAdapter(
            lifecycle = this.lifecycle,
            manager = parentFragmentManager,
            questionList = QuestionnaireRepository.getQuestions()
        )

        with(viewBinding) {
            viewPager.apply {
                adapter = vpAdapter

                registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {

                    override fun onPageSelected(position: Int) {
                        pageNumberTv.text = context?.getString(
                            R.string.page_count_tv_text,
                            position + 1,
                            vpAdapter.itemCount
                        )
                        prevBtn.isEnabled = position > 0

                        if (position == vpAdapter.itemCount - 1) {
                            nextBtn.isEnabled = getAnsweredQuestionsCount(QuestionnaireRepository.getQuestions()) == vpAdapter.itemCount
                            nextBtn.setText(R.string.send_btn_text)
                            nextBtn.setBackgroundColor(
                                ContextCompat.getColor(
                                    requireContext(),
                                    R.color.green
                                )
                            )
                        } else {
                            nextBtn.isEnabled = true
                            nextBtn.setText(R.string.next_btn_text)
                            nextBtn.setBackgroundColor(
                                ContextCompat.getColor(
                                    requireContext(),
                                    R.color.default_btn_purple
                                )
                            )
                        }
                    }
                })
            }

            nextBtn.setOnClickListener {
                with(viewPager) {
                    if (currentItem < vpAdapter.itemCount - 1) {
                        currentItem += 1

                    }
                    else {
                        Snackbar.make(root, R.string.snackbar_text, Snackbar.LENGTH_LONG).show()
                    }
                }

            }

            prevBtn.setOnClickListener {
                with(viewPager) {
                    if (currentItem > 0)
                        currentItem -= 1
                }
            }


        }
    }

    fun getAnsweredQuestionsCount(questions: List<QuestionModel>): Int {
        var answeredQuestions = 0

        for (item in questions) {
            if (item.isAnswered) {
                answeredQuestions++
            } else {
                for (ans in item.answers) {
                    if (ans.isChosen) {
                        answeredQuestions++
                        item.isAnswered = true
                        break
                    }
                }
            }
        }

        return answeredQuestions
    }

    private fun initAdapter() {

    }

    private fun initButtons() {

    }

    companion object {
        const val TAG = "VP_FRAGMENT_TAG"
    }
}