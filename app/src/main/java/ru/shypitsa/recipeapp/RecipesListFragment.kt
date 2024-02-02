package ru.shypitsa.recipeapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import ru.shypitsa.recipeapp.databinding.FragmentRecipesListBinding

class RecipesListFragment : Fragment() {

    private var categoryId: Int? = null
    private var categoryName: String? = null
    private var categoryImageUrl: String? = null

    private var _binding: FragmentRecipesListBinding? = null
    private val binding
        get() = _binding
            ?: throw IllegalStateException("Binding for FragmentRecipesListBinding must not be null")

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentRecipesListBinding.inflate(layoutInflater, container, false)
        return binding.root

        val tv: TextView = binding.tvTitleRecipes
        val iv: ImageView = binding.ivTitleRecipes

        arguments?.let {
            categoryId = it.getInt("ARG_CATEGORY_ID")
            categoryName = it.getString("ARG_CATEGORY_NAME")
            categoryImageUrl = it.getString("ARG_CATEGORY_IMAGE_URL")
        }

        tv.text = categoryName

    }

}