package com.example.quizzapp.ui

import android.content.Intent
import android.os.Bundle
import android.provider.SyncStateContract
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.quizzapp.MainActivity
import com.example.quizzapp.R
import com.example.quizzapp.utlis.Constants

class ResultActivity : AppCompatActivity() {
    private lateinit var scoreTextView: TextView
    private lateinit var usernameTextView: TextView
    private lateinit var finishButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_result)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        scoreTextView = findViewById<TextView>(R.id.score)
        usernameTextView = findViewById<TextView>(R.id.name)
        finishButton = findViewById<Button>(R.id.finish)

        val totalQuestions = intent.getIntExtra(Constants.TOTAL_QUESTIONS, 0)
        val score = intent.getIntExtra(Constants.SCORE, 0)
        val name = intent.getStringExtra(Constants.USER_NAME)

        scoreTextView.text = getString(R.string.score_template, score, totalQuestions)
        usernameTextView.text = name

        finishButton.setOnClickListener {
            Intent(this, MainActivity::class.java).also {
                startActivity(it)
                finish()
            }
        }
    }
}