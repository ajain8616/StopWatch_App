package com.example.stopwatchapp

import android.os.Bundle
import android.os.Handler
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout

class MainActivity : AppCompatActivity() {

    private lateinit var stopwatchTextView: TextView
    private lateinit var startButton: Button
    private lateinit var stopButton: Button
    private lateinit var restartButton: Button
    private lateinit var imageView: ImageView
    private lateinit var layout: ConstraintLayout
    private var running = false
    private var seconds = 0
    private var handler: Handler = Handler()
    private lateinit var runnable: Runnable
    private var currentImage = R.drawable.stopwatch

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        stopwatchTextView = findViewById(R.id.stopwatchTextView)
        startButton = findViewById(R.id.startButton)
        stopButton = findViewById(R.id.stopButton)
        restartButton = findViewById(R.id.restartButton)
        imageView = findViewById(R.id.imageView)
        layout = findViewById(R.id.layout)
        layout.setBackgroundResource(R.color.light_bold_blue)
        startButton.setOnClickListener {
            startStopwatch()
            currentImage = R.drawable.stopwatch
            imageView.setImageResource(currentImage)
            layout.setBackgroundResource(R.color.dark_bold_blue)
        }

        stopButton.setOnClickListener {
            stopStopwatch()
            currentImage = R.drawable.stopwatch1
            imageView.setImageResource(currentImage)
            layout.setBackgroundResource(R.color.dark_bold_white)
        }

        restartButton.setOnClickListener {
            restartStopwatch()
            currentImage = R.drawable.stopwatch2
            imageView.setImageResource(currentImage)
            layout.setBackgroundResource(R.color.dark_bold_cyan)
        }
    }

    private fun startStopwatch() {
        if (!running) {
            running = true
            runnable = object : Runnable {
                override fun run() {
                    seconds++
                    updateStopwatchTime(seconds)
                    handler.postDelayed(this, 100)
                }
            }
            handler.post(runnable)
        }
    }

    private fun stopStopwatch() {
        if (running) {
            running = false
            handler.removeCallbacks(runnable)
        }
    }

    private fun restartStopwatch() {
        stopStopwatch()
        seconds = 0
        updateStopwatchTime(seconds)
    }

    private fun updateStopwatchTime(timeInSeconds: Int) {
        val hours = timeInSeconds / 3600
        val minutes = (timeInSeconds % 3600) / 60
        val secs = timeInSeconds % 60
        val time = String.format("%02d:%02d:%02d", hours, minutes, secs)
        stopwatchTextView.text = time
    }
}