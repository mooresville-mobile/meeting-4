package dev.meetup.mooresville.meetupmovies;


import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

class MovieResponse {

    @SerializedName("results")
    private List<Movie> movies;

    MovieResponse() {
        movies = new ArrayList<Movie>();
    }

    List<Movie> getMovies() {
        return movies;
    }
}
