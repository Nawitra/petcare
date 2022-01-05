package com.seventhgroup.petcare.activity

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.util.Patterns
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.firestore.FirebaseFirestore
import com.seventhgroup.petcare.R
import com.seventhgroup.petcare.databinding.ActivityRegisterBinding
import com.seventhgroup.petcare.model.User
import com.seventhgroup.petcare.utils.FirebaseUtils
import com.seventhgroup.petcare.utils.FirebaseUtils.firebaseAuth
import com.seventhgroup.petcare.utils.FirebaseUtils.firebaseUser

class RegisterActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRegisterBinding
    private lateinit var userUsername: String
    private lateinit var userSn: String
    private lateinit var userEmail: String
    private lateinit var userPassword: String
    private lateinit var uid: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.hide()

        binding.buttonRegister.setOnClickListener {
            userUsername = binding.editRegisterUsername.text.toString().trim()
            userSn = binding.editRegisterSn.text.toString().trim()
            userEmail = binding.editRegisterEmail.text.toString().trim()
            userPassword = binding.editRegisterPassword.text.toString().trim()

            if (checkValidity()) {
                createAccount()
            }
        }

        binding.textRegisterGotoLogin.setOnClickListener {
            val mIntent = Intent(this@RegisterActivity, LoginActivity::class.java)
            startActivity(mIntent)
            finish()
        }
    }

    override fun onStop() {
        super.onStop()
        startService(Intent(this, NotificationService::class.java))
    }

    private fun checkEmpty(): Boolean =
        binding.editRegisterUsername.text.toString().trim().isNotEmpty() &&
                binding.editRegisterSn.text.toString().trim().isNotEmpty() &&
                binding.editRegisterEmail.text.toString().trim().isNotEmpty() &&
                binding.editRegisterPassword.text.toString().trim().isNotEmpty()

    private fun checkUsernameValidity(): Boolean {
        if (userUsername.length < 5 || userUsername.length > 21) {
            return false
        }

        return true
    }

    private fun checkSnValidity(): Boolean {
        if(userSn.length != 9){
            return false
        }
        return true
    }

    private fun checkEmailValidity(): Boolean =
        Patterns.EMAIL_ADDRESS.matcher(userEmail).matches()

    private fun checkPasswordValidity(): Boolean {
        if (userPassword.length < 8 || userPassword.length > 16) {
            return false
        }

        return true
    }

    private fun checkValidity(): Boolean {
        if (!checkEmpty()) {
            Toast.makeText(
                this, getString(R.string.toast_register_empty_field), Toast.LENGTH_SHORT
            ).show()

            return false
        }

        if (!checkUsernameValidity()) {
            Toast.makeText(
                this, getString(R.string.toast_register_invalid_username), Toast.LENGTH_SHORT
            ).show()

            return false
        }

        if (!checkSnValidity()) {
            Toast.makeText(
                this, getString(R.string.toast_register_invalid_sn), Toast.LENGTH_SHORT
            ).show()

            return false
        }

        if (!checkEmailValidity()) {
            Toast.makeText(
                this, getString(R.string.toast_register_invalid_email), Toast.LENGTH_SHORT
            ).show()

            return false
        }

        if (!checkPasswordValidity()) {
            Toast.makeText(
                this, getString(R.string.toast_register_invalid_password), Toast.LENGTH_SHORT
            ).show()

            return false
        }

        return true
    }

    private fun createAccount() {
        val userData = User(
            binding.editRegisterUsername.text.toString(),
            binding.editRegisterSn.text.toString(),
            binding.editRegisterEmail.text.toString(),
            binding.editRegisterPassword.text.toString()
        )

        firebaseAuth.createUserWithEmailAndPassword(userEmail, userPassword)

        val db = FirebaseFirestore.getInstance()

        db.collection("users").document().set(userData)
            .addOnCompleteListener { register ->
                if (register.isSuccessful) {
                    Toast.makeText(
                        this,
                        getString(R.string.toast_register_create_account_successful),
                        Toast.LENGTH_SHORT
                    ).show()

                    val mIntent = Intent(this@RegisterActivity, HistoryActivity::class.java)
                    startActivity(mIntent)
                    finish()
                } else {
                    Toast.makeText(
                        this,
                        getString(R.string.toast_register_create_account_fail),
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }

        val user = FirebaseUtils.firebaseAuth.currentUser
        if (user != null) {
            uid = user.uid

            Log.i("Testing UID", uid)
            db.collection("userData").document(uid)
                .set(userData)
                .addOnSuccessListener {
                    Toast.makeText(this, "Success", Toast.LENGTH_SHORT).show()
                }
                .addOnFailureListener { e ->
                    Toast.makeText(this, "Fail", Toast.LENGTH_SHORT).show()
                }
        }

    }

    override fun onStart() {
        super.onStart()
        val user = firebaseUser
        user?.let {
            val mIntent = Intent(this@RegisterActivity, HistoryActivity::class.java)
            startActivity(mIntent)
            finish()
        }
    }

}