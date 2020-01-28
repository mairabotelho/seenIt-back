package com.project.seenit.controllers;

import com.project.seenit.models.Review;
import com.project.seenit.services.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
public class ReviewController {

    private final ReviewService service;

    @Autowired
    public ReviewController(ReviewService service) {
        this.service = service;
    }

    @PostMapping("/review")
    public ResponseEntity<Review> addUser(@Valid @RequestBody Review review) {
        return new ResponseEntity<>(service.addReview(review), HttpStatus.CREATED);
    }

    @GetMapping("/review/{movieId}")
    public ResponseEntity<Iterable<Review>> getReviews(@PathVariable Long movieId){
        return new ResponseEntity<>(service.findReviews(movieId), HttpStatus.OK);
    }
}
