package com.example.habittracker_semogaberuntung

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.habittracker_semogaberuntung.databinding.FragmentEditHabitBinding

class EditHabitFragment : Fragment() {
    private var habitId = -1
    private var _binding: FragmentEditHabitBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: HabitViewModel
    private var currentHabit: Habit? = null


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentEditHabitBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(requireActivity())[HabitViewModel::class.java]
        habitId = requireArguments().getInt("habitId")

        viewModel.getHabitById(habitId) { habit ->

            activity?.runOnUiThread {

                if (habit != null) {
                    currentHabit = habit
                    binding.etName.setText(habit.name)
                    binding.etDescription.setText(habit.description)
                    binding.etGoal.setText(habit.goal.toString())
                    binding.etUnit.setText(habit.unit)
                }

            }

        }
        binding.btnSave.setOnClickListener {
            currentHabit?.let { habit ->
                habit.name = binding.etName.text.toString()
                habit.description = binding.etDescription.text.toString()
                habit.goal = binding.etGoal.text.toString().toInt()
                habit.unit = binding.etUnit.text.toString()
                viewModel.updateHabit(habit)
                findNavController().popBackStack()
            }
        }

        println("Habit ID = $habitId")
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}