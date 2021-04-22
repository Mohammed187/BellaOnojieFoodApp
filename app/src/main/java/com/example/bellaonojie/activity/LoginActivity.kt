package com.example.bellaonojie.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.bellaonojie.R
import com.example.bellaonojie.databinding.ActivityLoginBinding
import com.example.bellaonojie.fragments.LoginFragment
import com.example.bellaonojie.fragments.RegisterFragment
import com.google.android.material.tabs.TabLayoutMediator

class LoginActivity : AppCompatActivity() {

    lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_login)

        init()
    }

    private fun init() {

        val tabs = binding.loginTabsLayout
        val viewPage = binding.loginPager

        val adapter = ScreenAdapter(supportFragmentManager, lifecycle, 2)
        viewPage.adapter = adapter

        TabLayoutMediator(tabs, viewPage) { tab, position ->
            when (position) {
                0 -> {
                    tab.text = "Login"
                }
                1 -> {
                    tab.text = "Register"
                }
            }
        }.attach()
    }
}

class ScreenAdapter(fm: FragmentManager, lifecycle: Lifecycle, private val numsOfTabs: Int) :
    FragmentStateAdapter(fm, lifecycle) {

    override fun createFragment(position: Int): Fragment {
        when (position) {
            0 -> {
                //Login Fragment
                return LoginFragment()
            }
            1 -> {
                return RegisterFragment()
            }
            else -> return LoginFragment()
        }
    }

    override fun getItemCount(): Int {
        return numsOfTabs
    }


}