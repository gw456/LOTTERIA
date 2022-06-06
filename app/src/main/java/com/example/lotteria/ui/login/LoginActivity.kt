package com.example.lotteria.ui.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Window
import androidx.fragment.app.commit
import com.example.lotteria.R
import com.example.lotteria.databinding.ActivityLoginBinding
import com.example.lotteria.ui.register.RegisterActivity
import com.example.lotteria.ui.setting.SettingPageFragment

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE)
        setContentView(binding.root)

        binding.apply {
            loginButton.setOnClickListener {
                val email = emailEditText.text.toString()
                val password = passwordEditText.text.toString()
                when {
                    email.isEmpty() -> {
                        emailEditTextLayout.error = "Masukkan email"
                    }
                    password.isEmpty() -> {
                        passwordEditTextLayout.error = "Masukkan password"
                    }
                    else -> {
                        finish()
                    }
                }
            }
            register.setOnClickListener {
                val intentRegistrasi = Intent(this@LoginActivity, RegisterActivity::class.java)
                startActivity(intentRegistrasi)
            }
        }
    }
}