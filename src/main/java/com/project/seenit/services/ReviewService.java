package com.project.seenit.services;

import com.project.seenit.models.Review;
import com.project.seenit.repositories.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReviewService {

    private final ReviewRepository repository;

    @Autowired
    public ReviewService(ReviewRepository repository) {
        this.repository = repository;
    }

    public Review addReview(Review review){
        return repository.save(review);
    }

    public Iterable<Review> findReviews(Long movieId){
        return repository.findAllByMovieId(movieId);
    }

    public Review updateReview(Review review){
        Review tempReview = new Review();
        tempReview.setUsername(review.getUsername());
        tempReview.setMovieId(review.getMovieId());
        tempReview.setReview(review.getReview());

        return repository.save(tempReview);
    }

    public Boolean deleteReview(Long id){
        repository.deleteById(id);
        return true;
    }
}
