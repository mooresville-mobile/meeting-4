package dev.meetup.mooresville.meetupmovies;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Date;

public class MovieAPIMapper {

    public static ArrayList<Movie> retrieveMoviesFromResponse(String response) throws
            JSONException {
        if (null == response) {
            return new ArrayList<>();
        }
        JSONObject resultObject = new JSONObject(response);
        ArrayList<Movie> movies = new ArrayList<>();

        JSONArray jsonArray = resultObject.getJSONArray("results");

        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject jsonObject = jsonArray.getJSONObject(i);
            if (null != jsonObject) {
                String title = null;
                Double voteAverage = null;
                String releaseDate = null;
                String overview = null;
                String posterURLString = null;

                if (jsonObject.has("title")) {
                    title = jsonObject.getString("title");
                }
                if (jsonObject.has("vote_average")) {
                    voteAverage = jsonObject.getDouble("vote_average");
                }
                if (jsonObject.has("release_date")) {
                    releaseDate = jsonObject.getString("release_date");
                }
                if (jsonObject.has("overview")) {
                    overview = jsonObject.getString("overview");
                }
                if (jsonObject.has("poster_path")) {
                    posterURLString = jsonObject.getString("poster_path");
                }
                Movie movie = new Movie(title, voteAverage, overview, posterURLString, releaseDate);
                movies.add(movie);
            }
        }
        return movies;
    }
}
