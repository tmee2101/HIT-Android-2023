package com.example.btvn_b4

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class RegisterActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        val edtEnterName: EditText = findViewById(R.id.name_edt)
        val edtEmailR: EditText = findViewById(R.id.email_edt_register)
        val edtEnterPassword: EditText = findViewById(R.id.pass_edt_register)
        val edtRetype: EditText = findViewById(R.id.retypepass_edt)
        val btnConfirmRegister: Button = findViewById(R.id.confirm_register_btn)
        val btnNavToLogin: Button = findViewById(R.id.nav_login_btn)


        btnNavToLogin.setOnClickListener {
            val intent: Intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            finish()
        }

        btnConfirmRegister.setOnClickListener {
            if (edtRetype.text.toString() == edtEnterPassword.text.toString()) {
                val intent: Intent = Intent(this, LoginActivity::class.java)
                val account: Account = Account("${edtEnterName.text}", "${edtEmailR.text}", "${edtEnterPassword.text}")
                intent.putExtra("My account", account)
                startActivity(intent)
                finish()
            } else {
                Toast.makeText(this, "Mật khẩu không đúng", Toast.LENGTH_SHORT) //hiển thị thông báo trong khoảng 2s
            }

        }

    }
}