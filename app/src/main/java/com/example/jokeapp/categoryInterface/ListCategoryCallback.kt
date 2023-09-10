package com.example.jokeapp.categoryInterface

interface ListCategoryCallback {

    fun onSuccess(response: List<String>)
    fun onFailure(error: String)
    fun onLoading(isLoading: Boolean)
}