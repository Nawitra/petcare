package com.seventhgroup.petcare.fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.seventhgroup.petcare.activity.LoginActivity
import com.seventhgroup.petcare.databinding.ActivityRegisterBinding
import com.seventhgroup.petcare.databinding.FragmentNotificationBinding

class NotificationFragment: Fragment() {
    private lateinit var binding: FragmentNotificationBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentNotificationBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }
}