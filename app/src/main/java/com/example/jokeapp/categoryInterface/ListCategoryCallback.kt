package com.example.jokeapp.categoryInterface

import com.example.jokeapp.model.CategoryJoke

interface ListCategoryCallback {

    fun onSuccess(response: List<String>)
    fun onFailure(error: String)
    fun onLoading(isLoading: Boolean)
}