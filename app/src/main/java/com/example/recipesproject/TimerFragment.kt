package com.example.recipesproject

import android.os.Bundle
import android.os.CountDownTimer
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.recipesproject.databinding.FragmentTimerBinding

class TimerFragment : Fragment() {
    private lateinit var binding: FragmentTimerBinding
    private lateinit var countDownTimer: CountDownTimer
    private var timeLeftInMillis: Long = 0
    private var isTimerRunning = false

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentTimerBinding.inflate(inflater, container, false)

        binding.startButton.setOnClickListener {
            startTimer()
        }

        binding.stopButton.setOnClickListener {
            stopTimer()
        }

        if (savedInstanceState != null) {
            isTimerRunning = savedInstanceState.getBoolean("isTimerRunning", false)
        }

        return binding.root


    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putBoolean("isTimerRunning", isTimerRunning)
    }

    override fun onViewStateRestored(savedInstanceState: Bundle?) {
        super.onViewStateRestored(savedInstanceState)
        if (savedInstanceState != null) {
            isTimerRunning = savedInstanceState.getBoolean("isTimerRunning", false)
        }

        if (isTimerRunning) {
            startTimer()
        }
    }


    private fun startTimer() {
        val inputTime = binding.timeInput.text.toString()
        val timeParts = inputTime.split(":")
        if (timeParts.size != 2) {
            Toast.makeText(context, "Input time in format mm:ss", Toast.LENGTH_SHORT).show()
            return
        }

        val minutes = timeParts[0].toLongOrNull()
        val seconds = timeParts[1].toLongOrNull()

        if (minutes == null || seconds == null || minutes < 0 || seconds < 0) {
            Toast.makeText(context, "Input time in format mm:ss", Toast.LENGTH_SHORT).show()
            return
        }

        timeLeftInMillis = (minutes * 60 + seconds) * 1000

        countDownTimer = object : CountDownTimer(timeLeftInMillis, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                timeLeftInMillis = millisUntilFinished
                updateCountDownText()

            }

            override fun onFinish() {
                timeLeftInMillis = 0
                updateCountDownText()

            }
        }.start()

        isTimerRunning = true
    }

    private fun stopTimer() {

        if (::countDownTimer.isInitialized) {
            countDownTimer.cancel()
            isTimerRunning = false
        }
        timeLeftInMillis = 0
        updateCountDownText()
    }

    private fun updateCountDownText() {
        if (timeLeftInMillis <= 0) {
            // Показать сообщение о завершении таймера, если время вышло
            Toast.makeText(context, "Time is up!", Toast.LENGTH_SHORT).show()
            return
        }

        val minutes = (timeLeftInMillis / 1000) / 60
        val seconds = (timeLeftInMillis / 1000) % 60
        val timeLeftFormatted = String.format("%02d:%02d", minutes, seconds)
        binding.timeInput.setText(timeLeftFormatted)
    }
}
