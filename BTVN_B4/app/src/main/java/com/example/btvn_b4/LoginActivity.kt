package com.example.btvn_b4

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.Toast

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        val edtEnterEmail: EditText = findViewById(R.id.email_edt_lg)
        val edtEnterPassword: EditText = findViewById(R.id.pass_edt_lg)
        val btnLogin: Button = findViewById(R.id.confirm_login_btn)
        val btnNavToRegister: Button = findViewById(R.id.nav_register_btn)
        val checkBox: CheckBox = findViewById(R.id.checkbox)

        val listAccount:MutableList<Account> = mutableListOf()

        var email = ""
        var password = ""
        var name = ""

        listAccount.add(Account("Mee", "mee@gmail.com", "Mee@123"))

        val saveAccount = getSharedPreferences("PREFERENCE_NAME", MODE_PRIVATE)
        val edit = saveAccount.edit()

        val intent = intent

        btnNavToRegister.setOnClickListener {
            val intent: Intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
        }

        val account: Account? = intent.getSerializableExtra("My account") as? Account
        if (account != null) {
            listAccount.add(account)
            checkBox.isChecked = true
            edit.putString("emailRem", edtEnterEmail.text.toString())
            edit.putString("passwordRem", edtEnterPassword.text.toString())
            edit.commit()
        }

        btnLogin.setOnClickListener {
            for (item in listAccount) {
                if (edtEnterEmail.text.toString() == item.email && edtEnterPassword.text.toString() == item.password) {
                    if (checkBox.isChecked) {
                        edit.putString("emailRem", edtEnterEmail.text.toString())
                        edit.putString("passwordRem", edtEnterPassword.text.toString())
                        edit.commit()
                    } else {
                        edit.remove("emailRem")
                        edit.remove("passwordRem")
                    }

                    val nav: Intent = Intent(this, HomeScreen::class.java)
                    val account = Account(name, email, password)
                    nav.putExtra("My account", account)
                    startActivity(nav)
                } else {
                    Toast.makeText(this, "Sai tên đăng nhập hoặc mật khẩu", Toast.LENGTH_SHORT) //hiển thị thông báo trong khoảng 2s
                }
            }
        }

        email = saveAccount.getString("emailRem", "").toString()
        password = saveAccount.getString("passwordRem", "").toString()
        edtEnterEmail.setText(email)
        edtEnterPassword.setText(password)


    }


}





