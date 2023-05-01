package br.com.luisnovaes.moviebattle.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.luisnovaes.moviebattle.entities.Usuario;

import java.util.List;
import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    Optional<Usuario> findByUsername(String usuario);

    @Query(value = "SELECT RANK() OVER (ORDER BY u.MELHOR_SCORE DESC) posicao, * FROM Usuario u ORDER BY posicao", nativeQuery = true)
    List<Usuario> getRanking();

}
