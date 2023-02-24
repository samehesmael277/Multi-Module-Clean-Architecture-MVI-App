package com.example.data.mapper

import com.example.data.model.CategoryDto
import com.example.data.model.CategoryResponseDto
import com.example.domain.model.Category
import com.example.domain.model.CategoryResponse

object MealsMapper {

    private fun CategoryDto.toCategory() = Category(idCategory, strCategory, strCategoryDescription, strCategoryThumb)

    fun CategoryResponseDto.toCategoryResponse() = CategoryResponse(categories.map { it.toCategory() })
}