package br.com.luisnovaes.moviebattle.service.impl;

import br.com.luisnovaes.moviebattle.entities.Jogo;
import br.com.luisnovaes.moviebattle.entities.Usuario;
import br.com.luisnovaes.moviebattle.entities.exceptions.BusinessException;
import br.com.luisnovaes.moviebattle.repository.JogoRepository;
import br.com.luisnovaes.moviebattle.service.JogoService;
import br.com.luisnovaes.moviebattle.service.impl.JogoServiceImpl;
import br.com.luisnovaes.moviebattle.service.impl.UsuarioServiceImpl;
import br.com.luisnovaes.moviebattle.utils.ConstantesUtils;
import br.com.luisnovaes.moviebattle.utils.TestUtils;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class JogoServiceImplTest {

    final String NOME_USUARIO = TestUtils.NOME_USUARIO;;

    @Mock
    JogoRepository jogoRepository;

    @Mock
    UsuarioServiceImpl usuarioService;

    JogoService jogoService;

    Usuario user;
    Jogo jogo;

    @BeforeEach
    void setUp() {
        jogoService = new JogoServiceImpl(jogoRepository, usuarioService);
        user = TestUtils.getUsuarioMock(NOME_USUARIO);
        jogo = TestUtils.getJogoMock(user);
    }

    @Test
    void deveSucessoAoIniciarUmNovoJogo() {
        Jogo jogo = new Jogo(user);
        jogo.setFinalizado(true);
        user.setJogos(Collections.singletonList(jogo));
        Mockito.when(usuarioService.getUsuario(Mockito.anyString())).thenReturn(user);
        Mockito.when(usuarioService.getUsernameUsuarioLogado()).thenReturn(NOME_USUARIO);
        jogoService.inicializarJogo();
        Mockito.verify(jogoRepository).save(Mockito.any(Jogo.class));
    }

    @Test
    void deveLancarExcecaoQuandoIniciarUmNovoJogoComOutroEmAndamento() {
        Mockito.when(usuarioService.getUsuario(Mockito.anyString())).thenReturn(user);
        Mockito.when(usuarioService.getUsernameUsuarioLogado()).thenReturn(NOME_USUARIO);

        assertThrows(BusinessException.class, () -> jogoService.inicializarJogo());
    }

    @Test
    void deveSucessoAoFinalizarUmJogo() {
        Mockito.when(usuarioService.getUsernameUsuarioLogado()).thenReturn(NOME_USUARIO);

        Mockito.when(jogoRepository.findByUsuarioUsernameAndIsFinalizadoFalse(Mockito.anyString())).thenReturn(Optional.of(jogo));

        var resultado = jogoService.finalizarJogo(jogoService.findJogoByUsuario());

        assertNotNull(resultado);
        assertEquals(ConstantesUtils.mensagemDeDespedida(jogo.getScoreFinal()), resultado);
    }

    @Test
    void deveSucessoAoAcharUmJogoAtivoPorUsuario() {
        Mockito.when(usuarioService.getUsernameUsuarioLogado()).thenReturn(NOME_USUARIO);
        Mockito.when(jogoRepository.findByUsuarioUsernameAndIsFinalizadoFalse(Mockito.anyString())).thenReturn(Optional.of(jogo));

        var jogo = jogoService.findJogoByUsuario();

        assertNotNull(jogo);
        assertEquals(NOME_USUARIO, jogo.getUsuario().getUsername());
    }




}