package com.example.simple_counter

import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.simple_counter.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private var counterValue = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.clickMeButton.setOnClickListener {
            counterValue++
            binding.counterTextView.text = counterValue.toString()
            if (counterValue == 10) {
                showAlertDialog("Счетчик досчитал до конца", "Счетчик сброшен") {
                    counterValue = 0
                    binding.counterTextView.text = counterValue.toString()
                }
            }
        }

        binding.stopButton.setOnClickListener {
            showAlertDialog("Счетчик сброшен", "") {
                counterValue = 0
                binding.counterTextView.text = counterValue.toString()
            }
        }
    }

    private fun showAlertDialog(title: String, message: String, onDismiss: () -> Unit) {
        val builder = AlertDialog.Builder(this)
        builder.setTitle(title)
        if (message.isNotEmpty()) {
            builder.setMessage(message)
        }
        builder.setPositiveButton("OK") { dialog, _ ->
            dialog.dismiss()
            onDismiss()
        }
        builder.setCancelable(false) // Предотвращает закрытие по нажатию вне диалога
        val alertDialog = builder.create()
        alertDialog.show()
    }
}