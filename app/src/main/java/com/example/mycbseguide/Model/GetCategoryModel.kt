package com.example.mycbseguide.Model

import com.google.gson.annotations.SerializedName

class GetCategoryModel(
    @SerializedName("categories") val categories: List<Category>,
    @SerializedName("status")  val status: String
)
