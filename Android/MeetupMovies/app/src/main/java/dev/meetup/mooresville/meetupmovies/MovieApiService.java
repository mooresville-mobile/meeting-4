package dev.meetup.mooresville.meetupmovies;


import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface MovieApiService {

    @GET("search/movie")
    Call<MovieResponse> getMovies(
            @Query("api_key") String apiKey,
            @Query("language") String language,
            @Query("query") String query,
            @Query("page") String page,
            @Query("include_adult") boolean includeAdult);

}
