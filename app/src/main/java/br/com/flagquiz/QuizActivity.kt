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
import android.widget.ImageView


class QuizActivity : AppCompatActivity() {
    private lateinit var textInputGuess: TextInputEditText
    data class Flag(val drawableId: Int, val countryName: String)
    private var currentFlagIndex = 0
    private val flags = listOf(
        Flag( drawableId = R.drawable.flag_catar, "CATAR"),
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
            val textViewScores = findViewById<TextView>(R.id.textViewScores)
            textViewScores.text = score.toString()

            displayCurrentFlag()
    }

    private fun displayCurrentFlag() {
        // Encontra o ImageView no seu layout
        val imageViewFlag: ImageView = findViewById(R.id.imageView)
        // Pega a bandeira atual da lista usando o índice
        val currentFlag = quizFlags[currentFlagIndex]
        // Define a imagem da bandeira
        imageViewFlag.setImageResource(currentFlag.drawableId)

        // Opcional, mas recomendado: Atualize o contador de tentativas/perguntas
        val textViewTries: TextView = findViewById(R.id.textViewTries)
        textViewTries.text = "${currentFlagIndex + 1}/5"
    }

    // Substitua seu método buttonGuessClicked por este:
    fun buttonGuessClicked(view: View) {
        // Pega a bandeira da pergunta atual
        val currentFlag = quizFlags[currentFlagIndex]
        val correctAnswer = currentFlag.countryName

        // Pega a resposta do usuário
        val userAnswer = textInputGuess.text.toString()

        // Lógica de verificação e pontuação
        if (userAnswer.equals(correctAnswer, ignoreCase = true)) {
            Toast.makeText(this, "Correto!", Toast.LENGTH_SHORT).show()
            this.score += 20 // Conforme o requisito do projeto
        } else {
            // Apresenta mensagem de erro e subtrai 10 pontos
            Toast.makeText(this, "Incorreto! A resposta era $correctAnswer", Toast.LENGTH_SHORT).show()
        }

        // Atualiza o placar na tela
        val textViewScores: TextView = findViewById(R.id.textViewScores)
        textViewScores.text = score.toString()

        // Avança para a próxima pergunta
        currentFlagIndex++

        // Verifica se o quiz acabou
        if (currentFlagIndex < quizFlags.size) {
            // Se ainda tem perguntas, limpa o campo e mostra a próxima bandeira
            textInputGuess.text?.clear()
            displayCurrentFlag()
        } else {
            // Se acabaram as perguntas, vai para a tela de resultados
            exit()
        }
    }

    // Exit
    fun exit() {
        val scores = this.score.toString()
        val name = intent.getStringExtra("playerName")
        val intent = Intent(this, ResultActivity::class.java)
        intent.putExtra("RESULT", scores)
        intent.putExtra("NAME", name)
        startActivity(intent)
    }
}


//Arquivo para usos futuros:
//Toast.makeText(this, "Your guess: $guess", Toast.LENGTH_SHORT).show()