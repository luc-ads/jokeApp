package com.example.jokeapp.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.fragment.app.findFragment
import com.example.jokeapp.R
import com.example.jokeapp.model.CategoryJoke
import com.example.jokeapp.model.Joke
import com.example.jokeapp.presentation.JokePresenter
import com.google.android.material.floatingactionbutton.FloatingActionButton

class JokeFragment: Fragment() {

    private lateinit var presenter: JokePresenter
    private lateinit var progressBar: ProgressBar
    private lateinit var textView: TextView
    private lateinit var imageView: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        presenter = JokePresenter(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_joke, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val categoryName = arguments?.getString("category")!!

        activity?.findViewById<Toolbar>(R.id.toolbar)!!.title = categoryName
        progressBar = view.findViewById(R.id.progressBar)
        textView = view.findViewById(R.id.txt_joke)
        imageView = view.findViewById(R.id.img_joke)

        presenter.findBy(categoryName)

        view.findViewById<FloatingActionButton>(R.id.floatingActionButton).setOnClickListener {
            presenter.findBy(categoryName)
        }
    }

    fun showJoke(joke: Joke) {
        textView.text = joke.text
        // TODO: inserção de imagem

    }

    fun showProgress(show: Boolean) {
        if (show)  {
            progressBar.visibility = View.VISIBLE
        } else {
            progressBar.visibility = View.GONE
        }
    }

    fun showError(error: String) {
        Toast.makeText(requireContext(), error, Toast.LENGTH_SHORT).show()
    }
}