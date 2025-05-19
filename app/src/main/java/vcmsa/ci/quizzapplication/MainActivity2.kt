package vcmsa.ci.quizzapplication

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import kotlin.system.exitProcess

class MainActivity2 : AppCompatActivity() {


    private lateinit var txtQuestion: TextView
    private lateinit var truebtn: Button
    private lateinit var falsebtn: Button
    private lateinit var nextbtn: Button
    private lateinit var startbutton: Button
    private lateinit var exitbtn: Button
    private lateinit var checkscorebtn: Button


    private var currentQuestion = 1
    private var isAnswered = false
    private var score = 0


    private val Question = arrayOf(
        "The bmw f90 m5 is a coupe",
        "The bmw m8 is a hyper car",
        "The bmw f80 is a sedan",
        "The bmw x7m is an suv",
        "The bmw e60 m5 has a v10 engine"


    )

    private val correctAnswer = booleanArrayOf(
        false, false, true, true, true
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main2)


        txtQuestion = findViewById(R.id.txtQuestion) // 🔧 Missing initialization added
        truebtn = findViewById(R.id.truebtn)
        falsebtn = findViewById(R.id.falsebtn)
        nextbtn = findViewById(R.id.nextbtn)
        exitbtn = findViewById(R.id.exitbtn)
        checkscorebtn = findViewById(R.id.checkscorebtn)
        startbutton = findViewById(R.id.startbutton)

        truebtn.isEnabled = false
        falsebtn.isEnabled = false
        nextbtn.isEnabled = false
        checkscorebtn.isEnabled = false


        startbutton.setOnClickListener { startquestion() }
        checkscorebtn.setOnClickListener {
            val intent = Intent(this, MainActivity3::class.java)
            intent.putExtra("score", score)
            startActivity(intent)
        }
        truebtn.setOnClickListener { checkAnswer(true) }
        falsebtn.setOnClickListener { checkAnswer(false) }

        nextbtn.setOnClickListener { nextquestion() }

        exitbtn.setOnClickListener {
            finishAffinity()
            exitProcess(0)
        }


    }


    private fun checkAnswer(answer: Boolean) {
        if (isAnswered) return // Prevent multiple answers

        val index = currentQuestion - 1
        if (correctAnswer[index] == answer) {
            score++ //add to the score
            txtQuestion.text = "Correct!"
        } else {
            txtQuestion.text = "Incorrect!"
        }

        isAnswered = true
        truebtn.isEnabled = false
        falsebtn.isEnabled = false
        nextbtn.isEnabled = true
        checkscorebtn.isEnabled = currentQuestion == Question.size
    }



    private fun startquestion() {
        currentQuestion = 1
        score = 0
        isAnswered = false
        nextbtn.isEnabled = false
        truebtn.isEnabled = true
        falsebtn.isEnabled = true

        updateQuestion()
    }

    // Moves to the next question
    private fun nextquestion() {
        if (currentQuestion < Question.size) {
            currentQuestion++
            isAnswered = false

            truebtn.isEnabled = true
            falsebtn.isEnabled = true
            nextbtn.isEnabled = false
            checkscorebtn.isEnabled = false

            updateQuestion()
        } else {
            showFinalScore()
        }
    }


    @SuppressLint("SetTextI18n")
    private fun updateQuestion() {

        val questionIndex = currentQuestion - 1


        if (questionIndex < Question.size) {
            txtQuestion.text = "$currentQuestion. ${Question[questionIndex]}"
        }



    }


    @SuppressLint("SetTextI18n")
    private fun showFinalScore() {
        val percentage = (score * 100) / Question.size
        val message = when {
            percentage < 20 -> "You can do better, try again!"
            percentage < 40 -> "Give it another go—you can do better!"
            percentage < 60 -> "Good, but not your best!"
            percentage < 80 -> "Great!"
            else -> "Excellent!"
        }

        txtQuestion.text = "You have completed the quiz!\nYour score: $score ($percentage%)\n\n$message"


        truebtn.isEnabled = false
        falsebtn.isEnabled = false
        nextbtn.isEnabled = false
        checkscorebtn.isEnabled = true
    }
}
