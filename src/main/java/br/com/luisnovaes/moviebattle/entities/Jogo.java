package br.com.luisnovaes.moviebattle.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
public class Jogo {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    private int erros = 0;
    private Double score = 0d;
    @JsonIgnore
    @OneToMany(mappedBy = "jogo")
    private List<Rodada> rodadas = new ArrayList<>();
    @JsonIgnore
    private boolean isFinalizado;
    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

    private long scoreFinal;

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Jogo() {}

    public Jogo(Usuario usuario) {
        this.usuario = usuario;
        this.isFinalizado = false;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public Double getScore() {
        return score;
    }

    public int getErros() {
        return erros;
    }

    public void setErros(int erros) {
        this.erros = erros;
    }

    public List<Rodada> getRodadas() {
        return rodadas;
    }

    public void setScore(Double score) {
        this.score = score;
    }

    public boolean isFinalizado() {
        return isFinalizado;
    }

    public void setRodadas(List<Rodada> rodadas) {
        this.rodadas = rodadas;
    }

    public void setFinalizado(boolean finalizado) {
        isFinalizado = finalizado;
    }

    public long getScoreFinal() {
        return scoreFinal;
    }

    public void setScoreFinal(long scoreFinal) {
        this.scoreFinal = scoreFinal;
    }

    public long calcularPontuacaoFinal() {
        var numeroDeAcertos = getRodadas()
                .stream()
                .filter(Rodada::isAcerto)
                .count();
        var numeroTotalDeRodadas = getRodadas().stream().count();
        if (numeroTotalDeRodadas > 0) {
            var porcentagemDeAcerto = (numeroDeAcertos * 100) / numeroTotalDeRodadas;
            return numeroTotalDeRodadas * porcentagemDeAcerto;
        }
        return 0;
    }
}
