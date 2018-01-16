package dev.meetup.mooresville.meetupmovies;


import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

class MovieResponse {

    @SerializedName("results")
    private List<Movie> movies;

    MovieResponse() {
        movies = new ArrayList<Movie>();
    }

    static MovieResponse parseJSON(String response) {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES);
        Gson gson = gsonBuilder.create();
        MovieResponse movieResponse = gson.fromJson(response, MovieResponse.class);
        return movieResponse;
    }

    List<Movie> getMovies() {
        return movies;
    }
}
