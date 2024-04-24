package entities;

import at.ac.fhcampuswien.fhmdb.models.Genre;
import at.ac.fhcampuswien.fhmdb.models.Movie;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@DatabaseTable(tableName = "Movie")
public class MovieEntity {
    @DatabaseField(generatedId = true)
    private long ID;

    @DatabaseField
    private String apiID;

    @DatabaseField
    private String title;

    @DatabaseField
    private String description;

    @DatabaseField
    private String genres;

    @DatabaseField
    private int releaseYear;

    @DatabaseField
    private String imgUrl;

    @DatabaseField
    private int lengthInMinutes;

    @DatabaseField
    private double rating;

    public MovieEntity(String ApiID, String title, String description, String genres, int releaseYear, String imgURL, int lengthInMinutes, double rating)
    {
        this.apiID = ApiID;
        this.title = title;
        this.description = description;
        this.genres = genres;
        this.releaseYear = releaseYear;
        this.imgUrl = imgURL;
        this.lengthInMinutes = lengthInMinutes;
        this.rating = rating;
    }

    public MovieEntity() {

    }

    public long getID() {
        return ID;
    }

    public void setID(long ID) {
        this.ID = ID;
    }

    public String getApiID() {
        return apiID;
    }

    public void setApiID(String apiID) {
        this.apiID = apiID;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getGenres() {
        return genres;
    }

    public void setGenres(String genres) {
        this.genres = genres;
    }

    public int getReleaseYear() {
        return releaseYear;
    }

    public void setReleaseYear(int releaseYear) {
        this.releaseYear = releaseYear;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public int getLengthInMinutes() {
        return lengthInMinutes;
    }

    public void setLengthInMinutes(int lengthInMinutes) {
        this.lengthInMinutes = lengthInMinutes;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public static String genresToString(List<Genre> genres) {

        String result = "";
        for (var item: genres) {
            result += item.toString() + ",";
        }
        return result;
    }
    public static List<MovieEntity> fromMovies(List<Movie> movies){
        List<MovieEntity> movieEntities = new ArrayList<>();
        for (Movie movie : movies) {
            MovieEntity movieEntity = new MovieEntity();
            movieEntity.setApiID(movie.getID());
            movieEntity.setTitle(movie.getTitle());
            movieEntity.setDescription(movie.getDescription());
            movieEntity.setGenres(genresToString(movie.getGenres())); // Hier müssen Sie sicherstellen, dass die genresToString Methode korrekt implementiert ist.
            movieEntity.setReleaseYear(movie.getReleaseYear());
            movieEntity.setImgUrl(movie.getImgUrl());
            movieEntity.setLengthInMinutes(movie.getLengthInMinutes());
            movieEntity.setRating(movie.getRating());

            movieEntities.add(movieEntity);
        }
        return movieEntities;
    }
    public Movie toMovies(List<MovieEntity> movieEntities) {
        List<Genre> genres = Arrays.stream(this.genres.split(","))
                .map(Genre::valueOf)
                .collect(Collectors.toList());
        return new Movie(apiID, title, description, genres, releaseYear, imgUrl, lengthInMinutes, rating);
    }

    public Movie toMovie() {
        List<Genre> genres = Arrays.stream(this.genres.split(","))
                .map(Genre::valueOf)
                .collect(Collectors.toList());
        return new Movie(apiID, title, description, genres, releaseYear, imgUrl, lengthInMinutes, rating);
    }

//    public static String genresToString(List<Genre> genres) {
//        return genres.stream()
//                .map(genre -> genre.name())
//                .collect(Collectors.joining(","));
//    }

}
