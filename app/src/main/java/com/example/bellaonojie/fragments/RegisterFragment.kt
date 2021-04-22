package com.example.bellaonojie.fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.example.bellaonojie.BellaApplication
import com.example.bellaonojie.MainActivity
import com.example.bellaonojie.R
import com.example.bellaonojie.databinding.FragmentRegsiterBinding
import com.example.bellaonojie.models.User
import com.example.bellaonojie.viewmodel.UserViewModel
import com.example.bellaonojie.viewmodel.UserViewModelFactory
import kotlinx.coroutines.launch

class RegisterFragment : Fragment() {

    private lateinit var binding: FragmentRegsiterBinding
    private lateinit var mUserViewModel: UserViewModel

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_regsiter, container, false)

        val application = requireNotNull(this.activity).application as BellaApplication

        val userViewModelFactory = UserViewModelFactory(application)

        mUserViewModel = ViewModelProvider(this, userViewModelFactory).get(UserViewModel::class.java)

        binding.registerButton.setOnClickListener {
            val username = binding.registerUsername.editText?.text.toString()
            val email = binding.registerEmailAddress.editText?.text.toString()
            val password = binding.registerPassword.editText?.text.toString()

            val timeId = System.currentTimeMillis()

            val user = User(
                    timeId,
                    username,
                    email,
                    password,
                    "https://support.hubstaff.com/wp-content/uploads/2019/08/good-pic.png",
                    "00000000",
                    "None",
                    timeId,
                    timeId,
                    0
            )

            lifecycleScope.launch {
                mUserViewModel.insertUser(user)
                Toast.makeText(context, "User Added Successfully", Toast.LENGTH_SHORT).show()

                val intent = Intent(requireContext(), MainActivity::class.java)
                intent.putExtra("userData", user)
                startActivity(intent)
            }

        }

        return binding.root
    }
}