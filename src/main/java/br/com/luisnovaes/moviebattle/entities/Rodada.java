package br.com.luisnovaes.moviebattle.entities;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
public class Rodada {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "jogo_id")
    private Jogo jogo;
    @ManyToOne
    @JoinColumn(name="user_id", nullable=false)
    private Usuario usuario;
    @ManyToMany(cascade = { CascadeType.ALL })
    private List<Movie> movies = new ArrayList<>();
    private boolean isFinalizada;
    private boolean isAcerto;

    public Rodada() {}

    public Rodada(List<Movie> movies, Usuario usuario, Jogo jogo) {
        this.movies = movies;
        this.isFinalizada = false;
        this.jogo = jogo;
        this.usuario = usuario;
    }

    public boolean verificarResposta(int numeroFilme) {
        if (Objects.nonNull(numeroFilme)) {
            if (Objects.equals(numeroFilme, 1)) {
                return movies.get(0).getRating() > movies.get(1).getRating();
            } else if (Objects.equals(numeroFilme, 2)) {
                return movies.get(1).getRating() > movies.get(0).getRating();
            }
        }
        throw new IllegalArgumentException("Você só pode escolher entre o filme 1 e filme 2");
    }

    public List<Movie> getMovies() {
        return movies;
    }

    public void setMovies(List<Movie> movies) {
        this.movies = movies;
    }

    public Jogo getJogo() {
        return jogo;
    }

    public void setJogo(Jogo jogo) {
        this.jogo = jogo;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public boolean isFinalizada() {
        return isFinalizada;
    }

    public void setFinalizada(boolean finalizada) {
        isFinalizada = finalizada;
    }

    public boolean isAcerto() {
        return isAcerto;
    }

    public void setAcerto(boolean acerto) {
        isAcerto = acerto;
    }
}
