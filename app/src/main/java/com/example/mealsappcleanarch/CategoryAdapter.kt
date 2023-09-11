package com.example.mealsappcleanarch

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.domain.entity.Category
import com.example.mealsappcleanarch.databinding.ItemRecCategoryBinding

class CategoryAdapter : ListAdapter<Category, CategoryAdapter.CategoryViewHolder>(CategoryDiffUtil()) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        return CategoryViewHolder(
            ItemRecCategoryBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        holder.bind(getItem(position))
    }


    class CategoryViewHolder(private val binding: ItemRecCategoryBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Category) {
            Glide.with(binding.imageView).load(item.strCategoryThumb).into(binding.imageView)
            binding.categoryName.text = item.strCategory
            binding.tvCategoryDesc.text = item.strCategoryDescription
        }

    }


}

class CategoryDiffUtil : DiffUtil.ItemCallback<Category>() {
    override fun areItemsTheSame(oldItem: Category, newItem: Category): Boolean {
        return oldItem.idCategory==newItem.idCategory
    }

    override fun areContentsTheSame(oldItem: Category, newItem: Category): Boolean {
        return oldItem==newItem
    }

}