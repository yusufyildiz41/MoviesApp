package com.yusufyildiz.moviesapp.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.findFragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.yusufyildiz.moviesapp.R
import com.yusufyildiz.moviesapp.common.downloadFromURL
import com.yusufyildiz.moviesapp.common.placeHolderProgressBar
import com.yusufyildiz.moviesapp.databinding.FragmentMovieDetailBinding
import com.yusufyildiz.moviesapp.viewmodel.MovieDetailViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MovieDetailFragment : Fragment() {

    private lateinit var binding: FragmentMovieDetailBinding
    private val movieDetailViewModel: MovieDetailViewModel by viewModels()
    private var movieImdbId = ""
    private var movieTitle = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_movie_detail, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        arguments?.let {
            movieImdbId = MovieDetailFragmentArgs.fromBundle(it).movieId
            movieTitle = MovieDetailFragmentArgs.fromBundle(it).movieTitle
        }
        if (movieTitle.isNotEmpty()) {
            (activity as AppCompatActivity).supportActionBar?.title = movieTitle
        } else {
            (activity as AppCompatActivity).supportActionBar?.title = "Movie Name Not Found"
        }
        movieDetailViewModel.getMovieDetails(movieImdbId)
        initObservers()

    }

    private fun initObservers() {
        movieDetailViewModel.movieDetailsLiveData.observe(viewLifecycleOwner) { movie ->

            binding.movieDetailImageView.visibility = View.VISIBLE
            binding.imageView.visibility = View.VISIBLE
            binding.movieCategoryDetailText.visibility = View.VISIBLE
            binding.movieDescriptionDetailText.visibility = View.VISIBLE
            binding.movieHoursDetailText.visibility = View.VISIBLE
            binding.movieRateDetailText.visibility = View.VISIBLE
            binding.movieNameDetailText.visibility = View.VISIBLE
            binding.textView2.visibility = View.VISIBLE

            context?.let {
                binding.movieDetailImageView.downloadFromURL(
                    movie.moviePoster.toString(),
                    placeHolderProgressBar(it)
                )
            }
            binding.movieNameDetailText.text = movie.movieTitle.toString()
            binding.movieCategoryDetailText.text = movie.movieGenre.toString()
            binding.movieDescriptionDetailText.text = movie.moviePlot.toString()
            binding.movieRateDetailText.text = movie.movieImdbRating.toString()
            binding.movieHoursDetailText.text = movie.movieRuntime.toString()

        }
    }
}