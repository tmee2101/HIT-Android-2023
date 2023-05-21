package com.example.buoi8_firebase

import android.app.Dialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.buoi8_firebase.databinding.ActivityMainBinding
import com.example.buoi8_firebase.databinding.DialogInforBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase


class MainActivity : AppCompatActivity() {
    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.createUser.setOnClickListener {
            val email = binding.edtEmail.text.toString().trim()
            val password = binding.edtPassword.text.toString().trim()

            if (email.isEmpty()) {
                Toast.makeText(this, "Vui lòng không để trống email", Toast.LENGTH_LONG).show()
            } else if (password.length < 6) {
                Toast.makeText(this, "Password phải từ 6 kí tự", Toast.LENGTH_LONG).show()
            } else {
                creatUser(email, password)
            }
        }

        binding.btnLogin.setOnClickListener {
            val email = binding.edtEmail.text.toString().trim()
            val password = binding.edtPassword.text.toString().trim()

            login(email, password)
        }

        binding.btnShow.setOnClickListener {
            val email = binding.edtEmail.text.toString().trim()
            val password = binding.edtPassword.text.toString().trim()

            showInfor(email, password)
        }
    }


    fun creatUser(email: String, password: String) {
        val auth = FirebaseAuth.getInstance()
        val db = Firebase.firestore
        auth.createUserWithEmailAndPassword(
            email,
            password
        ).addOnCompleteListener {
            if (it.isSuccessful) {
                Toast.makeText(this, "Successs", Toast.LENGTH_LONG).show()
                val user = hashMapOf(
                    "email" to email,
                    "password" to password,
                    "uid" to it.result.user?.uid.toString()
                )
//
//                db.collection("users")
//                    .add(user)
//                    .addOnSuccessListener { documentReference ->
//                        documentReference
//                    }
//                    .addOnFailureListener { e ->
//                    }


                db.collection("users/")
                    .document(it.result.user?.uid.toString()) //set document
                    .set(user)      //set user cho document
                    .addOnSuccessListener { documentReference ->
                        documentReference
                    }
                    .addOnFailureListener { e ->
                    }

            } else {
                Toast.makeText(this, it.exception?.message, Toast.LENGTH_LONG).show()
            }
        }
    }

    private fun login(email: String, password: String) {
        val auth = FirebaseAuth.getInstance()
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener {
                if (it.isSuccessful) {
                    val dialog = Dialog(this)
                    val dialogBinding = DialogInforBinding.inflate(layoutInflater)
                    dialog.setContentView(dialogBinding.root)
                    dialogBinding.txtInfo.text = "Success"
                    dialog.show()
                } else {
                    it.exception
                }
            }
    }

    private fun showInfor(email: String, password: String) {
        val auth = FirebaseAuth.getInstance()
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener {
                if (it.isSuccessful) {
                    val db = Firebase.firestore
                    val docRef = db.collection("users").document(auth.currentUser?.uid.toString())
                    docRef.get()
                        .addOnSuccessListener { document ->
                            if (document != null) {
                                val dialog = Dialog(this)
                                val dialogBinding = DialogInforBinding.inflate(layoutInflater)
                                dialog.setContentView(dialogBinding.root)
                                dialogBinding.txtInfo.text = document.data.toString()
                                dialog.show()
                            }
                        }
                }
            }
    }
}