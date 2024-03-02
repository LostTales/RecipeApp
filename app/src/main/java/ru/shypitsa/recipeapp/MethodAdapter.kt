package ru.shypitsa.recipeapp

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ru.shypitsa.recipeapp.databinding.ItemMethodBinding

class MethodAdapter(
    private val dataSet: List<String>,
) : RecyclerView.Adapter<MethodAdapter.MethodHolder>() {
    class MethodHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val binding = ItemMethodBinding.bind(itemView)
        val tvMethod = binding.tvMethod
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MethodHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_method, parent, false)
        return MethodHolder(view)
    }

    override fun getItemCount(): Int {
        return dataSet.size
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: MethodHolder, position: Int) {
        holder.tvMethod.text = "${position + 1}. ${dataSet[position]}"
    }

}