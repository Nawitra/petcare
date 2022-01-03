package com.seventhgroup.petcare.activity

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.seventhgroup.petcare.R
import com.seventhgroup.petcare.databinding.ActivityLoginBinding
import com.seventhgroup.petcare.utils.FirebaseUtils.firebaseAuth
import com.seventhgroup.petcare.utils.FirebaseUtils.firebaseUser

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    private lateinit var userEmail: String
    private lateinit var userPassword: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.hide()

        binding.textLoginGotoRegister.setOnClickListener {
            val mIntent = Intent(this@LoginActivity, RegisterActivity::class.java)
            startActivity(mIntent)
            finish()
        }

        binding.buttonLogin.setOnClickListener {
            if (!checkEmpty()) {
                Toast.makeText(
                    this,
                    getString(R.string.toast_login_invalid_email_password),
                    Toast.LENGTH_SHORT
                ).show()
            } else {
                loginUsingEmail()
            }
        }
    }

    private fun checkEmpty(): Boolean =
        binding.editLoginEmail.text.toString().trim().isNotEmpty() &&
                binding.editLoginPassword.text.toString().trim().isNotEmpty()

    private fun loginUsingEmail() {
        userEmail = binding.editLoginEmail.text.toString().trim()
        userPassword = binding.editLoginPassword.text.toString().trim()

        firebaseAuth.signInWithEmailAndPassword(userEmail, userPassword)
            .addOnCompleteListener { login ->
                if (login.isSuccessful) {
                    val mIntent = Intent(this@LoginActivity, ProfileActivity::class.java)
                    startActivity(mIntent)
                    Toast.makeText(
                        this,
                        getString(R.string.toast_login_successful),
                        Toast.LENGTH_SHORT
                    ).show()
                    finish()
                } else {
                    Toast.makeText(this, getString(R.string.toast_login_fail), Toast.LENGTH_SHORT)
                        .show()
                }
            }
    }
    
    override fun onStart() {
        super.onStart()
        val user = firebaseUser
        user?.let {
            val mIntent = Intent(this@LoginActivity, MainActivity::class.java)
            startActivity(mIntent)
            finish()
        }
    }
}