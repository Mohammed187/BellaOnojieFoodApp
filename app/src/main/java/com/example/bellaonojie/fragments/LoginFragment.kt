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
import com.example.bellaonojie.BellaApplication
import com.example.bellaonojie.MainActivity
import com.example.bellaonojie.R
import com.example.bellaonojie.databinding.FragmentLoginBinding
import com.example.bellaonojie.viewmodel.UserViewModel
import com.example.bellaonojie.viewmodel.UserViewModelFactory

class LoginFragment : Fragment() {

    private lateinit var binding: FragmentLoginBinding
    private lateinit var mUserViewModel: UserViewModel

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_login, container, false)

        val application = requireNotNull(this.activity).application as BellaApplication

        val userViewModelFactory = UserViewModelFactory(application)

        mUserViewModel = ViewModelProvider(this, userViewModelFactory).get(UserViewModel::class.java)

        binding.loginButton.setOnClickListener {
            val email = binding.loginEmailAddress.editText?.text.toString()
            val password = binding.loginPassword.editText?.text.toString()

            context?.let {
                mUserViewModel.checkUserData(email, password)?.observe(viewLifecycleOwner, { user ->
                    if (user == null) {
                        Toast.makeText(context, "Not Found", Toast.LENGTH_SHORT).show()
                    } else {
                        Toast.makeText(context, "Found", Toast.LENGTH_SHORT).show()
                        val intent = Intent(requireContext(), MainActivity::class.java)
                        intent.putExtra("userData", user)
                        it.startActivity(intent)
                    }
                })
            }
        }

        return binding.root
    }

}