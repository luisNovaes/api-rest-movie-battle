package br.com.luisnovaes.moviebattle.service;

import java.util.List;

import br.com.luisnovaes.moviebattle.entities.Jogo;
import br.com.luisnovaes.moviebattle.entities.Movie;

public interface MovieService {

    List<Movie> getTwoRandomMovies(Jogo jogo);

}

