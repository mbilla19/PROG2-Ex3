package entities;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;
import entities.MovieEntity;

@DatabaseTable(tableName = "WatchlistMovie")
public class WatchlistMovieEntity{

    @DatabaseField(generatedId = true)
    private long id;

    @DatabaseField
    private String apiID;


    @DatabaseField(foreign = true, columnName = "movie_id")
    private MovieEntity movie;


    public WatchlistMovieEntity(String apiID, MovieEntity movie) {
        this.apiID = apiID;
        this.movie = movie;
    }
    public WatchlistMovieEntity(){

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getApiID() {
        return apiID;
    }

    public void setApiID(String apiID) {
        this.apiID = apiID;
    }

    public MovieEntity getMovie() {
        return movie;
    }

    public void setMovie(MovieEntity movie) {
        this.movie = movie;
    }


}