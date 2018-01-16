package dev.meetup.mooresville.meetupmovies;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();

    public static final String BASE_URL = "https://api.themoviedb.org/3/";
    public static final String API_KEY = "99651ffbdb70f6a2d891f47ea9928677";
    public static final String FROZEN = "frozen";
    public static final String PAGE = "1";
    public static final boolean INCLUDE_ADULT = false;

    private MovieAdapter mAdapter;
    private ProgressDialog mProgressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bindViews();

        if (isNetworkConnected()) {
            showLoadingIndicator();
            startDownload();
        } else {
            showNetworkConnectionError();
        }
    }

    private void bindViews() {
        RecyclerView recyclerView = findViewById(R.id.movieRecyclerView);

        recyclerView.setHasFixedSize(true);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        mAdapter = new MovieAdapter(new ArrayList<Movie>());
        recyclerView.setAdapter(mAdapter);
    }

    private boolean isNetworkConnected() {
        ConnectivityManager connMgr = (ConnectivityManager)
                getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();

        return networkInfo != null && networkInfo.isConnected();
    }

    private void showLoadingIndicator() {
        mProgressDialog = new ProgressDialog(this);
        mProgressDialog.setMessage("Loading...");
        mProgressDialog.setCancelable(false);
        mProgressDialog.show();
    }

    private void startDownload() {
        Retrofit retrofit = getRetrofit();

        MovieApiService movieApiService = retrofit.create(MovieApiService.class);

        Call<MovieResponse> call = movieApiService.getMovies(
                API_KEY,
                Locale.getDefault().toString(),
                FROZEN,
                PAGE,
                INCLUDE_ADULT);

        call.enqueue(new Callback<MovieResponse>() {
            @Override
            public void onResponse(Call<MovieResponse> call, Response<MovieResponse> response) {
                MovieResponse movieResponse = response.body();
                downloadComplete(movieResponse.getMovies());
            }

            @Override
            public void onFailure(Call<MovieResponse> call, Throwable t) {
                if (mProgressDialog != null) {
                    mProgressDialog.dismiss();
                }
                showDownloadError(t);
            }
        });
    }

    private Retrofit getRetrofit() {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES);
        Gson gson = gsonBuilder.create();

        return new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
    }

    private void showNetworkConnectionError() {
        new AlertDialog.Builder(this)
                .setTitle("No Internet Connection")
                .setMessage("It looks like your internet connection is off. Please turn it " +
                        "on and try again")
                .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                    }
                })
                .setIcon(android.R.drawable.ic_dialog_alert)
                .show();
    }

    private void showDownloadError(Throwable throwable) {
        new AlertDialog.Builder(this)
                .setTitle("Download Error")
                .setMessage("We had a problem getting your list of movies.")
                .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                    }
                })
                .setIcon(android.R.drawable.ic_dialog_alert)
                .show();
    }

    public void downloadComplete(List<Movie> movies) {
        if (mProgressDialog != null) {
            mProgressDialog.dismiss();
        }
        mAdapter.updateMovies(movies);
    }
}
