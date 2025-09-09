package br.com.flagquiz

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import android.widget.Button
import com.google.android.material.textfield.TextInputEditText
import android.widget.TextView
import android.content.Intent

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val buttonStart = findViewById<Button>(R.id.buttonStart)
        val textView = findViewById<TextInputEditText>(R.id.textInputName)
        val intent = Intent(this, QuizActivity::class.java)

        buttonStart.setOnClickListener {
            val playerName = textView.text.toString()
            intent.putExtra("playerName", playerName)
            startActivity(intent)
        }

    }
}