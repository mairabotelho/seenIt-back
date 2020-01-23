package com.project.seenit.repositories;

import com.project.seenit.models.Movie;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface MovieRepository extends CrudRepository<Movie, String> {

    @Query("SELECT c FROM Movie c WHERE c.username = ?1 AND c.watchlist = false ORDER BY c.title ASC")
    Iterable<Movie> findMoviesByUsername(String username);

    @Query("SELECT c FROM Movie c WHERE c.title = ?1 AND c.username = ?2")
    Movie findMovieByTitleAndUsername(String title, String username);

    @Query("SELECT c FROM Movie c WHERE c.username = ?1 AND c.favorite = true")
    Iterable<Movie> findMovieByUsernameAndFavorite(String username);

    boolean deleteMovieByUsernameAndId(String username, Long id);
}
