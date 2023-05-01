package br.com.luisnovaes.moviebattle.service.impl;

import br.com.luisnovaes.moviebattle.entities.Jogo;
import br.com.luisnovaes.moviebattle.entities.Usuario;
import br.com.luisnovaes.moviebattle.repository.MovieRepository;
import br.com.luisnovaes.moviebattle.service.MovieService;
import br.com.luisnovaes.moviebattle.service.impl.MovieServiceImpl;
import br.com.luisnovaes.moviebattle.utils.TestUtils;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

@ExtendWith(MockitoExtension.class)
class MovieServiceImplTest {

    final String NOME_USUARIO = TestUtils.NOME_USUARIO;

    @Mock
    MovieRepository movieRepository;

    MovieService movieService;

    Usuario user;
    Jogo jogo;

    @BeforeEach
    void setUp() {
        user = TestUtils.getUsuarioMock(NOME_USUARIO);
        jogo = TestUtils.getJogoMock(user);
        movieService = new MovieServiceImpl(movieRepository);
    }

    @Test
    void deveSucessoAoBuscarDoisFilmesAleatorios() {
        Mockito.when(movieRepository.findAll()).thenReturn(TestUtils.getQuarentaFilmes());

        var filmesAleatorios = movieService.getTwoRandomMovies(jogo);

        assertNotEquals(filmesAleatorios.get(0).getId(), filmesAleatorios.get(1).getId());
        assertNotEquals(filmesAleatorios.get(0).getRating(), filmesAleatorios.get(1).getRating());
        assertEquals(2, filmesAleatorios.size() );
    }

}