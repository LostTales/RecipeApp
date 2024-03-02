package ru.shypitsa.recipeapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ru.shypitsa.recipeapp.databinding.ItemIngredientsBinding

class IngredientsAdapter(
    private val dataSet: List<Ingredient>,
) : RecyclerView.Adapter<IngredientsAdapter.IngredientsHolder>() {
    class IngredientsHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val binding = ItemIngredientsBinding.bind(itemView)
        val tvIngredientsDescription = binding.tvIngredientsDescription
        val tvUnitOfMeasure = binding.tvUnitOfMeasure
        val tvQuantity = binding.tvQuantity
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): IngredientsHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_ingredients, parent, false)
        return IngredientsHolder(view)
    }

    override fun getItemCount(): Int {
        return dataSet.size
    }

    override fun onBindViewHolder(holder: IngredientsHolder, position: Int) {
        holder.tvIngredientsDescription.text = dataSet[position].description
        holder.tvUnitOfMeasure.text = dataSet[position].unitOfMeasure
        holder.tvQuantity.text = dataSet[position].quantity
    }

}