package com.example.btvn_b4

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class HomeScreen : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home_screen)

        val intent = intent
        val account: Account? = intent.getSerializableExtra("My account") as? Account

        val txtName: TextView = findViewById(R.id.txt_name)
        val txtEmail: TextView = findViewById(R.id.txt_email )
        val btnLogout: Button = findViewById(R.id.logout_btn)

        if (account != null) {
            txtName.text = account.name
            txtEmail.text = account.email
        }

        btnLogout.setOnClickListener {
            val deliver = getSharedPreferences("PREFERENCE_NAME", MODE_PRIVATE).edit()
            if (account != null) {
                deliver.putString("email", account.email)
                deliver.putString("password", account.password)
            }
            deliver.commit()
            val nav: Intent = Intent(this, LoginActivity::class.java)
//            intent.putExtra("My account", account)
            startActivity(nav)
            finish()
        }

    }
}