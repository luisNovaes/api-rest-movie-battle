package br.com.luisnovaes.moviebattle.service.impl;

import br.com.luisnovaes.moviebattle.entities.Movie;
import br.com.luisnovaes.moviebattle.entities.exceptions.WebScrappingException;
import br.com.luisnovaes.moviebattle.repository.MovieRepository;
import br.com.luisnovaes.moviebattle.service.WebScrappingService;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class ImdbWebScrapping implements WebScrappingService {

    @Override
    public List<Movie> getMoviesFromWebScrapping() {
        try {
            var document = Jsoup.connect("https://www.imdb.com/search/title/"
            		+ "?title_type=movie&genres=action&sort=user_rating,"
            		+ "desc&explore=title_type,genres").get();

            var titulos = document.getElementsByClass("lister-item-header")
                    .stream()
                    .map(e -> (Element) e.childNode(3))
                    .map(Element::text)
                    .collect(Collectors.toList());

            var rating = document.getElementsByClass("inline-block ratings-imdb-rating")
                    .stream()
                    .map(e -> e.childNode(3).childNode(0))
                    .map(e -> Double.parseDouble(e.outerHtml()))
                    .collect(Collectors.toList());

            var plot = document.getElementsByClass("lister-item-content")
                    .stream()
                    .map(e -> e.childNode(7).childNode(0))
                    .map(Node::outerHtml)
                    .collect(Collectors.toList());

            return instanciarFilmes(titulos, rating, plot);
        } catch (IOException e) {
            throw new WebScrappingException("Não foi possível recuperar a lista de filmes");
        }
    }

    @Bean
    public CommandLineRunner movieData(MovieRepository repository) {
        var movies =  getMoviesFromWebScrapping();
        return args -> repository.saveAll(movies);
    }

    private List<Movie> instanciarFilmes(List<String> titles, List<Double> ratings, List<String> plot) {
        List<Movie> listFilmes = new ArrayList<>();
        for (int i = 0; i < titles.size(); i++) {
            listFilmes.add(new Movie(titles.get(i), ratings.get(i), plot.get(i)));
        }
        return listFilmes;
    }

}
