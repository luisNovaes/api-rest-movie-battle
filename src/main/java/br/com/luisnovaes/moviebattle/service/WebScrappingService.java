package br.com.luisnovaes.moviebattle.service;

import java.util.List;

import br.com.luisnovaes.moviebattle.entities.Movie;

public interface WebScrappingService {

    List<Movie> getMoviesFromWebScrapping();

}
