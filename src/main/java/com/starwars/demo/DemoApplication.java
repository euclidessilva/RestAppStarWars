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
	
	public void saveStarwars() {
	    RestTemplate restTemplate = new RestTemplate();
	    Response response = restTemplate.getForObject("https://swapi.dev/api/planets/1", Response.class);
		StarWars s1 = new StarWars(null, "Tatooine", "arid", "desert", response.getFilms().size());
	    HttpHeaders headers = new HttpHeaders();
	    headers.add("Accept", MediaType.APPLICATION_JSON_VALUE);
	    headers.setContentType(MediaType.APPLICATION_JSON);
	    HttpEntity < StarWars > requestBody = new HttpEntity < > (s1, headers);
	    restTemplate.postForObject("http://localhost:8080/add", requestBody, StarWars.class);
	  }
	
	
	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
			saveStarwars();
	}
	

}