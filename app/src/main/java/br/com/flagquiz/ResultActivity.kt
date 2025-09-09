package br.com.flagquiz

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import android.widget.Button

class ResultActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_result)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val nameRecebido = intent.getStringExtra("NAME")
        val dadoRecebido = intent.getStringExtra("RESULT")
        val textViewScores = findViewById<TextView>(R.id.textViewScoreResult)
        val textViewPlayerName = findViewById<TextView>(R.id.textViewPlayerName)
        textViewScores.text = dadoRecebido
        textViewPlayerName.text = nameRecebido

        val restartButton = findViewById<Button>(R.id.buttonRestart)
    }

    fun restartGame(view: View) {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }
}