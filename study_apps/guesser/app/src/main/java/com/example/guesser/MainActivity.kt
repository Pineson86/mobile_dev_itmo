package com.example.guesser

import android.os.Bundle
import android.widget.Button
import android.widget.SeekBar
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import kotlin.random.Random

class MainActivity : AppCompatActivity() {

    private lateinit var numberTextView: TextView
    private lateinit var seekBar: SeekBar
    private lateinit var resetButton: Button

    private var targetNumber = 0
    private var attempts = 0
    private var totalScore = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        numberTextView = findViewById(R.id.numberTextView)
        seekBar = findViewById(R.id.seekBar)
        resetButton = findViewById(R.id.resetButton)

        resetGame()

        seekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {}
            override fun onStartTrackingTouch(seekBar: SeekBar?) {}

            override fun onStopTrackingTouch(seekBar: SeekBar?) {
                if (attempts >= 3) return

                attempts++
                val guess = seekBar?.progress ?: 0
                val score = calculateScore(guess)
                totalScore += score

                // показываем результат попытки
                showAttemptResult(attempts, guess, score)
            }
        })

        resetButton.setOnClickListener {
            resetGame()
        }
    }

    private fun resetGame() {
        targetNumber = Random.nextInt(0, 101)
        numberTextView.text = targetNumber.toString()
        attempts = 0
        totalScore = 0
        seekBar.progress = 50
    }

    private fun calculateScore(guess: Int): Int {
        val diff = kotlin.math.abs(targetNumber - guess)
        return when {
            diff <= 5 -> 10
            diff <= 10 -> 5
            else -> 0
        }
    }

    private fun showAttemptResult(attempt: Int, guess: Int, score: Int) {
        val message = "Вы выбрали $guess. Ваш балл: $score"
        val builder = AlertDialog.Builder(this)
            .setTitle("Попытка $attempt")
            .setMessage(message)
            .setPositiveButton("OK") { dialog, _ ->
                dialog.dismiss()
                // после закрытия окна третьей попытки показать итог
                if (attempt == 3) {
                    showGameEndResult()
                }
            }

        builder.show()
    }

    private fun showGameEndResult() {
        AlertDialog.Builder(this)
            .setTitle("Игра окончена!")
            .setMessage("После трёх попыток вы набрали $totalScore баллов.")
            .setPositiveButton("OK", null)
            .show()
    }
}
