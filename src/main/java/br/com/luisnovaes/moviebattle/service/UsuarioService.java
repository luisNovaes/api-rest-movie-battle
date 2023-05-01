package br.com.luisnovaes.moviebattle.service;

import java.util.List;

import br.com.luisnovaes.moviebattle.entities.Usuario;

public interface UsuarioService {

    Usuario getUsuario(String string);

    List<Usuario> getRanking();

    String getUsernameUsuarioLogado();
}
