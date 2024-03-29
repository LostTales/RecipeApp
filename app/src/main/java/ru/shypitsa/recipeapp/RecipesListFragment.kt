package ru.shypitsa.recipeapp

import android.graphics.drawable.Drawable
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import androidx.fragment.app.replace
import androidx.recyclerview.widget.RecyclerView
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
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecycler()
        getHeaderOfRecipesList()
    }

    private fun getHeaderOfRecipesList() {
        val tv: TextView = binding.tvTitleRecipes
        val iv: ImageView = binding.ivTitleRecipes

        arguments?.let {
            categoryId = it.getInt(KEY_IN_BUNDLE_FOR_ARG_CATEGORY_ID)
            categoryName = it.getString(KEY_IN_BUNDLE_FOR_ARG_CATEGORY_NAME)
            categoryImageUrl = it.getString(KEY_IN_BUNDLE_FOR_ARG_CATEGORY_IMAGE_URL)
        }

        tv.text = categoryName

        try {
            val inputStream = context?.assets?.open(categoryImageUrl.toString())
            val drawable = Drawable.createFromStream(inputStream, null)
            iv.setImageDrawable(drawable)
        } catch (e: Exception) {
            Log.e("error", e.printStackTrace().toString())
        }
    }

    private fun initRecycler() {
        arguments?.let { categoryId = it.getInt(KEY_IN_BUNDLE_FOR_ARG_CATEGORY_ID) }
        val recipesListAdapter =
            RecipesListAdapter(STUB.getRecipesByCategoryId(categoryId ?: 0))
        val recyclerView: RecyclerView = binding.rvRecipes
        recyclerView.adapter = recipesListAdapter
        recipesListAdapter.setOnItemClickListener(object :
            RecipesListAdapter.OnItemClickListener {
            override fun onItemClick(recipeId: Int) {
                openRecipeByRecipeId(recipeId)
            }
        })
    }

    private fun openRecipeByRecipeId(recipeId: Int) {
        val recipe = STUB.getRecipeById(recipeId)
        val bundle = Bundle().apply { putParcelable(KEY_IN_BUNDLE_FOR_ARG_RECIPE, recipe) }
        parentFragmentManager.commit {
            replace<RecipeFragment>(R.id.mainContainer, args = bundle)
            setReorderingAllowed(true)
            addToBackStack(null)
        }
    }

}