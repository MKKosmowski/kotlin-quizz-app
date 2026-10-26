package com.example.quizzapp.ui

import android.content.Intent
import android.graphics.Typeface
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.quizzapp.R
import com.example.quizzapp.model.Question
import com.example.quizzapp.utlis.Constants
import androidx.core.graphics.toColorInt

class QuestionsActivity : AppCompatActivity() {
    private lateinit var progressBar: ProgressBar
    private lateinit var textViewProgress: TextView
    private lateinit var textViewQuestion: TextView
    private lateinit var flagImage: ImageView

    private lateinit var textViewOption1: TextView
    private lateinit var textViewOption2: TextView
    private lateinit var textViewOption3: TextView
    private lateinit var textViewOption4: TextView

    private lateinit var checkButton: Button

    private var questionCounter = 1
    private lateinit var questionList: MutableList<Question>

    private var selectedAnswer = 0
    private lateinit var currentQuestion: Question
    private var checked = false

    private lateinit var name: String
    private var score = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_questions)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        progressBar = findViewById<ProgressBar>(R.id.progressBar)
        textViewProgress = findViewById<TextView>(R.id.progressTextView)
        textViewQuestion = findViewById<TextView>(R.id.questionTextView)
        flagImage = findViewById<ImageView>(R.id.imageFlag)
        checkButton = findViewById<Button>(R.id.buttonCheck)

        textViewOption1 = findViewById<TextView>(R.id.optionOneTextView)
        textViewOption2 = findViewById<TextView>(R.id.optionTwoTextView)
        textViewOption3 = findViewById<TextView>(R.id.optionThreeTextView)
        textViewOption4 = findViewById<TextView>(R.id.optionFourTextView)

        textViewOption1.setOnClickListener {
            selectOption(textViewOption1, 1)
        }

        textViewOption2.setOnClickListener {
            selectOption(textViewOption2, 2)
        }

        textViewOption3.setOnClickListener {
            selectOption(textViewOption3, 3)
        }

        textViewOption4.setOnClickListener {
            selectOption(textViewOption4, 4)
        }

        questionList = Constants.getQuestions()
        Log.d("QuestionSize", "${questionList.size}")

        checkButton.setOnClickListener {
            if (!checked) {
                checkAnswer()
            } else {
                showNextQuestion()
            }

            selectedAnswer = 0
        }

        showNextQuestion()

        if(intent.hasExtra(Constants.USER_NAME)) {
            name = intent.getStringExtra(Constants.USER_NAME)!!
        }
    }

    private fun showNextQuestion() {


        if (questionCounter < questionList.size) {
            checkButton.text = getString(R.string.check)
            currentQuestion = questionList[questionCounter]

            val question = questionList[questionCounter - 1]
            currentQuestion = question

            resetOptions()

            flagImage.setImageResource(question.image)
            progressBar.progress = questionCounter
            textViewProgress.text = getString(
                R.string.progressTemplate, questionCounter, progressBar.max
            )

            textViewQuestion.text = question.question
            textViewOption1.text = question.optionOne
            textViewOption2.text = question.optionTwo
            textViewOption3.text = question.optionThree
            textViewOption4.text = question.optionFour
        } else {
            checkButton.text = getString(R.string.finish)

            // start activity here
            Intent(this, ResultActivity::class.java).also {
                it.putExtra(Constants.USER_NAME, name)
                it.putExtra(Constants.TOTAL_QUESTIONS, questionList.count())
                it.putExtra(Constants.SCORE, score)

                startActivity(it)
                finish()
            }
        }

        questionCounter++
        checked = false
    }

    private fun resetOptions() {
        val options = mutableListOf<TextView>()
        options.add(textViewOption1)
        options.add(textViewOption2)
        options.add(textViewOption3)
        options.add(textViewOption4)

        for (o in options) {
            o.setTextColor("#7A8089".toColorInt())
            o.background = ContextCompat.getDrawable(this, R.drawable.default_option_border_bg)
            o.typeface = Typeface.DEFAULT
            o.background = ContextCompat.getDrawable(
                this,
                R.drawable.default_option_border_bg
            )
        }
    }

    private fun selectOption(textView: TextView, selectedOptionNumber: Int) {
        resetOptions()

        selectedAnswer = selectedOptionNumber

        textView.setTextColor("#363A43".toColorInt())
        textView.typeface = Typeface.DEFAULT_BOLD
        textView.background = ContextCompat.getDrawable(
            this,
            R.drawable.selected_option_border_bg
        )
    }

    private fun checkAnswer() {
        checked = true

        var background = ContextCompat.getDrawable(this, R.drawable.wrong_option_border_bg)

        if(selectedAnswer == 0) {
            textViewOption1.background = background
            textViewOption2.background = background
            textViewOption3.background = background
            textViewOption4.background = background
        } else {
            if (selectedAnswer == currentQuestion.correctAnswer) {
                background = ContextCompat.getDrawable(this, R.drawable.correct_option_border_bg)
                score++
            }

            highlightAnswer(selectedAnswer, background)
        }

        showSolution()
        checkButton.text = getString(R.string.next)
    }

    private fun showSolution() {
        selectedAnswer = currentQuestion.correctAnswer

        highlightAnswer(selectedAnswer)
    }

    private fun highlightAnswer(answer: Int, background: Drawable? = ContextCompat.getDrawable(this, R.drawable.correct_option_border_bg)) {
        when(answer) {
            1 -> {
                textViewOption1.background =
                    background
            }

            2 -> {
                textViewOption2.background =
                    background
            }

            3 -> {
                textViewOption3.background =
                    background
            }

            4 -> {
                textViewOption4.background =
                    background
            }
        }
    }
}