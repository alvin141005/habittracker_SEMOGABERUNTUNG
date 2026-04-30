package com.example.habittracker_semogaberuntung

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.habittracker_semogaberuntung.databinding.FragmentAddHabitBinding

class AddHabitFragment : Fragment() {

    private var _binding: FragmentAddHabitBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: HabitViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAddHabitBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(requireActivity())[HabitViewModel::class.java]

        // Observer (opsional, cuma buat debug)
        viewModel.habitList.observe(viewLifecycleOwner) { list ->
            println("Jumlah habit setelah tambah: ${list.size}")
        }

        // Setup tombol save
        binding.btnSave.setOnClickListener {
            val name = binding.etName.text.toString().trim()
            val desc = binding.etDescription.text.toString().trim()
            val goal = binding.etGoal.text.toString().toIntOrNull() ?: 0

            if (name.isEmpty() || goal <= 0) {
                // Bisa tambah Toast error nanti
                return@setOnClickListener
            }

            val habit = Habit(
                name = name,
                description = desc,
                goal = goal,
                icon = R.drawable.ic_launcher_foreground  // nanti ganti dengan spinner
            )

            viewModel.addHabit(habit)
            findNavController().popBackStack()  // balik ke Dashboard
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}