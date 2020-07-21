package io.javabrains.moviecatalogueservice.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

import io.javabrains.moviecatalogueservice.models.CatalogueItem;
import io.javabrains.moviecatalogueservice.models.Movie;
import io.javabrains.moviecatalogueservice.models.Rating;

@Service
public class MovieInfo {
	
	@Autowired
	private RestTemplate restTemplate;
	
	@HystrixCommand(fallbackMethod = "getFallbackCatalogueItem")
	public CatalogueItem getCatalogueItem(Rating rating) {
		Movie movie = restTemplate.getForObject("http://movie-info-service/movies/" + rating.getMovieId(), Movie.class);
		return new CatalogueItem(movie.getName(), movie.getDescription(), rating.getRating());
	}
	
	public CatalogueItem getFallbackCatalogueItem(Rating rating) {
		return new CatalogueItem("Movie not found", "", 0);
	}

}
