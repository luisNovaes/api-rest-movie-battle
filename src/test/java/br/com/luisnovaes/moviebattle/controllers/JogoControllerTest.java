package br.com.luisnovaes.moviebattle.controllers;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.httpBasic;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
class JogoControllerTest {

    final String URI = "/jogo";
    final String USERNAME = "luisnovaes";
    final String PASSWORD = "123";

    @Autowired
    MockMvc mockMvc;

    @Test
    @Order(1)
    void deveSucessoAoIniciarUmaPartida() throws Exception {
        mockMvc
          .perform(post(URI.concat("/iniciar"))
                .with(httpBasic(USERNAME, PASSWORD)))
            .andExpect(status()
            .isCreated());
    }

    @Test
    @Order(2)
    void deveSucessoAoIniciarUmaNovaRodada() throws Exception {
        mockMvc
           .perform(get(URI.concat("/iniciar-rodada"))
                .with(httpBasic(USERNAME, PASSWORD)))
              .andExpect(status()
              .isOk());
    }

    @Test
    @Order(3)
    void deveSucessoAEscolherUmaOpcaoDeFilme() throws Exception {
        mockMvc
            .perform(get(URI.concat("/escolher-opcao"))
                .with(httpBasic(USERNAME, PASSWORD))
                .queryParam("opcaoFilme", "1"))
              .andExpect(status()
              .isOk());
    }

    @Test
    @Order(4)
    void deveSucessoAoEncerrarUmJogo() throws Exception {
        mockMvc
                .perform(get(URI.concat("/encerrar"))
                        .with(httpBasic(USERNAME, PASSWORD)))
                .andExpect(status()
                .isOk());
    }

    @Test
    @Order(5)
    void deveSucessoAoBuscarRankingJogadores() throws Exception {
        mockMvc
                .perform(get(URI.concat("/ranking"))
                        .with(httpBasic(USERNAME, PASSWORD)))
                .andExpect(status()
                        .isOk());
    }

}