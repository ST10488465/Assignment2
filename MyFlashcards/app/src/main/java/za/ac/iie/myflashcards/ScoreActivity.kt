package za.ac.iie.myflashcards

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class ScoreActivity : AppCompatActivity() {

    private lateinit var txtFinalScore: TextView
    private lateinit var txtMessage: TextView
    private lateinit var btnReview: Button
    private lateinit var btnRestart: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_score)

        txtFinalScore = findViewById(R.id.txtFinalScore)
        txtMessage = findViewById(R.id.txtMessage)
        btnReview = findViewById(R.id.btnReview)
        btnRestart = findViewById(R.id.btnRestart)

        val score = intent.getIntExtra("score", 0)
        val total = intent.getIntExtra("total", 0)

        txtFinalScore.text = "You scored $score out of $total"
        txtMessage.text = if (score >= total / 2) "Well done!" else "Keep practicing!"

        btnReview.setOnClickListener {
            val reviewIntent = Intent(this, ReviewActivity::class.java)
            reviewIntent.putStringArrayListExtra("questions", intent.getStringArrayListExtra("questions"))
            reviewIntent.putStringArrayListExtra("results", intent.getStringArrayListExtra("results"))
            startActivity(reviewIntent)
        }

        btnRestart.setOnClickListener {
            val restartIntent = Intent(this, MainActivity::class.java)
            restartIntent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK
            startActivity(restartIntent)
            finish()
        }
    }
}
