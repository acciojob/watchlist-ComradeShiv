package com.driver;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@Controller
@RequestMapping("/movies")
@Slf4j
public class MovieController {
    @Autowired
    MovieService movieService;


    @PostMapping("/add-movie")
    public ResponseEntity addMovie(@RequestBody Movie movie) {
        return new ResponseEntity<>(movieService.addMovie(movie), HttpStatus.CREATED);
    }

    @PostMapping("/add-director")
    public ResponseEntity addDirector(@RequestBody Director director) {
        return new ResponseEntity(movieService.addDirector(director), HttpStatus.CREATED);
    }

    @PutMapping("/add-movie-director-pair")
    public ResponseEntity addMovieDirectorPair(@RequestParam("name") String movieName, @RequestParam("dir") String directorName) {
        return new ResponseEntity<>(movieService.addMovieDirectorPair(movieName, directorName), HttpStatus.CREATED);
    }

    @GetMapping("/get-movie-by-name/{name}")
    public ResponseEntity getMovieByName(@PathVariable("name") String movieName) {
        Movie movie = movieService.getMovieByName(movieName);
        if(movie == null)
            return new ResponseEntity<>(new Movie(), HttpStatus.NOT_FOUND);
        return new ResponseEntity(movie, HttpStatus.FOUND);
    }

    @GetMapping("/get-director-by-name/{name}")
    public ResponseEntity getDirectorByName(@PathVariable("name") String directorName) {
        Director director = movieService.getDirectorByName(directorName);
        if(director == null)
            return new ResponseEntity<>(new Director(), HttpStatus.NOT_FOUND);
        return new ResponseEntity(director, HttpStatus.FOUND);
    }

    @GetMapping("/get-movies-by-director-name/{director}")
    public ResponseEntity getMoviesByDirectorName(@PathVariable("director") String directorName) {
        ArrayList<String> movieList = movieService.getMoviesByDirectorName(directorName);
        if(movieList.size() == 0)
            return new ResponseEntity<>(new ArrayList<String>(), HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(movieList, HttpStatus.FOUND);
    }

    @GetMapping("/get-all-movies")
    public ResponseEntity findAllMovies() {
        ArrayList<Movie> movieList = movieService.findAllMovies();
        if(movieList.size() == 0)
            return new ResponseEntity<>(new ArrayList<Movie>(), HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(movieList, HttpStatus.FOUND);
    }

    @DeleteMapping("/delete-director-by-name")
    public ResponseEntity deleteDirectorByName(@RequestParam String directorName) {
        return new ResponseEntity<>(movieService.deleteDirectorByName(directorName), HttpStatus.MOVED_PERMANENTLY);
    }

    @DeleteMapping("/delete-all-directors")
    public ResponseEntity deleteAllDirectors() {
        return new ResponseEntity<>(movieService.deleteAllDirectors(), HttpStatus.MOVED_PERMANENTLY);
    }
}
