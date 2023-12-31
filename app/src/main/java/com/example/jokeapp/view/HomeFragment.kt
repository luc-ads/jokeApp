package com.example.jokeapp.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.jokeapp.R
import com.example.jokeapp.adapter.CategoryAdapter
import com.example.jokeapp.data.CategoryRemoteDataSource
import com.example.jokeapp.model.CategoryJoke
import com.example.jokeapp.presentation.HomePresenter

class HomeFragment: Fragment() {

    private lateinit var presenter: HomePresenter
    private var listCategories = mutableListOf<CategoryJoke>()
    private lateinit var progressBar: ProgressBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val dataSource = CategoryRemoteDataSource()
        presenter = HomePresenter(this, dataSource)

    }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        progressBar = view.findViewById(R.id.progressBar)

        if (listCategories.isEmpty()) {
            presenter.findAllCategories()
            onLoading(true)
        }
        val adapter =
            CategoryAdapter(listCategories) { name ->
                val bundle = Bundle()
                bundle.putString("category", name)
                findNavController().navigate(R.id.action_nav_home_to_nav_joke, bundle)
            }

        val recyclerView = view.findViewById<RecyclerView>(R.id.rv_main)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.adapter = adapter


    }

    fun showCategories(response: List<CategoryJoke>) {
        val recyclerView = view?.findViewById<RecyclerView>(R.id.rv_main)

        if (response.isNotEmpty()) {
            listCategories.addAll(response)
            recyclerView?.adapter?.notifyDataSetChanged()
        }
    }

    fun showError(error: String) {
        Toast.makeText(requireContext(), error, Toast.LENGTH_SHORT).show()
    }

    fun onLoading(isLoading: Boolean) {
        if (isLoading) {
            progressBar.visibility = View.VISIBLE
        } else {
            progressBar.visibility = View.GONE
        }
    }
}