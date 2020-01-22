package com.project.seenit.controllers;

import com.project.seenit.models.Movie;
import com.project.seenit.services.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
public class MovieController {

    private final MovieService service;

    @Autowired
    public MovieController(MovieService service) {
        this.service = service;
    }

    @PostMapping("/movies")
    public ResponseEntity<Movie> addMovieToUser(@RequestBody Movie movie) {
        return new ResponseEntity<>(service.addMovieToUser(movie), HttpStatus.CREATED);
    }

    @GetMapping("/movies/all/{username}")
    public ResponseEntity<Iterable<Movie>> findUsersMovieList(@PathVariable String username) {
        return new ResponseEntity<>(service.findUsersMovieList(username), HttpStatus.OK);
    }

    @GetMapping("/movies")
    public ResponseEntity<Movie> findByMovieTitle(@RequestParam String movieTitle){
        return new ResponseEntity<>(service.findByMovieByTitle(movieTitle), HttpStatus.OK);
    }

    @GetMapping("/movies/favorites")
    public ResponseEntity<Iterable<Movie>> findFavoriteMovies(){
        return new ResponseEntity<>(service.findFavoriteMovies(), HttpStatus.OK);
    }

    @PutMapping("/movies")
    public ResponseEntity<Movie> updateMovie(@RequestParam String movieTitle,
                                             @Valid @RequestBody Movie movie){
        return new ResponseEntity<>(service.updateMovie(movieTitle, movie), HttpStatus.OK);
    }

    @DeleteMapping("/movies/{username}")
    public ResponseEntity<Boolean> deleteByMovieTitle(@PathVariable String username, @RequestParam Long id){
        return new ResponseEntity<>(service.deleteMovie(username, id), HttpStatus.OK);
    }

}
