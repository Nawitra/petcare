package com.seventhgroup.petcare.adapter

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.seventhgroup.petcare.fragment.FirstIntroFragment
import com.seventhgroup.petcare.fragment.SecondIntroFragment
import com.seventhgroup.petcare.fragment.ThirdIntroFragment

class OnboardingPagerAdapter(activity: AppCompatActivity): FragmentStateAdapter(activity) {
    private val screens = 3

    override fun createFragment(position: Int): Fragment {
        var fragment: Fragment? = null
        when (position) {
            0 -> fragment = FirstIntroFragment()
            1 -> fragment = SecondIntroFragment()
            2 -> fragment = ThirdIntroFragment()
        }
        return fragment as Fragment
    }

    override fun getItemCount(): Int = screens
}