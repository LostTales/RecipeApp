package ru.shypitsa.recipeapp

import android.graphics.drawable.Drawable
import android.os.Build.VERSION.SDK_INT
import android.os.Bundle
import android.os.Parcelable
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.core.content.res.ResourcesCompat
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView
import ru.shypitsa.recipeapp.databinding.FragmentRecipeBinding

class RecipeFragment : Fragment() {

    private var recipeId: Int? = null
    private var recipeName: String? = null
    private var recipeImageUrl: String? = null

    private var _binding: FragmentRecipeBinding? = null
    private val binding
        get() = _binding
            ?: throw IllegalStateException("Binding for FragmentRecipeBinding must not be null")

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentRecipeBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initUI()
        initRecycler()
    }

    private fun initRecycler() {
        arguments?.let { recipeId = it.parcelable<Recipe>(KEY_IN_BUNDLE_FOR_ARG_RECIPE)?.id }

        val dividerItemDecoration = DividerItemDecoration(context, RecyclerView.VERTICAL)
        ResourcesCompat.getDrawable(resources, R.drawable.divider, null)?.let {
            dividerItemDecoration.setDrawable(it)
        }

        val ingredientsAdapter =
            recipeId?.let { STUB.getRecipeById(it)?.ingredients }?.let { IngredientsAdapter(it) }
        val recyclerViewIngredients: RecyclerView = binding.rvIngredients
        recyclerViewIngredients.adapter = ingredientsAdapter
        recyclerViewIngredients.addItemDecoration(dividerItemDecoration)


        val methodAdapter =
            recipeId?.let { STUB.getRecipeById(it)?.method }?.let { MethodAdapter(it) }
        val recyclerViewMethod: RecyclerView = binding.rvMethod
        recyclerViewMethod.adapter = methodAdapter
        recyclerViewMethod.addItemDecoration(dividerItemDecoration)
    }

    private fun initUI() {
        val iv: ImageView = binding.ivTitleRecipe
        arguments?.let {
            recipeName = it.parcelable<Recipe>(KEY_IN_BUNDLE_FOR_ARG_RECIPE)?.title
            recipeImageUrl = it.parcelable<Recipe>(KEY_IN_BUNDLE_FOR_ARG_RECIPE)?.imageUrl
        }
        binding.tvTitleRecipe.text = recipeName
        iv.contentDescription =
            "${
                resources.getString(R.string.start_of_text_for_card_recipe_content_description)
            } $recipeName"

        try {
            val inputStream = context?.assets?.open(recipeImageUrl.toString())
            val drawable = Drawable.createFromStream(inputStream, null)
            iv.setImageDrawable(drawable)
        } catch (e: Exception) {
            Log.e("error", e.printStackTrace().toString())
        }
    }

    private inline fun <reified T : Parcelable> Bundle.parcelable(key: String): T? = when {
        SDK_INT >= 33 -> getParcelable(key, T::class.java)
        else -> @Suppress("DEPRECATION") getParcelable(key) as? T
    }

}