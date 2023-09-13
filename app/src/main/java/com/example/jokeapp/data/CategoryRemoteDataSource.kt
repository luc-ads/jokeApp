package com.example.jokeapp.data

import android.os.Handler
import android.os.Looper
import android.util.Log
import com.example.jokeapp.categoryInterface.ListCategoryCallback
import com.example.jokeapp.model.CategoryJoke
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CategoryRemoteDataSource {

    fun findAllCategories(callback: ListCategoryCallback) {
        HTTPClient.retrofit()
            .create(ChuckNorrisApi::class.java)
            .findAllCategories()
            .enqueue(object : Callback<List<String>> {
                override fun onResponse(
                    call: Call<List<String>>,
                    response: Response<List<String>>
                ) {
                    if (response.isSuccessful) {
                        val obj = response.body()
                        callback.onSuccess(obj ?: emptyList())
                    } else {
                        //Quando o servidor devevolve status code menor que 500
                        val error = response.errorBody()?.toString()
                        callback.onFailure(error ?: "Erro desconhecido")
                    }
                }

                override fun onFailure(call: Call<List<String>>, t: Throwable) {
                    callback.onFailure(t.message ?: "Erro interno")
                }
            })
    }
}