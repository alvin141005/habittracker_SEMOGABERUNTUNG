package com.example.habittracker_semogaberuntung

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController

class AddHabitFragment : Fragment() {
    private lateinit var viewModel: HabitViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(requireActivity())[HabitViewModel::class.java]

        viewModel.habitList.observe(viewLifecycleOwner) { list ->
            println("Jumlah habit: ${list.size}")
        }

        val etName = view.findViewById<EditText>(R.id.etName)
        val etDesc = view.findViewById<EditText>(R.id.etDescription)
        val etGoal = view.findViewById<EditText>(R.id.etGoal)

        val btnSave = view.findViewById<Button>(R.id.btnSave)

        btnSave.setOnClickListener {

            val name = etName.text.toString()
            val desc = etDesc.text.toString()
            val goal = etGoal.text.toString().toIntOrNull() ?: 0

            val habit = Habit(
                name = name,
                description = desc,
                goal = goal,
                icon = R.drawable.ic_launcher_foreground
            )

            viewModel.addHabit(habit)

            findNavController().popBackStack()
        }
    }
}