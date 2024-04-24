package repository;

import at.ac.fhcampuswien.fhmdb.models.Movie;
import com.j256.ormlite.dao.Dao;
import entities.MovieEntity;
import entities.WatchlistMovieEntity;

import java.sql.SQLException;
import java.util.List;

public class WatchlistRepository {
    private Dao<WatchlistMovieEntity,Long> dao;

    public List<WatchlistMovieEntity> getWatchlist() throws SQLException {
        return dao.queryForAll();
    }

    public int addToWatchlist(WatchlistMovieEntity movie) throws SQLException {
        return dao.create(movie);
    }

    public int removeFromWatchlist(String apiId) throws SQLException {
        List<WatchlistMovieEntity> movies = dao.queryForEq("apiId", apiId);
        if (!movies.isEmpty()) {
            dao.delete(movies);
        }
        return 0;
    }

    public WatchlistMovieEntity movieToEntity(Movie movie)
    {
        MovieEntity movieEntity = new MovieEntity(movie.getID(), movie.getTitle(), movie.getDescription(), MovieEntity.genresToString(movie.getGenres()), movie.getReleaseYear(), movie.getImgUrl(), movie.getLengthInMinutes(), movie.getRating());
        return new WatchlistMovieEntity(movieEntity.getApiID(), movieEntity);
    }
}
