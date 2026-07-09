package com.example.habittracker_semogaberuntung

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.habittracker_semogaberuntung.databinding.FragmentLoginBinding

class LoginFragment : Fragment(R.layout.fragment_login) {

    private lateinit var binding: FragmentLoginBinding
    private lateinit var viewModel: LoginViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentLoginBinding.bind(view)

        viewModel = ViewModelProvider(this)[LoginViewModel::class.java]

        // Membuat user default admin / 123 jika belum ada
        viewModel.registerDefaultUser()

        // Cek apakah sudah login sebelumnya
        viewModel.checkLogin { isLogin ->

            if (isLogin) {
                requireActivity().runOnUiThread {
                    findNavController().navigate(R.id.action_login_to_dashboard)
                }
            }

        }

        binding.btnLogin.setOnClickListener {

            val username = binding.etUsername.text.toString().trim()
            val password = binding.etPassword.text.toString().trim()

            if (username.isEmpty() || password.isEmpty()) {
                Toast.makeText(
                    requireContext(),
                    "Username dan Password harus diisi",
                    Toast.LENGTH_SHORT
                ).show()
                return@setOnClickListener
            }

            viewModel.login(username, password) { success ->

                requireActivity().runOnUiThread {

                    if (success) {

                        findNavController().navigate(R.id.action_login_to_dashboard)

                    } else {

                        Toast.makeText(
                            requireContext(),
                            "Username / Password salah",
                            Toast.LENGTH_SHORT
                        ).show()

                    }

                }

            }

        }

    }
}