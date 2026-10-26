package com.example.quizzapp.utlis

import com.example.quizzapp.R
import com.example.quizzapp.model.Question

object Constants {
    const val USER_NAME = "user_name"
    const val TOTAL_QUESTIONS = "total_questions"
    const val SCORE = "correct_answers"

    fun getQuestions(): MutableList<Question> {
        val questions = mutableListOf<Question>()

        val question1 = Question(
            1,
            "What country does this flag belong?",
            R.drawable.italy_flag,
            "Italy",
            "India",
            "Iran",
            "Irleand",
            1
        )

        questions.add(question1)

        val question2 = Question(
            2,
            "What country does this flag belong?",
            R.drawable.argentina_flag,
            "Uruguay",
            "Argentina",
            "Brazil",
            "Chile",
            2
        )
        questions.add(question2)

        val question3 = Question(
            3,
            "What country does this flag belong?",
            R.drawable.brazil_flag,
            "Portugal",
            "Argentina",
            "Brazil",
            "Colombia",
            3
        )
        questions.add(question3)

        val question4 = Question(
            4,
            "What country does this flag belong?",
            R.drawable.finland_flag,
            "Sweden",
            "Finland",
            "Norway",
            "Iceland",
            2
        )
        questions.add(question4)

        val question5 = Question(
            5,
            "What country does this flag belong?",
            R.drawable.france_flag,
            "France",
            "Italy",
            "Netherlands",
            "Russia",
            1
        )
        questions.add(question5)

        val question6 = Question(
            6,
            "What country does this flag belong?",
            R.drawable.germany_flag,
            "Belgium",
            "Austria",
            "Switzerland",
            "Germany",
            4
        )
        questions.add(question6)

        val question7 = Question(
            7,
            "What country does this flag belong?",
            R.drawable.india_flag,
            "Ireland",
            "India",
            "Pakistan",
            "Bangladesh",
            2
        )
        questions.add(question7)

        val question8 = Question(
            8,
            "What country does this flag belong?",
            R.drawable.poland_flag,
            "Monaco",
            "Indonesia",
            "Poland",
            "Austria",
            3
        )
        questions.add(question8)

        val question9 = Question(
            9,
            "What country does this flag belong?",
            R.drawable.romania_flag,
            "Chad",
            "Moldova",
            "Andorra",
            "Romania",
            4
        )
        questions.add(question9)

        val question10 = Question(
            10,
            "What country does this flag belong?",
            R.drawable.spain_flag,
            "Portugal",
            "Spain",
            "Mexico",
            "Italy",
            2
        )
        questions.add(question10)

        return questions
    }
}