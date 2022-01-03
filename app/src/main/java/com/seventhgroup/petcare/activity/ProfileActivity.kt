package com.seventhgroup.petcare.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.hide()

        db.collection("users").whereEqualTo("email", firebaseUser?.email).get().addOnCompleteListener { fetch ->
            if(fetch.isSuccessful) {
                for(document: DocumentSnapshot in fetch.result!!) {
                    binding.textUserUsername.text = document.get("username").toString()
                    binding.textUserSn.text = document.get("sn").toString()
                    binding.textUserEmail.text = document.get("email").toString()
                }
            }
        }

        binding.buttonLogOut.setOnClickListener {
            FirebaseAuth.getInstance().signOut()
            val mIntent = Intent(this, LoginActivity::class.java)
            mIntent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(mIntent)
            Toast.makeText(this, "Signed Out", Toast.LENGTH_SHORT).show()
            this?.finish()
        }
    }
}