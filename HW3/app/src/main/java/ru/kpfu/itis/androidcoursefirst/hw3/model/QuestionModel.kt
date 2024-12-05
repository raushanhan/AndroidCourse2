package ru.kpfu.itis.androidcoursefirst.hw3.model

data class QuestionModel (
    val questionText: String,
    val answers: List<AnswerModel>,
    var isAnswered: Boolean
)