package com.seventhgroup.petcare.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.FirebaseFirestore
import com.seventhgroup.petcare.R
import com.seventhgroup.petcare.databinding.ActivityLoginBinding
import com.seventhgroup.petcare.databinding.ActivityProfileBinding
import com.seventhgroup.petcare.databinding.FragmentProfileBinding
import com.seventhgroup.petcare.utils.FirebaseUtils
import com.seventhgroup.petcare.utils.FirebaseUtils.db
import com.seventhgroup.petcare.utils.FirebaseUtils.firebaseUser

class ProfileActivity : AppCompatActivity() {
    private lateinit var binding: ActivityProfileBinding
    private lateinit var uid: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.hide()

        val db = FirebaseFirestore.getInstance()
        val user = FirebaseUtils.firebaseAuth.currentUser
        if (user != null) {
            uid = user.uid
        }

        val data = db.collection("userData").document(uid)
            data.get()
                .addOnSuccessListener { document ->
                    if(document != null){
                        binding.textUserUsername.text = document.getString("username")
                        binding.textUserSn.text = document.getString("sn")
                        binding.textUserEmail.text = document.getString("email")
                    }
                    Toast.makeText(this, "Success", Toast.LENGTH_SHORT).show()
            }
            .addOnFailureListener { e ->
                Toast.makeText(this, "Fail", Toast.LENGTH_SHORT).show()
            }

        binding.buttonLogOut.setOnClickListener {
            FirebaseAuth.getInstance().signOut()
            val mIntent = Intent(this, LoginActivity::class.java)
            mIntent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(mIntent)
            Toast.makeText(this, "Signed Out", Toast.LENGTH_SHORT).show()
            this?.finish()
        }

        binding.textUserUsername.setOnClickListener {
            finish();
            overridePendingTransition(0, 0);
            startActivity(getIntent());
            overridePendingTransition(0, 0);
        }

        val profileNav: BottomNavigationView = findViewById(R.id.bottom_navigation_profile)
        profileNav.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.btn_profile -> {
                    Toast.makeText(this, "You are already in the Profile SCREEN", Toast.LENGTH_SHORT).show()
                }
                R.id.btn_history -> {
                    val mIntent = Intent(this@ProfileActivity, HistoryActivity::class.java)
                    startActivity(mIntent)
                    finish()
                }
                R.id.btn_setting -> {
                    val mIntent = Intent(this@ProfileActivity, SettingActivity::class.java)
                    startActivity(mIntent)
                    finish()
                }
            }
            true}
    }
}