package br.com.luisnovaes.moviebattle.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.List;

@Entity
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String nome;
    @Column(unique = true)
    private String username;
    private String senha;
    private Double melhorScore = 0d;
    @JsonIgnore
    private int posicao;
    @JsonIgnore
    @OneToMany(mappedBy = "usuario")
    private List<Jogo> jogos;

    public Usuario() {}

    public Usuario(String nome, String usuario, String senha) {
        this.nome = nome;
        this.username = usuario;
        this.senha = senha;
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public Double getMelhorScore() {
        return melhorScore;
    }

    public void setMelhorScore(Double melhorScore) {
        this.melhorScore = melhorScore;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getPosicao() {
        return posicao;
    }

    public void setPosicao(int posicao) {
        this.posicao = posicao;
    }

    public List<Jogo> getJogos() {
        return jogos;
    }

    public void setJogos(List<Jogo> jogos) {
        this.jogos = jogos;
    }

}
