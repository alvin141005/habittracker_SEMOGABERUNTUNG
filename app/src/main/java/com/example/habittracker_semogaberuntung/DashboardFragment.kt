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

class DashboardFragment : Fragment(), HabitAdapter.OnHabitActionListener {

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
        // fetch dari tabel habit (room)
        viewModel.loadHabits()

        setupRecyclerView()
        setupObservers()
        setupFab()
    }

    private fun setupRecyclerView() {
        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
        // FAB gak perlu data binding, jadi listener biasa aja disini sudah cukup.
        // Untuk tiap kartu (item_habit.xml), yang dipakai adalah listener binding lewat "listener" ini
        adapter = HabitAdapter(habits = emptyList(), listener = this)
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

    // dipanggil lewat listener binding dari tombol + di item_habit.xml
    override fun onIncrement(habit: Habit) {
        viewModel.incrementProgress(habit)
    }

    // dipanggil lewat listener binding dari tombol - di item_habit.xml
    override fun onDecrement(habit: Habit) {
        viewModel.decrementProgress(habit)
    }

    // (BARU) judul habit ditekan -> pindah ke Edit Habit, kirim id habit-nya
    override fun onTitleClick(habit: Habit) {
        val bundle = Bundle().apply {
            putInt("habitId", habit.id)
        }
        findNavController().navigate(R.id.action_dashboard_to_editHabit, bundle)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}