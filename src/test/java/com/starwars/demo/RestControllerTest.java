package com.starwars.demo;
import static org.junit.Assert.assertEquals;

import java.io.IOException;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.entity.ContentType;
import org.apache.http.impl.client.HttpClientBuilder;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import com.starwars.controller.StarWarsController;
import com.starwars.entity.StarWars;

//import io.restassured.RestAssured.*;
//import io.restassured.matcher.RestAssuredMatchers.*;
import org.hamcrest.Matchers.*;


@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class RestControllerTest {

    @Autowired
    private StarWarsController controller;

    @Autowired
    private RestTemplate restTemplate;
    
    @Value("http://localhost:${local.server.port}/${server.servlet.context-path}/")
    private String appPath;

    private StarWars c1, c2, c3;

    @Before
    public void setUp() {

        this.c1 = new StarWars(1L, "Tatooine", "arid", "desert", 2);
        this.c1 = new StarWars(2L, "Alderaan", "temperate", "grasslands, mountains", 5);
        this.c1 = new StarWars(3L, "Yavin IV", "temperate, tropical", "jungle, rainforests", 7);
    }
    @Test
	public void contextLoads() throws Exception {
		//assertThat(controller).isNotNull();
	}
    @Test
    public void testApi() throws ClientProtocolException, IOException {
    	   String jsonMimeType = "application/json";
    	   HttpUriRequest request = new HttpGet( "http://localhost:8080/all" );

    	   HttpResponse response = HttpClientBuilder.create().build().execute( request );

    	   String mimeType = ContentType.getOrDefault(response.getEntity()).getMimeType();
    	   assertEquals( jsonMimeType, mimeType );
    }
    
    @Test public void returns200() {

       // when().
        //        get("/all/{id}", 5).
       // then().
        //        statusCode(200).
        //        body("qtdFilmes", equalTo(5);
        //             ;

    }
    
    
 /*
    @Test
    public void allCitiesTest() {

        var paramType = new ParameterizedTypeReference<List<StarWars>>() { };
        var starwars = restTemplate.exchange(appPath, HttpMethod.GET, null, paramType);

        assertThat(starwars.getBody()).hasSize(8);
        assertThat(starwars.getBody()).contains(this.c1, this.c2, this.c3);
    }

    @Test
    public void oneCity() {

         var city = this.restTemplate.getForObject(appPath + "/1/", City.class);
         assertThat(city).extracting("name", "population").containsExactly("Bratislava", 432000);
    }
    */
}