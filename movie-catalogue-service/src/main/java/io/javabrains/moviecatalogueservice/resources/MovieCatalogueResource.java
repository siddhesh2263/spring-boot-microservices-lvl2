package io.javabrains.moviecatalogueservice.resources;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

import io.javabrains.moviecatalogueservice.models.CatalogueItem;
import io.javabrains.moviecatalogueservice.models.Movie;
import io.javabrains.moviecatalogueservice.models.Rating;
import io.javabrains.moviecatalogueservice.models.UserRating;
import io.javabrains.moviecatalogueservice.services.MovieInfo;
import io.javabrains.moviecatalogueservice.services.UserRatingInfo;

@RestController
@RequestMapping("/catalogue")
public class MovieCatalogueResource {
	
	@Autowired
	private RestTemplate restTemplate;
	
	/*
	 * @Autowired private WebClient.Builder webClientBuilder;
	 */
	
	@Autowired
	MovieInfo movieInfo;
	
	@Autowired
	UserRatingInfo userRatingInfo;
	
	@RequestMapping("/{userId}")
	public List<CatalogueItem> getCatalogue(@PathVariable("userId") String userId) {
		
		UserRating ratings = userRatingInfo.getUserRating(userId);
		
		return ratings.getUserRating().stream().map(rating -> {
			 return movieInfo.getCatalogueItem(rating);
		})
			.collect(Collectors.toList());
		
	}
}
