package ru.shypitsa.recipeapp

data class Recipe(
    val id: Int,
    val title: String,
    val ingredients: ArrayList<Ingredient>,
    val method: ArrayList<String>,
    val imageUrl: String,
)