package com.example.btvn_b8

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.btvn_b8.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
    }
}