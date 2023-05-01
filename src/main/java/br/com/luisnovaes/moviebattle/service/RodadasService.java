package br.com.luisnovaes.moviebattle.service;

import br.com.luisnovaes.moviebattle.entities.Rodada;
import br.com.luisnovaes.moviebattle.entities.Usuario;

public interface RodadasService {

    Rodada inicializarRodada(Usuario usuario);

    String jogar(int numeroFilme);

}