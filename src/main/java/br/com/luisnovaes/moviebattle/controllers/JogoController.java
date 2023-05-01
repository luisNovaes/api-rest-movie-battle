package br.com.luisnovaes.moviebattle.controllers;

import br.com.luisnovaes.moviebattle.controllers.mappers.RankingMapper;
import br.com.luisnovaes.moviebattle.controllers.mappers.RodadaMapper;
import br.com.luisnovaes.moviebattle.openapi.api.JogoApi;
import br.com.luisnovaes.moviebattle.openapi.model.Mensagem;
import br.com.luisnovaes.moviebattle.openapi.model.PosicaoRanking;
import br.com.luisnovaes.moviebattle.openapi.model.Rodada;
import br.com.luisnovaes.moviebattle.repository.RodadaRepository;
import br.com.luisnovaes.moviebattle.service.JogoService;
import br.com.luisnovaes.moviebattle.service.RodadasService;
import br.com.luisnovaes.moviebattle.service.UsuarioService;
import br.com.luisnovaes.moviebattle.utils.ConstantesUtils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin(origins = "http://localhost:8080", maxAge = 3600)
@RestController
public class JogoController implements JogoApi {

    private final RodadasService rodadasService;
    private final UsuarioService usuarioService;
    private final JogoService jogoService;
    private final RodadaMapper rodadaMapper;
    private final RankingMapper rankingMapper;

    @Autowired
    private RodadaRepository movieRepository;

    public JogoController(RodadasService rodadasService, 
    				UsuarioService usuarioService, JogoService jogoService) {
    	
        this.rodadasService = rodadasService;
        this.usuarioService = usuarioService;
        this.jogoService = jogoService;
        this.rodadaMapper = RodadaMapper.INSTANCE;
        this.rankingMapper = RankingMapper.INSTANCE;
    }
    
    @Override
    public ResponseEntity<Mensagem> iniciarJogo() {
        var usernameLogado = usuarioService.getUsernameUsuarioLogado();
        jogoService.inicializarJogo();
        var response = new Mensagem();
        response.setMsg(ConstantesUtils.mensagemDeBoasVindas(
        				usuarioService.getUsuario(usernameLogado).getNome()));
        
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }


    @Override
    public ResponseEntity<Rodada> iniciarNovaRodada() {
        return ResponseEntity.status(HttpStatus.OK).body(rodadaMapper.map(rodadasService.inicializarRodada(
        									usuarioService.getUsuario(usuarioService.getUsernameUsuarioLogado()))));
    }

    @Override
    public ResponseEntity<Mensagem> escolherOpcao(@RequestParam("opcaoFilme") Integer opcaoFilme) {
        var resultadoJogada = rodadasService.jogar(opcaoFilme);
        var response = new Mensagem();
        response.setMsg(resultadoJogada);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @Override
    public ResponseEntity<Mensagem> finalizar() {
        var resultadoFinal = jogoService.finalizarJogo(jogoService.findJogoByUsuario());
        var response = new Mensagem();
        response.setMsg(resultadoFinal);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @Override
    public ResponseEntity<List<PosicaoRanking>> getRanking() {
        var ranking = rankingMapper.map(usuarioService.getRanking());
        return ResponseEntity.status(HttpStatus.OK).body(ranking);
    }
}
