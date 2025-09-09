package br.com.flagquiz


import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import android.widget.TextView
import android.view.View
import android.widget.Button
import android.widget.Toast
import com.google.android.material.textfield.TextInputEditText


class QuizActivity : AppCompatActivity() {
    private lateinit var textInputGuess: TextInputEditText
    data class Flag(val drawableId: Int, val countryName: String)
    private val flags = listOf(
        Flag(R.drawable.flag_catar, "CATAR"),
        Flag(R.drawable.flag_canada, "CANADA"),
        Flag(R.drawable.flag_china, "CHINA"),
        Flag(R.drawable.flag_egito, "EGITO"),
        Flag(R.drawable.flag_belgica, "BELGICA"),
        Flag(R.drawable.flag_brasil, "BRASIL"),
        Flag(R.drawable.flag_india, "INDIA"),
        Flag(R.drawable.flag_colombia, "COLOMBIA"),
        Flag(R.drawable.flag_nepal, "NEPAL"),
        Flag(R.drawable.flag_paraguai, "PARAGUAI"),
    )


        private val auxFlagList = flags.shuffled()
        val quizFlags = auxFlagList.take(5)

        // Tentativas e score
        var tries = 0
        var score = 0

        override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_quiz)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
            val buttonGuess = findViewById<Button>(R.id.buttonGuess)
            val textView = findViewById<TextView>(R.id.textViewPlayer)
            val dadoRecebido = intent.getStringExtra("playerName")
            textView.text = dadoRecebido
            textInputGuess = findViewById(R.id.textInputGuess)
            val textViewTries = findViewById<TextView>(R.id.textViewTries)
            val textViewScores = findViewById<TextView>(R.id.textViewScores)
            textViewTries.text = tries.toString()
            textViewScores.text = score.toString()
    }

    fun buttonGuessClicked(view: View) {
        if (tries < 5) {
            play(textInputGuess.text.toString(), drawableId, countryName)
            //resetar a tela para o reinicio do jogo
            textInputGuess.text?.clear()
            drawFlag()
        }else {
            exit()
        }
    }

    fun play(guess: String, drawableId: Int, countryName: String){

        if (guess == countryName) {if (guess.isEmpty()) {
            Toast.makeText(this, "Please enter a guess", Toast.LENGTH_SHORT).show()
        } else {
            if (guess.equals(countryName, ignoreCase = true)) {
                Toast.makeText(this, "Correct!", Toast.LENGTH_SHORT).show()
                tries += 1
                score += 1
            }else {
                Toast.makeText(this, "Incorrect!", Toast.LENGTH_SHORT).show()
                tries += 1
                score -= 1
            }
        }}
    }

    // Exit
    fun exit() {
        val intent = Intent(this, ResultActivity::class.java)
        intent.putExtra("RESULT", score)
        startActivity(intent)
    }

    fun drawFlag() {
        randomFlag = flags.random()
        drawableId = randomFlag.drawableId
        countryName = randomFlag.countryName
    }
}


//Arquivo para usos futuros:
//Toast.makeText(this, "Your guess: $guess", Toast.LENGTH_SHORT).show()