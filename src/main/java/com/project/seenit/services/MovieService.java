package com.project.seenit.services;

import com.project.seenit.models.Movie;
import com.project.seenit.repositories.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

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
        if (repository.findMovieByIdAndUsername(movie.getId(), movie.getUsername()) == null)
            return repository.save(movie);

        return null;
    }

    public Iterable<Movie> findUsersMovieList(String username) {
        return repository.findMoviesByUsername(username);
    }

    public Movie findByMovieByTitle(String title, String username){
        return repository.findMovieByTitleAndUsername(title, username);
    }

    public Iterable<Movie> findFavoriteMovies(){
        return repository.findMovieByUsernameAndFavorite(userService.getCurrentUser());
    }

//    public Movie updateMovie(String title, Movie movie){
////        Movie ogMovie = findByMovieByTitle(title);
////        return repository.save(ogMovie);
////    }

    public boolean deleteMovie(String username, Long id){
        return repository.deleteMovieByUsernameAndId(username, id);
    }
}

