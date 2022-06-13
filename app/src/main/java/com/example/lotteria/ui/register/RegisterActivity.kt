package com.example.lotteria.ui.register

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Window
import android.widget.Toast
import com.example.lotteria.R
import com.example.lotteria.databinding.ActivityRegisterBinding
import com.example.lotteria.ui.login.LoginActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class RegisterActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRegisterBinding
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE)
        setContentView(binding.root)

        auth = Firebase.auth

        binding.apply {
            registerButton.setOnClickListener {
                val username = usernameEditText.text.toString()
                val email = emailEditText.text.toString()
                val password = passwordEditText.text.toString()
                val confirm_password = confirmPasswordEditText.text.toString()
                when {
                    username.isEmpty() -> {
                        usernameEditTextLayout.error = "Masukkan username"
                    }
                    email.isEmpty() -> {
                        emailEditTextLayout.error = "Masukkan email"
                    }
                    password.isEmpty() -> {
                        passwordEditTextLayout.error = "Masukkan password"
                    }
                    confirm_password.isEmpty() -> {
                        confirmPasswordEditTextLayout.error = "Masukkan password konfirmasi"
                    }
                    password != confirm_password -> {
                        confirmPasswordEditTextLayout.error = "Masukkan password yang sama"
                    }
                    else -> {
                        createAccount(email, password)
                    }
                }
            }
        }
    }

    public override fun onStart() {
        super.onStart()
        // Check if user is signed in (non-null) and update UI accordingly.
        val currentUser = auth.currentUser
        if(currentUser != null){
            finish()
        }
    }

    private fun createAccount(email: String, password: String) {
        // [START create_user_with_email]
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(this@RegisterActivity) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    Log.d(TAG, "createUserWithEmail:success")
                    val user = auth.currentUser
                    Toast.makeText(this@RegisterActivity,
                        "Akun anda sudah dibuat dan berhasil login",
                        Toast.LENGTH_SHORT).show()
                    updateUI(user)
                } else {
                    // If sign in fails, display a message to the user.
                    Log.w(TAG, "createUserWithEmail:failure", task.exception)
                    Toast.makeText(this@RegisterActivity, "Authentication failed.",
                        Toast.LENGTH_SHORT).show()
                    updateUI(null)
                }
            }
        // [END create_user_with_email]
    }

    private fun updateUI(user: FirebaseUser?) {
        if (user != null){
            finish()
        }
    }

    companion object {
        private const val TAG = "EmailPassword"
    }
}