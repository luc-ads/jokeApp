package com.example.jokeapp.data

import android.os.Handler
import android.os.Looper
import android.util.Log
import com.example.jokeapp.categoryInterface.ListCategoryCallback
import com.example.jokeapp.model.CategoryJoke

class CategoryRemoteDataSource {

    fun findAllCategories(callback: ListCategoryCallback) {
        Handler(Looper.getMainLooper()).postDelayed({
//            val response = arrayListOf(
//                CategoryJoke("Teste 1", 0xFFFace6e),
//                CategoryJoke("Teste 2", 0xFFecd16d),
//                CategoryJoke("Teste 3", 0xFFded36e),
//                CategoryJoke("Teste 4", 0xFFcfd571)
//            )
//
            val response = arrayListOf(
                "Teste 1",
                "Teste 2",
                "Teste 3",
                "Teste 4",
            )

            //Aqui a lista já está pronta
//            inter.onSuccess(response)
//            inter.onFailure()
            callback.onSuccess(response)
            Log.i("dataSource", response.toString())
        }, 2000)
    }
}