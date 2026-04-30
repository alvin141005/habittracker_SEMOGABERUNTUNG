package com.example.habittracker_semogaberuntung

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.habittracker_semogaberuntung.databinding.ItemHabitBinding

class HabitAdapter(
    private var habits: List<Habit>,
    private val onIncrement: (Int) -> Unit,
    private val onDecrement: (Int) -> Unit
) : RecyclerView.Adapter<HabitAdapter.HabitViewHolder>() {

    inner class HabitViewHolder(private val binding: ItemHabitBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(habit: Habit, position: Int) {
            binding.apply {
                ivIcon.setImageResource(habit.icon)
                tvHabitName.text = habit.name
                tvDescription.text = habit.description

                val progressText = "${habit.progress} / ${habit.goal}"
                tvProgress.text = progressText

                progressBar.max = habit.goal
                progressBar.progress = habit.progress

                if (habit.progress >= habit.goal) {
                    tvStatus.text = "Completed"
                    tvStatus.setTextColor(ContextCompat.getColor(root.context, android.R.color.holo_green_dark))
                    cardHabit.strokeColor = ContextCompat.getColor(root.context, android.R.color.holo_green_dark)
                } else {
                    tvStatus.text = "In Progress"
                    tvStatus.setTextColor(ContextCompat.getColor(root.context, android.R.color.darker_gray))
                    cardHabit.strokeColor = ContextCompat.getColor(root.context, android.R.color.darker_gray)
                }

                btnPlus.setOnClickListener { onIncrement(position) }
                btnMinus.setOnClickListener { onDecrement(position) }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HabitViewHolder {
        val binding = ItemHabitBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return HabitViewHolder(binding)
    }

    override fun onBindViewHolder(holder: HabitViewHolder, position: Int) {
        holder.bind(habits[position], position)
    }

    override fun getItemCount() = habits.size

    fun updateList(newList: List<Habit>) {
        habits = newList
        notifyDataSetChanged()
    }
}