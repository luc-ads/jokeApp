package com.example.jokeapp.presentation

import com.example.jokeapp.categoryInterface.JokeCallback
import com.example.jokeapp.data.JokeRemoteDataSource
import com.example.jokeapp.model.Joke
import com.example.jokeapp.view.JokeFragment

class JokePresenter(
    private val view: JokeFragment,
    private val dataSource: JokeRemoteDataSource = JokeRemoteDataSource()
): JokeCallback {

    fun findBy(categoryName: String) {
        view.showProgress(true)
        dataSource.findBy(categoryName, this)

    }

    override fun onSuccess(response: Joke) {
        view.showJoke(response)
        onLoading(false)
    }

    override fun onFailure(error: String) {
        view.showError(error)
        onLoading(false)
    }

    override fun onLoading(isLoading: Boolean) {
        view.showProgress(isLoading)
    }
}