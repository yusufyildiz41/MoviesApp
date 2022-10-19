package com.yusufyildiz.moviesapp.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.yusufyildiz.moviesapp.R
import com.yusufyildiz.moviesapp.adapter.MoviesAdapter
import com.yusufyildiz.moviesapp.common.showToast
import com.yusufyildiz.moviesapp.databinding.FragmentMovieListBinding
import com.yusufyildiz.moviesapp.viewmodel.MovieListViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MovieListFragment : Fragment() {

    private lateinit var binding: FragmentMovieListBinding
    private val movieListViewModel: MovieListViewModel by viewModels()
    private val movieListAdapter = MoviesAdapter(arrayListOf())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_movie_list, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.moviesLoading.visibility = View.GONE
        binding.movieError.visibility = View.GONE


        binding.searchButton.setOnClickListener {

            val movieText = binding.movieNameEditText.text.toString()

            if (movieText.trim().isEmpty())
            {
                context showToast "Please enter a movie name !!!"
                binding.movieError.visibility = View.VISIBLE
                binding.moviesLoading.visibility = View.GONE
                binding.movieList.visibility = View.GONE
            } else {
                movieListViewModel.getMovies(movieText)
                binding.moviesLoading.visibility = View.GONE
                binding.movieList.adapter = movieListAdapter
                initObservers()
            }

        }

        binding.swipeRefreshLayout.setOnRefreshListener {

            if (binding.movieNameEditText.text.toString().trim().isEmpty())
            {
                binding.moviesLoading.visibility = View.GONE
                binding.movieError.visibility = View.VISIBLE
                binding.movieList.visibility = View.GONE
            }
            else
            {
                binding.movieList.visibility = View.GONE
                binding.movieError.visibility = View.GONE
                binding.moviesLoading.visibility = View.VISIBLE
                movieListViewModel.getMovies(binding.movieNameEditText.text.toString())

            }
            binding.swipeRefreshLayout.isRefreshing = false
        }

    }

    private fun initObservers() {
        movieListViewModel.movieListLiveData.observe(viewLifecycleOwner) { data ->
            data.let { movieList ->
                binding.movieList.visibility = View.VISIBLE
                movieListAdapter.updateMovieList(movieList)
            }
        }

        movieListViewModel.movieError.observe(viewLifecycleOwner) { error ->
            error?.let {
                if (it)
                {
                    binding.movieError.visibility = View.VISIBLE
                    binding.moviesLoading.visibility = View.GONE
                }
                else
                {
                    binding.movieError.visibility = View.GONE
                }
            }
        }

        movieListViewModel.movieLoading.observe(viewLifecycleOwner) { loading ->
            loading?.let {
                if (it)
                {
                    binding.moviesLoading.visibility = View.GONE
                    binding.movieList.visibility = View.VISIBLE
                }
                else
                {
                    binding.moviesLoading.visibility = View.VISIBLE
                    binding.movieError.visibility = View.GONE
                    binding.movieList.visibility = View.GONE

                }
            }
        }

    }

}