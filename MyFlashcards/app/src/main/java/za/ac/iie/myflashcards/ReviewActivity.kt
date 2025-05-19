package za.ac.iie.myflashcards

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class ReviewActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_review)

        val questions = intent.getStringArrayListExtra("questions")
        val results = intent.getBooleanArrayExtra("results")

        val reviewTextView = findViewById<TextView>(R.id.reviewText)

        val reviewText = questions?.mapIndexed { index, question ->
            val status = if (results?.get(index) == true) "✅ Correct" else "❌ Incorrect"
            "Q${index + 1}: $question\nResult: $status"
        }?.joinToString("\n\n") ?: "No review data available."

        reviewTextView.text = reviewText
    }
}

