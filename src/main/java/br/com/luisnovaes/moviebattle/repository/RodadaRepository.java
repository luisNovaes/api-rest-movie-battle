package br.com.luisnovaes.moviebattle.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.luisnovaes.moviebattle.entities.Rodada;

import java.util.Optional;

public interface RodadaRepository extends JpaRepository<Rodada, Long> {

    Optional<Rodada> findByUsuarioUsernameAndIsFinalizadaFalse(String usuario);

}
