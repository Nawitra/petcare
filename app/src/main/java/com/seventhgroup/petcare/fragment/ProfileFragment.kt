package com.seventhgroup.petcare.fragment

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.DocumentSnapshot
import com.seventhgroup.petcare.activity.LoginActivity
import com.seventhgroup.petcare.databinding.FragmentProfileBinding
import com.seventhgroup.petcare.utils.FirebaseUtils.db
import com.seventhgroup.petcare.utils.FirebaseUtils.firebaseUser

class ProfileFragment: Fragment() {
    private lateinit var binding: FragmentProfileBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentProfileBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        
        db.collection("users").whereEqualTo("email", firebaseUser?.email).get().addOnCompleteListener { fetch ->
            if(fetch.isSuccessful) {
                for(document: DocumentSnapshot in fetch.result!!) {
                    binding.textUserUsername.text = document.get("username").toString()
                    binding.textUserPhoneNumber.text = "0" + document.get("phoneNumber").toString()
                    binding.textUserEmail.text = document.get("email").toString()
                }
            }
        }

        binding.buttonLogOut.setOnClickListener { 
            FirebaseAuth.getInstance().signOut()
            val mIntent = Intent(activity, LoginActivity::class.java)
            mIntent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(mIntent)
            Toast.makeText(activity, "Signed Out", Toast.LENGTH_SHORT).show()
            activity?.finish()
        }
    }
}