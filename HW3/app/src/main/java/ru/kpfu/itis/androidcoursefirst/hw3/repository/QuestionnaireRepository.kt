package ru.kpfu.itis.androidcoursefirst.hw3.repository

import ru.kpfu.itis.androidcoursefirst.hw3.model.AnswerModel
import ru.kpfu.itis.androidcoursefirst.hw3.model.QuestionModel

object QuestionnaireRepository {

    private val itemList = listOf<QuestionModel>(
        QuestionModel(
            questionText = "What is 2 + 2?",
            answers =
            listOf(
                AnswerModel(
                    answerText = "4",
                ),
                AnswerModel(
                    answerText = "5",
                ),
                AnswerModel(
                    answerText = "2",
                ),
                AnswerModel(
                    answerText = "22",
                ),
            ),
            isAnswered = false
        ),
        QuestionModel(
            questionText = "What is H2O?",
            answers =
            listOf(
                AnswerModel(
                    answerText = "It's a pepper spray brand",
                ),
                AnswerModel(
                    answerText = "It's a new Coke type",
                ),
                AnswerModel(
                    answerText = "It is a TV series",
                ),
                AnswerModel(
                    answerText = "It is a Hydrogen formula",
                ),
            ),
            isAnswered = false
        ),
        QuestionModel(
            questionText = "Who is the author of War and Peace?",
            answers =
            listOf(
                AnswerModel(
                    answerText = "Aleksandr Pushkin",
                ),
                AnswerModel(
                    answerText = "Gabdulla Tuqay",
                ),
                AnswerModel(
                    answerText = "Leo Tolstoy",
                ),
                AnswerModel(
                    answerText = "J K Rowling",
                ),
            ),
            isAnswered = false
        ),
        QuestionModel(
            questionText = "What is the best country organization type?",
            answers =
            listOf(
                AnswerModel(
                    answerText = "Federative",
                ),
                AnswerModel(
                    answerText = "Unitary",
                )
            ),
            isAnswered = false
        ),
        QuestionModel(
            questionText = "What is a black hole?",
            answers =
            listOf(
                AnswerModel(
                    answerText = "It is a supermassive cosmic object",
                ),
                AnswerModel(
                    answerText = "It is a dead star",
                ),
                AnswerModel(
                    answerText = "It is a planet in Solar System",
                )
            ),
            isAnswered = false
        ),
        QuestionModel(
            questionText = "What language is mostly used in Android Developing?",
            answers =
            listOf(
                AnswerModel(
                    answerText = "Java",
                ),
                AnswerModel(
                    answerText = "Tatar",
                ),
                AnswerModel(
                    answerText = "Kotlin",
                ),
                AnswerModel(
                    answerText = "English",
                ),
                AnswerModel(
                    answerText = "Python",
                ),
            ),
            isAnswered = false
        ),
        QuestionModel(
            questionText = "How do fish breathe?",
            answers =
            listOf(
                AnswerModel(
                    answerText = "Through lungs",
                ),
                AnswerModel(
                    answerText = "Through nose",
                ),
                AnswerModel(
                    answerText = "Through bladder",
                ),
                AnswerModel(
                    answerText = "Through air",
                ),
            ),
            isAnswered = false
        ),
    )

    fun getQuestions() = itemList



    fun getQuestionByPosition(pos: Int) = itemList[pos]
}