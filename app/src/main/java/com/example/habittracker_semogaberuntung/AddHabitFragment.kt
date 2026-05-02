package com.example.habittracker_semogaberuntung

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.habittracker_semogaberuntung.databinding.FragmentAddHabitBinding
import android.widget.ArrayAdapter

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
        // spinner buat item
        val items = listOf("Fitness", "Minum", "Belajar")

        val icons = listOf(
            R.drawable.fitness,
            R.drawable.drink,
            R.drawable.study
        )

        binding.spinnerIcon.adapter = ArrayAdapter(
            requireContext(),
            android.R.layout.simple_spinner_dropdown_item,
            items
        )

        // spinner buat unit (gatau boleh gak)
        //val unitItems = listOf("menit", "gelas", "halaman")

        //binding.spinnerUnit.adapter = ArrayAdapter(
        //    requireContext(),
        //    android.R.layout.simple_spinner_dropdown_item,
        //    unitItems
        //)

        // Setup tombol save
        binding.btnSave.setOnClickListener {
            val name = binding.etName.text.toString().trim()
            val desc = binding.etDescription.text.toString().trim()
            val goal = binding.etGoal.text.toString().toIntOrNull() ?: 0
            val unit = binding.etUnit.text.toString().trim()

            if (name.isEmpty() || goal <= 0) {
                // Bisa tambah Toast error nanti
                return@setOnClickListener
            }

            val selectedIcon = icons[binding.spinnerIcon.selectedItemPosition]

            val habit = Habit(
                name = name,
                description = desc,
                goal = goal,
                icon = selectedIcon,
                unit = unit
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