package com.example.jokeapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Adapter
import com.example.jokeapp.databinding.ItemCategoryBinding
import com.example.jokeapp.model.CategoryJoke
import java.util.Locale.Category

class CategoryAdapter(
    private var listCategory: List<CategoryJoke>
): RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        return CategoryViewHolder(ItemCategoryBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        holder.bind(listCategory[position])
    }

    override fun getItemCount(): Int = listCategory.size

    inner class CategoryViewHolder(binding: ItemCategoryBinding): RecyclerView.ViewHolder(binding.root) {

        private val nameCategory = binding.nameCategory
        private val containerBackground = binding.containerCategory

        fun bind(categoryItem: CategoryJoke) {
            nameCategory.text = categoryItem.name
            containerBackground.setBackgroundColor(categoryItem.backgroundColor.toInt())
        }
    }
}