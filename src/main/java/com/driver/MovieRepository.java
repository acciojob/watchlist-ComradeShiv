package com.driver;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

@Repository
public class MovieRepository {

    HashMap<String, Movie> movieDB = new HashMap<>();
    HashMap<String, Director> directorDB = new HashMap<>();
    HashMap<String, ArrayList<String>> directorMovieDB = new HashMap<>();


    public String addMovie(Movie movie) {
        movieDB.put(movie.getName(), movie);
        return "Movie has been successfully added";
    }

    public String addDirector(Director director) {
        directorDB.put(director.getName(), director);
        return "Director details has been successfully added";
    }

    public String addMovieDirectorPair(String movieName, String directorName) {
        if(movieDB.containsKey(movieName) && directorDB.containsKey(directorName)) {
            if(!directorMovieDB.containsKey(directorName))
                directorMovieDB.put(directorName, new ArrayList<String>());
            directorMovieDB.get(directorName).add(movieName);
            return "Movie-Director pair has been successfully added";
        }
        return "Movie or Director may not exist";
    }

    public Movie getMovieByName(String movieName) {
        if(!movieDB.containsKey(movieName))
            return null;
        return movieDB.get(movieName);
    }

    public Director getDirectorByName(String directorName) {
        if(!directorDB.containsKey(directorName))
            return null;
        return directorDB.get(directorName);
    }

    public ArrayList<String> getMoviesByDirectorName(String directorName) {
        if(!directorMovieDB.containsKey(directorName))
            return new ArrayList<String>();
        return directorMovieDB.get(directorName);
    }

    public ArrayList<Movie> findAllMovies() {
        ArrayList<Movie> movieList = new ArrayList<>();
        for(Map.Entry<String, Movie> movie: movieDB.entrySet())
            movieList.add(movie.getValue());
        return movieList;
    }

    public String deleteDirectorByName(String directorName) {
        if(!directorMovieDB.containsKey(directorName))
            return "Director not found";
        for(String movieName: directorMovieDB.get(directorName))
            if(movieDB.containsKey(movieName))
                movieDB.remove(movieName);
        if(directorDB.containsKey(directorName))
            directorDB.remove(directorName);
        directorMovieDB.remove(directorName);
        return "All data about " + directorName + " has been erased";
    }

    public String deleteAllDirectors() {
        for(String directorName: directorDB.keySet()) {
            for(String movieName: directorMovieDB.get(directorName))
                if(movieDB.containsKey(movieName))
                    movieDB.remove(movieName);

            directorMovieDB.remove(directorName);
            directorDB.remove(directorName);
        }
        return "All data about all directors have been erased";
    }
}
