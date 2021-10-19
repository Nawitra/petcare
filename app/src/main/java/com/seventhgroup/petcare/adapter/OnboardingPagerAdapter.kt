package com.seventhgroup.petcare.adapter

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.seventhgroup.petcare.fragment.FinalIntroFragment
import com.seventhgroup.petcare.fragment.FirstIntroFragment
import com.seventhgroup.petcare.fragment.SecondIntroFragment

class OnboardingPagerAdapter(activity: AppCompatActivity): FragmentStateAdapter(activity) {
    private val screens = 3

    override fun createFragment(position: Int): Fragment {
        var fragment: Fragment? = null
        when (position) {
            0 -> FirstIntroFragment()
            1 -> SecondIntroFragment()
            2 -> FinalIntroFragment()
        }
        return fragment as Fragment
    }

    override fun getItemCount(): Int = screens
}