package com.seventhgroup.petcare.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.google.firebase.firestore.FirebaseFirestore
import com.seventhgroup.petcare.databinding.FragmentSettingBinding
import com.seventhgroup.petcare.model.Pet
import com.seventhgroup.petcare.utils.FirebaseUtils.firebaseAuth

class SettingFragment: Fragment() {
    private lateinit var binding: FragmentSettingBinding
    private lateinit var addPet: String
    private lateinit var addCollar: String
    private lateinit var uid: String

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSettingBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentSettingBinding.inflate(layoutInflater)
        binding.buttonAddPet.setOnClickListener {
            addPetToDb()
        }
    }

    private fun addPetToDb() {
        addPet = binding.addPetName.text.toString().trim()
        addCollar = binding.addCollarSerialNumber.text.toString().trim()

        val delim = ","
        val listPet = addPet.split(delim)
        val listCollar = addCollar.split(delim)

        for(i in listPet.indices){
            val petData = Pet(
            listPet.get(i),
            listCollar.get(i)
            )

            val db = FirebaseFirestore.getInstance()
            val user = firebaseAuth.currentUser
            if (user != null) {
                uid = user.uid
            }
            db.collection("userData").document(uid).collection("pet")
                .add(petData)
                .addOnSuccessListener {
                    Toast.makeText(activity, "Success", Toast.LENGTH_SHORT).show()
                }
                .addOnFailureListener { e ->
                    Toast.makeText(activity, "Fail", Toast.LENGTH_SHORT).show()
                }
            }
        }

}