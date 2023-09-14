package com.example.jokeapp.data

import com.example.jokeapp.categoryInterface.JokeCallback
import com.example.jokeapp.categoryInterface.ListCategoryCallback
import com.example.jokeapp.model.Joke
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class JokeRemoteDataSource {

    fun findBy(categoryName: String, callback: JokeCallback) {
        HTTPClient.retrofit()
            .create(ChuckNorrisApi::class.java)
            .findBy(categoryName)
            .enqueue(object: Callback<Joke> {
                override fun onResponse(call: Call<Joke>, response: Response<Joke>) {
                    if (response.isSuccessful) {
                        callback.onSuccess(response.body() ?: throw RuntimeException("Joke not found"))
                    } else {
                        //Quando o servidor devevolve status code menor que 500
                        val error = response.errorBody()?.toString()
                        callback.onFailure(error ?: "Erro desconhecido")
                    }

                }

                override fun onFailure(call: Call<Joke>, t: Throwable) {
                    callback.onFailure(t.message ?: "Erro interno")
                }
            })

    }

}