package com.example.habittracker_semogaberuntung

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider

class DashboardFragment : Fragment() {
    private lateinit var viewModel: HabitViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(requireActivity())[HabitViewModel::class.java]

        viewModel.habitList.observe(viewLifecycleOwner) { list ->
            println("Jumlah habit: ${list.size}")
        }
    }
}