package com.example.habittracker_semogaberuntung

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.habittracker_semogaberuntung.databinding.ItemHabitBinding

class HabitAdapter(
    private var habits: List<Habit>,
    private val listener: OnHabitActionListener
) : RecyclerView.Adapter<HabitAdapter.HabitViewHolder>() {

    // dipanggil dari item_habit.xml lewat listener binding
    interface OnHabitActionListener {
        fun onIncrement(habit: Habit)
        fun onDecrement(habit: Habit)
        fun onTitleClick(habit: Habit)
    }

    inner class HabitViewHolder(private val binding: ItemHabitBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(habit: Habit) {
            binding.habit = habit
            binding.listener = listener
            binding.executePendingBindings()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HabitViewHolder {
        val binding = ItemHabitBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return HabitViewHolder(binding)
    }

    override fun onBindViewHolder(holder: HabitViewHolder, position: Int) {
        holder.bind(habits[position])
    }

    override fun getItemCount() = habits.size

    fun updateList(newList: List<Habit>) {
        habits = newList
        notifyDataSetChanged()
    }
}