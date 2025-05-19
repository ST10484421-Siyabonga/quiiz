package vcmsa.ci.quizzapplication

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import kotlin.system.exitProcess

class MainActivity3 : AppCompatActivity() {
    private var checkScore: TextView? = null
    private var corrections: TextView? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main3)

        corrections = findViewById(R.id.corrections)
        checkScore = findViewById(R.id.checkScore)

        val retrybtn = findViewById<Button>(R.id.retrybtn)
        val exitbtn = findViewById<Button>(R.id.exitbtn)
        val scorebtn = findViewById<Button>(R.id.scorebtn)

        val score = intent.getIntExtra("score", 0)

        checkScore?.text = "Your final score: $score"

        val questions = arrayOf(
            "The bmw f90 m5 is a coupe",
            "The bmw m8 is a hyper car",
            "The bmw f80 is a sedan",
            "The bmw x7m is an suv",
            "The bmw e60 m5 has a v10 engine"
        )

        val answers = booleanArrayOf(
            false, false, true, true, true
        )

        val feedback = StringBuilder()
        for (i in questions.indices) {
            val correctText = if (answers[i]) "True" else "False"
            feedback.append("${i + 1}. ${questions[i]} — Correct Answer: $correctText\n")
        }

        corrections?.text = feedback.toString()

        exitbtn.setOnClickListener {
            finishAffinity()
            exitProcess(0)
        }

        retrybtn.setOnClickListener {
            val intent = Intent(this, MainActivity2::class.java)
            startActivity(intent)
            finish()
        }

        scorebtn.setOnClickListener {
            checkScore?.text = "Your final score: $score"
        }
    }

}