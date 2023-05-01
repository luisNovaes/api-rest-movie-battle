package br.com.luisnovaes.moviebattle.utils;

import br.com.luisnovaes.moviebattle.entities.Usuario;

public class ConstantesUtils {

    private ConstantesUtils() { 
    	throw new IllegalStateException("Classe utilitária, não deve ser instanciada"); 
    	}

    public static final String getMensagemRodada(Usuario usuario) {
        return  usuario.getNome().concat(" deve escolher entre os dois filmes, qual deles você acha que tem a maior pontuação? Filme 1 ou Filme 2?");
    }

    public static final String verificaAcerto(boolean acertou) {
        if (acertou) {
            return "Parabéns, você acertou!";
        }
        return "Desculpe, você errou!";
    }

    public static final String mensagemDeDespedida(long score) {
        return "Obrigado por jogar! Seu score final foi: ".concat(String.valueOf(score));
    }

    public static final String mensagemDeBoasVindas(String nome) {
        return "Olá " + nome + "! Jogo iniciado, boa sorte!";
    }

}
