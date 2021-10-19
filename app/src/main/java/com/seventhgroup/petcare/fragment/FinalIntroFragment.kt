package com.seventhgroup.petcare.fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.seventhgroup.petcare.R
import com.seventhgroup.petcare.activity.MainActivity
import com.seventhgroup.petcare.databinding.FragmentIntro3Binding

class FinalIntroFragment: Fragment() {
    private lateinit var binding: FragmentIntro3Binding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentIntro3Binding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.button3.setOnClickListener {
            val mIntent = Intent(requireContext(), MainActivity::class.java)
            startActivity(mIntent)
        }
    }
}