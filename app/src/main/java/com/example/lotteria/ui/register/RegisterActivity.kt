package com.example.lotteria.ui.register

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Window
import com.example.lotteria.R
import com.example.lotteria.databinding.ActivityRegisterBinding
import com.example.lotteria.ui.login.LoginActivity

class RegisterActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRegisterBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE)
        setContentView(binding.root)

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
                        val intentLogin = Intent(this@RegisterActivity, LoginActivity::class.java)
                        startActivity(intentLogin)
                    }
                }
            }
        }
    }
}