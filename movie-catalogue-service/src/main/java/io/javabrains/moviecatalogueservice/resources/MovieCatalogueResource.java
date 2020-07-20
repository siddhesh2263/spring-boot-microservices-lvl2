package io.javabrains.moviecatalogueservice.resources;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import io.javabrains.moviecatalogueservice.models.CatalogueItem;
import io.javabrains.moviecatalogueservice.models.Movie;
import io.javabrains.moviecatalogueservice.models.UserRating;

@RestController
@RequestMapping("/catalogue")
public class MovieCatalogueResource {
	
	@Autowired
	private RestTemplate restTemplate;
	
	/*
	 * @Autowired private WebClient.Builder webClientBuilder;
	 */
	
	@RequestMapping("/{userId}")
	public List<CatalogueItem> getCatalogue(@PathVariable("userId") String userId) {
		
		UserRating ratings = restTemplate.getForObject("http://ratings-data-service/ratingsdata/users/" + userId, UserRating.class);
		
		return ratings.getUserRating().stream().map(rating -> {
			 Movie movie = restTemplate.getForObject("http://movie-info-service/movies/" + rating.getMovieId(), Movie.class);
			return new CatalogueItem(movie.getName(), "movie description", rating.getRating());
		})
			.collect(Collectors.toList());
		
	}

}
