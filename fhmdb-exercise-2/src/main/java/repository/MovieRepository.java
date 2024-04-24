package repository;

import at.ac.fhcampuswien.fhmdb.models.Movie;
import com.j256.ormlite.dao.Dao;
import entities.MovieEntity;

import java.sql.SQLException;
import java.util.List;

public class MovieRepository {
    private Dao<MovieEntity, Long> dao;

    public List<MovieEntity> getAllMovies() throws SQLException {
        return dao.queryForAll();
    }

    public int removeAll() throws SQLException {
        dao.deleteBuilder().delete();
        return 0;
    }

    public MovieEntity getMovie() throws SQLException {
        List<MovieEntity> list = dao.queryForAll();
        if (!list.isEmpty()) {
            return list.get(0); // Gibt den ersten Film in der Liste zurück
        }
        return null;
    }

    public int addAllMoviesList(List<Movie> movies) throws SQLException {
        List<MovieEntity> movieList = MovieEntity.fromMovies(movies);
        for (MovieEntity movie:movieList) {
            dao.createIfNotExists(movie);
        }
        return 0;
    }

}
