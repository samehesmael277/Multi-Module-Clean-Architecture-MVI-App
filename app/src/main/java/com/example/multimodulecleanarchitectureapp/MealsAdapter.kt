package com.example.multimodulecleanarchitectureapp

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.domain.model.Category
import com.example.multimodulecleanarchitectureapp.databinding.ItemCategoreBinding
import javax.inject.Inject

class MealsAdapter @Inject constructor() :
    ListAdapter<Category, MealsAdapter.ViewHolder>(CategoryDiffCallback()) {

    inner class ViewHolder(private val itemBinding: ItemCategoreBinding) :
        RecyclerView.ViewHolder(itemBinding.root) {

        fun bind(category: Category) {
            itemBinding.categoryNameTv.text = category.strCategory
            itemBinding.categoryDesTv.text = category.strCategoryDescription
            Glide.with(itemBinding.root.context)
                .load(category.strCategoryThumb)
                .into(itemBinding.categoryIv)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemBinding =
            ItemCategoreBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentItem = getItem(position)
        holder.bind(currentItem)
    }

}

class CategoryDiffCallback : DiffUtil.ItemCallback<Category>() {
    override fun areItemsTheSame(
        oldItem: Category,
        newItem: Category
    ): Boolean {
        return oldItem.idCategory == newItem.idCategory
    }

    override fun areContentsTheSame(
        oldItem: Category,
        newItem: Category
    ): Boolean {
        return oldItem == newItem
    }
}
