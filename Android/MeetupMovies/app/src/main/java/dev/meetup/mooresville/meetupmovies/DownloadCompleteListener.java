package dev.meetup.mooresville.meetupmovies;


import java.util.List;

public interface DownloadCompleteListener {
    void downloadComplete(List<Movie> movies);
}
