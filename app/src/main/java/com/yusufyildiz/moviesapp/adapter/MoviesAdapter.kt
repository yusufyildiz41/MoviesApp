package com.yusufyildiz.moviesapp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.yusufyildiz.moviesapp.common.downloadFromURL
import com.yusufyildiz.moviesapp.common.placeHolderProgressBar
import com.yusufyildiz.moviesapp.databinding.MovieRecyclerRowBinding
import com.yusufyildiz.moviesapp.model.search.Movies
import com.yusufyildiz.moviesapp.ui.MovieListFragmentDirections

class MoviesAdapter(val movieList: ArrayList<Movies>) :
    RecyclerView.Adapter<MoviesAdapter.MoviesViewHolder>() {

    class MoviesViewHolder(val binding: MovieRecyclerRowBinding) : ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MoviesViewHolder {
        val binding =
            MovieRecyclerRowBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MoviesViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MoviesViewHolder, position: Int) {
        val item = movieList[position]
        holder.binding.movieNameText.text = item.moviesTitle.toString()
        holder.binding.movieCategoryText.text = item.moviesType.toString()
        holder.binding.movieYearText.text = item.moviesYear.toString()
        holder.binding.movieImageView.downloadFromURL(
            item.moviesPoster,
            placeHolderProgressBar(holder.itemView.context)
        )
        holder.itemView.setOnClickListener {
            val action = MovieListFragmentDirections.actionMovieListFragmentToMovieDetailFragment(
                item.moviesImdbId.toString(),
                item.moviesTitle.toString()
            )
            Navigation.findNavController(it).navigate(action)
        }
    }

    override fun getItemCount(): Int {
        return movieList.size
    }

    fun updateMovieList(newMovieList: ArrayList<Movies>) {
        movieList.clear()
        movieList.addAll(newMovieList)
        notifyDataSetChanged()
    }


}