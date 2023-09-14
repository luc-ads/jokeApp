package com.example.jokeapp.categoryInterface

import com.example.jokeapp.model.Joke

interface JokeCallback {

    fun onSuccess(response: Joke)
    fun onFailure(error: String)
    fun onLoading(isLoading: Boolean) }