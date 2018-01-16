package dev.meetup.mooresville.meetupmovies;


import com.google.gson.annotations.SerializedName;

class Movie {

    private String title;
    private Double voteAverage;
    private String overview;
    private String releaseDate;

    @SerializedName("poster_path")
    private String posterURLString;

    Movie(String title,
            Double voteAverage,
            String overview,
            String posterURLString,
            String releaseDate) {
        this.title = title;
        this.voteAverage = voteAverage;
        this.overview = overview;
        this.posterURLString = posterURLString;
        this.releaseDate = releaseDate;
    }

    String getTitle() {
        return title;
    }

    Double getVoteAverage() {
        return voteAverage;
    }

    String getOverview() {
        return overview;
    }

    String getPosterURLString() {
        return posterURLString;
    }

    String getReleaseDate() {
        return releaseDate;
    }
}
