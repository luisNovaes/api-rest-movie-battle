package br.com.luisnovaes.moviebattle.entities.exceptions;

public class JogoFinalizadoException extends RuntimeException {

    public JogoFinalizadoException(String msg, Long scoreFinal) {
        super(msg + " " + scoreFinal);

    }
}