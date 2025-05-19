package za.ac.iie.myflashcards

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity2 : AppCompatActivity() {

    // History-themed questions and answers
    private val questions = arrayOf(
        "The Great Wall of China was built to protect against invasions.",
        "World War I ended in 1918.",
        "Napoleon Bonaparte was born in Germany.",
        "The Berlin Wall fell in 1989.",
        "Julius Caesar was the first emperor of Rome."
    )

    private val answers = arrayOf(true, true, false, true, false)

    private var currentQuestionIndex = 0
    private var score = 0
    private val userAnswers = mutableListOf<Boolean>() // ✅ ADDED: To track correct/incorrect answers

    private lateinit var txtQuestions: TextView
    private lateinit var txtResults: TextView
    private lateinit var btnTrue: Button
    private lateinit var btnFalse: Button
    private lateinit var btnNext: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        txtQuestions = findViewById(R.id.txtQuestions)
        txtResults = findViewById(R.id.txtResults)
        btnTrue = findViewById(R.id.btnTrue)
        btnFalse = findViewById(R.id.btnFalse)
        btnNext = findViewById(R.id.btnNext)

        showQuestion()

        btnTrue.setOnClickListener {
            checkAnswer(true)
        }

        btnFalse.setOnClickListener {
            checkAnswer(false)
        }

        btnNext.setOnClickListener {
            nextQuestion()
        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    private fun showQuestion() {
        if (currentQuestionIndex < questions.size) {
            txtQuestions.text = questions[currentQuestionIndex]
            txtResults.text = ""
            btnTrue.isEnabled = true
            btnFalse.isEnabled = true
        }
    }

    private fun checkAnswer(userAnswer: Boolean) {
        val correct = answers[currentQuestionIndex]
        userAnswers.add(userAnswer == correct)

        if (userAnswer == correct) {
            txtResults.text = "Correct!"
            score++
        } else {
            txtResults.text = "Incorrect."
        }

        btnTrue.isEnabled = false
        btnFalse.isEnabled = false
    }

    private fun nextQuestion() {
        currentQuestionIndex++
        if (currentQuestionIndex < questions.size) {
            showQuestion()
        } else {
            val intent = Intent(this, ScoreActivity::class.java)
            intent.putExtra("score", score)
            intent.putExtra("total", questions.size)
            intent.putStringArrayListExtra("questions", ArrayList(questions.toList()))
            intent.putExtra("results", userAnswers.toBooleanArray())
            startActivity(intent)
            finish()
        }
    }
}


