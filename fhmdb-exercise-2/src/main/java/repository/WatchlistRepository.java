package repository;

import at.ac.fhcampuswien.fhmdb.models.Movie;
import com.j256.ormlite.dao.Dao;
import database.DatabaseManager;
import entities.MovieEntity;
import entities.WatchlistMovieEntity;

import java.sql.SQLException;
import java.util.List;

public class WatchlistRepository {
    private Dao<WatchlistMovieEntity,Long> dao;

    public WatchlistRepository()
    {
        this.dao = DatabaseManager.getInstance().getWatchlistDao();
    }
    public List<WatchlistMovieEntity> getWatchlist() throws SQLException {
        return dao.queryForAll();
    }

    public int addToWatchlist(WatchlistMovieEntity movie) throws SQLException {
        if (dao == null)  this.dao = DatabaseManager.getInstance().getWatchlistDao();
        return dao.create(movie);
    }

    public int removeFromWatchlist(String apiId) throws SQLException {
        List<WatchlistMovieEntity> watchListMovies = dao.queryForEq("apiId", apiId);
        if (!watchListMovies.isEmpty()) {
            dao.delete(watchListMovies);
        }
        return 0;
    }

    public WatchlistMovieEntity movieToEntity(Movie movie)
    {
        MovieEntity movieEntity = new MovieEntity(movie.getID(), movie.getTitle(), movie.getDescription(), MovieEntity.genresToString(movie.getGenres()), movie.getReleaseYear(), movie.getImgUrl(), movie.getLengthInMinutes(), movie.getRating());
        return new WatchlistMovieEntity(movieEntity.getApiID(), movieEntity);
    }
}
