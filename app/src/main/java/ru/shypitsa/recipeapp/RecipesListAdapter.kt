package ru.shypitsa.recipeapp

import android.graphics.drawable.Drawable
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ru.shypitsa.recipeapp.databinding.ItemRecipeBinding

class RecipesListAdapter(
    private val dataSet: List<Recipe>,
) : RecyclerView.Adapter<RecipesListAdapter.RecipeHolder>() {

    private var itemClickListener: OnItemClickListener? = null

    interface OnItemClickListener {
        fun onItemClick(recipeId: Int)
    }

    fun setOnItemClickListener(listener: OnItemClickListener) {
        itemClickListener = listener
    }

    class RecipeHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val binding = ItemRecipeBinding.bind(itemView)
        val cvRecipeItem = binding.cvRecipeItem
        val tvRecipeName = binding.tvItemRecipeName
        val ivRecipeImage = binding.ivItemRecipeImage
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecipeHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_recipe, parent, false)
        return RecipeHolder(view)
    }

    override fun getItemCount(): Int {
        return dataSet.size
    }

    override fun onBindViewHolder(holder: RecipeHolder, position: Int) {
        holder.tvRecipeName.text = dataSet[position].title
        holder.ivRecipeImage.contentDescription =
            "${
                holder.itemView.resources.getString(
                    R.string.start_of_text_for_card_recipe_content_description
                )
            } ${dataSet[position].title}"

        try {
            val inputStream = holder.itemView.context?.assets?.open(dataSet[position].imageUrl)
            val drawable = Drawable.createFromStream(inputStream, null)
            holder.ivRecipeImage.setImageDrawable(drawable)
        } catch (e: Exception) {
            Log.e("error", e.printStackTrace().toString())
        }

        holder.cvRecipeItem.setOnClickListener {
            itemClickListener?.onItemClick(dataSet[position].id)
        }

    }

}