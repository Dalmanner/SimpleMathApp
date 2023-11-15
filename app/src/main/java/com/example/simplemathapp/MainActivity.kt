package com.example.simplemathapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    lateinit var questionView: TextView
    lateinit var answerView: EditText
    var correctAnswer : Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        questionView = findViewById(R.id.questionView)
        answerView = findViewById(R.id.answerView)

        val button = findViewById<Button>(R.id.answerButton)

        button.setOnClickListener {
            handleAnswer()
        }

    }

    override fun onResume() {
        super.onResume()
        setNewQuestion()
    }

    fun handleAnswer() {
        val answeredCorrect = checkAnswer()

        Log.d("!!!", "Du svarade $answeredCorrect")
        val intent = Intent(this, AnswerActivity::class.java )
        intent.putExtra("answeredCorrect", answeredCorrect)

        startActivity(intent)

    }

    fun checkAnswer() : Boolean {
        // kolla vad användaren har svarat
        val answerText = answerView.text.toString()
        val answer = answerText.toIntOrNull() //gör om till Int, annars till null, men iaf ingen crash

        //jämföra användarens svar med rätta svaret
        return answer == correctAnswer
    }

    fun setNewQuestion() {
        val firstNumber = (1..10).random()
        val secondtNumber = (1..10).random()

        correctAnswer = firstNumber + secondtNumber

        questionView.text = "$firstNumber + $secondtNumber ="
    }
}