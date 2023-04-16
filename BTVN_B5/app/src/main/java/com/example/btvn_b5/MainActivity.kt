package com.example.btvn_b5

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.btvn_b5.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        //Sau khi đăng nhập thì vào màn Home
        supportFragmentManager.beginTransaction().replace(binding.frame.id, HomeFragment())
            .addToBackStack("Home_fragment").commit()

        //Chuyển sang các fragment khi ấn vào các icon
        binding.home.setOnClickListener {
            supportFragmentManager.beginTransaction().replace(binding.frame.id, HomeFragment())
                .addToBackStack("Home_fragment").commit()
        }

        binding.search.setOnClickListener {
            supportFragmentManager.beginTransaction().replace(binding.frame.id, SearchFragment())
                .addToBackStack("search_fragment").commit()
        }

        binding.profile.setOnClickListener {
            supportFragmentManager.beginTransaction().replace(binding.frame.id, ProfileFragment())
                .addToBackStack("profile_fragment").commit()
        }

        binding.notice.setOnClickListener {
            supportFragmentManager.beginTransaction().replace(binding.frame.id, NoticeFragment())
                .addToBackStack("notice_fragment").commit()
        }
    }
}