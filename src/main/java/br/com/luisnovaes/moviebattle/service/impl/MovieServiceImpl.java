package br.com.luisnovaes.moviebattle.service.impl;

import br.com.luisnovaes.moviebattle.entities.Jogo;
import br.com.luisnovaes.moviebattle.entities.Movie;
import br.com.luisnovaes.moviebattle.repository.MovieRepository;
import br.com.luisnovaes.moviebattle.service.MovieService;
import br.com.luisnovaes.moviebattle.service.WebScrappingService;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Random;

@Service
public class MovieServiceImpl implements MovieService {

    private MovieRepository movieData;

    public MovieServiceImpl(MovieRepository movieData) {
        this.movieData = movieData;
    }

    @Override
    public List<Movie> getTwoRandomMovies(Jogo jogo) {
        List<Movie> filmesRodadaAtual = new ArrayList<>();
        var allMovies = movieData.findAll();
        var primeiroFilme = allMovies.get(new Random().nextInt(allMovies.size() - 1));
        var segundoFilme = allMovies.get(new Random().nextInt(allMovies.size() - 1));
        var temMesmoId = Objects.equals(primeiroFilme.getId(), segundoFilme.getId());
        if (allMovies.size() > 2 && !Objects.equals(primeiroFilme.getRating(), segundoFilme.getRating()) && !temMesmoId) {
            filmesRodadaAtual.add(0, primeiroFilme);
            filmesRodadaAtual.add(1, segundoFilme);
            if (!verificarSeFilmesEstaoRepetidos(filmesRodadaAtual, jogo)) {
                filmesRodadaAtual.get(0).setOpcao(1);
                filmesRodadaAtual.get(1).setOpcao(2);
                return filmesRodadaAtual;
            }
        }
        return getTwoRandomMovies(jogo);
    }


    private boolean verificarSeFilmesEstaoRepetidos(List<Movie> filmesRodadaAtual, Jogo jogo) {
        var rodadasPassadas = jogo.getRodadas();
        return rodadasPassadas.stream().anyMatch(rodada -> {
            var filmesRodadaPassadas = rodada.getMovies();
            var primeiroFilmeRodadaPassada = filmesRodadaPassadas.get(0);
            var segundoFilmeRodadaPassada = filmesRodadaPassadas.get(1);
            var primeiroFilmeRodadaAtual = filmesRodadaAtual.get(0);
            var segundoFilmeRodadaAtual = filmesRodadaAtual.get(1);
            return primeiroFilmeRodadaAtual == primeiroFilmeRodadaPassada && segundoFilmeRodadaAtual == segundoFilmeRodadaPassada
                    || primeiroFilmeRodadaAtual == segundoFilmeRodadaPassada && segundoFilmeRodadaAtual == primeiroFilmeRodadaPassada;
        });
    }
}
