package br.com.luisnovaes.moviebattle.service.impl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import br.com.luisnovaes.moviebattle.service.WebScrappingService;
import br.com.luisnovaes.moviebattle.service.impl.ImdbWebScrapping;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class ImdbWebScrappingTest {

    WebScrappingService webScrappingService;

    @BeforeEach
    void setUp() {
        webScrappingService = new ImdbWebScrapping();
    }

    @Test
    void deveSucessoAoRealizarWebScrappingDoImdb() {
        var filmes = webScrappingService.getMoviesFromWebScrapping();

        assertNotNull(filmes);
        assertEquals(50, filmes.size());
        assertFalse(filmes.isEmpty());
    }
}