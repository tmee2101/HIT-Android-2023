package com.example.onl

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import com.example.onl.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        val binding: ActivityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)



        binding.btnSum.setOnClickListener {
            calcSum()
        }

        binding.btnShowFragment.setOnClickListener {
            supportFragmentManager.beginTransaction()
                .replace(R.id.frame_container, FirstFragment())
                .commit()
        }
    }

    private fun calcSum() {
        try {
            val numberOne = binding.edtNumberOne.text.toString().toFloat()
            val numberTwo = binding.edtNumberTwo.text.toString().toFloat()
            binding.tvResult.text = "${numberOne + numberTwo}"
        } catch (ex: Exception) {
            binding.tvResult.text = ex.message
        }
    }
}

