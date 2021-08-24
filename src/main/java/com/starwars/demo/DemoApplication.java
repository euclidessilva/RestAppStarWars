package com.starwars.demo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;

import com.starwars.Entity.Response;
import com.starwars.Entity.StarWars;

@SpringBootApplication
public class DemoApplication implements CommandLineRunner {
	
	private static final Logger log = LoggerFactory.getLogger(DemoApplication.class);
	 
	@Bean public RestTemplate getRestTemplate() {
		    return new RestTemplate();
	}
	
	private void addRest(StarWars s) {
	    RestTemplate restTemplate = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
	    headers.add("Accept", MediaType.APPLICATION_JSON_VALUE);
	    headers.setContentType(MediaType.APPLICATION_JSON);
	    HttpEntity < StarWars > requestBody = new HttpEntity < > (s, headers);
	    restTemplate.postForObject("http://localhost:8080/add", requestBody, StarWars.class);
	}

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		RestTemplate restTemplate = new RestTemplate();
	    Response response = restTemplate.getForObject("https://swapi.dev/api/planets/1", Response.class);
		StarWars s1 = new StarWars(null, "Tatooine", "arid", "desert", response.getFilms().size());
	    Response response2 = restTemplate.getForObject("https://swapi.dev/api/planets/2", Response.class);
		StarWars s2 = new StarWars(null, "Alderaan", "temperate", "grasslands, mountains", response2.getFilms().size());
	    Response response3 = restTemplate.getForObject("https://swapi.dev/api/planets/3", Response.class);
		StarWars s3 = new StarWars(null, "Yavin IV", "temperate, tropical", "jungle, rainforests", response3.getFilms().size());
		addRest(s1);
		addRest(s2);
		addRest(s3);
	}
}