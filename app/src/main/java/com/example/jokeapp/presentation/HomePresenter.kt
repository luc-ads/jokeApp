package com.example.jokeapp.presentation

import android.graphics.Color
import android.os.Handler
import android.os.Looper
import android.widget.Toast
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.recyclerview.widget.RecyclerView
import com.example.jokeapp.R
import com.example.jokeapp.categoryInterface.ListCategoryCallback
import com.example.jokeapp.data.CategoryRemoteDataSource
import com.example.jokeapp.model.CategoryJoke
import com.example.jokeapp.view.HomeFragment

class HomePresenter(
    private val view: HomeFragment,
    private val dataSource: CategoryRemoteDataSource
): ListCategoryCallback {

    fun findAllCategories() {
        dataSource.findAllCategories(this)
    }

    override fun onSuccess(response: List<String>) {
        val start = 40
        val end =  190
        val diff = (end - start) / response.size

        val categories = response.mapIndexed {
            index,
            s ->
            val hsv = floatArrayOf(
                start + (diff * index).toFloat(),
                100.0f,
                100.0f
            )
            CategoryJoke(s, Color.HSVToColor(hsv).toLong())
        }


        view.showCategories(categories)
        onLoading(false)
    }

    override fun onFailure(error: String) {
        view.showError(error)
        onLoading(false)
    }

    override fun onLoading(isLoading: Boolean) {
        view.onLoading(isLoading)
    }

}