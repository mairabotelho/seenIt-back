package com.project.seenit.services;

import com.project.seenit.models.Movie;
import com.project.seenit.repositories.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MovieService {

    private final MovieRepository repository;
    private final UserService userService;

    @Autowired
    public MovieService(MovieRepository repository, UserService userService) {
        this.repository = repository;
        this.userService = userService;
    }

    public Movie addMovieToUser(Movie movie) {
        return repository.save(movie);
    }

    public Iterable<Movie> findUsersMovieList(String username) {
        return repository.findMoviesByUsername(username);
    }

    public Movie findByMovieByTitle(String title){
        return repository.findMovieByTitle(userService.getCurrentUser(), title);
    }

    public Iterable<Movie> findFavoriteMovies(){
        return repository.findMovieByUsernameAndFavorite(userService.getCurrentUser());
    }

    public Movie updateMovie(String title, Movie movie){
        Movie ogMovie = findByMovieByTitle(title);
        return repository.save(ogMovie);
    }

//    public Boolean deleteMovie(String poster){
//        return repository.deleteMovieByPosterAndUsername(poster, userService.getCurrentUser());
//    }
}

