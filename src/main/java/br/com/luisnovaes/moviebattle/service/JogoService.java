package br.com.luisnovaes.moviebattle.service;

import br.com.luisnovaes.moviebattle.entities.Jogo;

public interface JogoService {

    Jogo inicializarJogo();

    Jogo findJogoByUsuario();

    String finalizarJogo(Jogo jogo);

}
