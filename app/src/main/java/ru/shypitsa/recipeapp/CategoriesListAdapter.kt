package ru.shypitsa.recipeapp

import android.graphics.drawable.Drawable
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ru.shypitsa.recipeapp.databinding.ItemCategoryBinding

class CategoriesListAdapter(
    private val dataSet: List<Category>,
) : RecyclerView.Adapter<CategoriesListAdapter.CategoryHolder>() {

    private var itemClickListener: OnItemClickListener? = null

    interface OnItemClickListener {
        fun onItemClick(categoryId: Int)
    }

    fun setOnItemClickListener(listener: OnItemClickListener) {
        itemClickListener = listener
    }

    class CategoryHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val binding = ItemCategoryBinding.bind(itemView)
        val cvCategoryItem = binding.cvCategoryItem
        val tvCategoryName = binding.tvItemCategoryName
        val tvCategoryDescription = binding.tvItemCategoryDescription
        val ivCategoryImage = binding.ivItemCategoryImage
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_category, parent, false)
        return CategoryHolder(view)
    }

    override fun getItemCount(): Int {
        return dataSet.size
    }

    override fun onBindViewHolder(holder: CategoryHolder, position: Int) {
        holder.tvCategoryName.text = dataSet[position].title
        holder.tvCategoryDescription.text = dataSet[position].description
        holder.ivCategoryImage.contentDescription =
            "${
                holder.itemView.resources.getString(
                    R.string.start_of_text_for_card_category_content_description
                )
            } ${dataSet[position].title}"

        try {
            val inputStream = holder.itemView.context?.assets?.open(dataSet[position].imageUrl)
            val drawable = Drawable.createFromStream(inputStream, null)
            holder.ivCategoryImage.setImageDrawable(drawable)
        } catch (e: Exception) {
            Log.e("error", e.printStackTrace().toString())
        }

        holder.cvCategoryItem.setOnClickListener {
            itemClickListener?.onItemClick(dataSet[position].id)
        }
    }

}