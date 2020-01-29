package com.project.seenit.repositories;

import com.project.seenit.models.Review;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReviewRepository extends CrudRepository<Review, Long> {

    @Query("SELECT c FROM Review c WHERE c.movieId = ?1 ORDER BY c.id DESC")
    Iterable<Review> findAllByMovieId(Long movieId);

}
