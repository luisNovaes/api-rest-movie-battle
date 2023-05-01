package br.com.luisnovaes.moviebattle.utils;

import org.mockito.Mockito;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import br.com.luisnovaes.moviebattle.entities.Jogo;
import br.com.luisnovaes.moviebattle.entities.Movie;
import br.com.luisnovaes.moviebattle.entities.Rodada;
import br.com.luisnovaes.moviebattle.entities.Usuario;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class TestUtils {

    public static final String NOME_USUARIO = "luisnovaes";

    public static final Usuario getUsuarioMock(String nomeUsuario) {
        var usuario = new Usuario("Luis", nomeUsuario, "123");
        usuario.setJogos(Collections.singletonList(getJogoMock(usuario)));
        return usuario;
    }

    public static final Jogo getJogoMock(Usuario usuario) {
        var jogo = new Jogo(usuario);
        jogo.setRodadas(Collections.singletonList(new Rodada(getDoisFilmes(), usuario, jogo)));
        return jogo;
    }

    public static final List<Movie> getDoisFilmes() {
        List<Movie> movies = new ArrayList<>();
        var primeiroFilme = new Movie("Star Wars", 10.0, "Guerra nas estrelas, piu piu");
        var segundoFilme = new Movie("Her", 8.0, "Homem apaixonado pela Siri");
        primeiroFilme.setOpcao(1);
        segundoFilme.setOpcao(2);
        movies.addAll(Arrays.asList(primeiroFilme, segundoFilme));
        return movies;
    }

    public static final Rodada getRodadaMock(Usuario usuario) {
        return new Rodada(getDoisFilmes(), usuario, getJogoMock(usuario));
    }

    public static final List<Movie> getQuarentaFilmes() {
        List<Movie> filmes = new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            var movie = new Movie("Filme " + i, Double.parseDouble(String.valueOf(i)), "Plot " + i);
            movie.setId((Long.parseLong(String.valueOf(i))));
            filmes.add(movie);
        }
        return filmes;
    }


}
