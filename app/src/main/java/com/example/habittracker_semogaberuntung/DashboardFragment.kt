package com.example.habittracker_semogaberuntung

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.habittracker_semogaberuntung.databinding.FragmentDashboardBinding

class DashboardFragment : Fragment() {

    private var _binding: FragmentDashboardBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: HabitViewModel
    private lateinit var adapter: HabitAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDashboardBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(requireActivity())[HabitViewModel::class.java]

        setupRecyclerView()
        setupObservers()
        setupFab()
    }

    private fun setupRecyclerView() {
        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
        adapter = HabitAdapter(
            habits = emptyList(),
            onIncrement = { pos -> viewModel.incrementProgress(pos) },
            onDecrement = { pos -> viewModel.decrementProgress(pos) }
        )
        binding.recyclerView.adapter = adapter
    }

    private fun setupObservers() {
        viewModel.habitList.observe(viewLifecycleOwner) { habits ->
            if (habits.isEmpty()) {
                binding.tvEmpty.visibility = View.VISIBLE
                binding.recyclerView.visibility = View.GONE
            } else {
                binding.tvEmpty.visibility = View.GONE
                binding.recyclerView.visibility = View.VISIBLE
                adapter.updateList(habits)
            }
        }
    }

    private fun setupFab() {
        binding.fabAdd.setOnClickListener {
            findNavController().navigate(R.id.action_dashboard_to_addHabit)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}