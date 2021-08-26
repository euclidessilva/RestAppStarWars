package com.starwars.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.starwars.entity.StarWars;
import com.starwars.service.StarWarsService;
@RestController

public class StarWarsController {
	 	@Autowired
	    private StarWarsService service; 

	 	@GetMapping("/search")
	    public Page<StarWars> search(
	            @RequestParam("nome") String nome, @RequestParam(value = "page", required = false, defaultValue = "0") int page,
	            @RequestParam(value = "size", required = false, defaultValue = "10") int size) {
	        return service.search(nome, page, size);

	    }

	    @GetMapping
	    public Page<StarWars> getNameAll() {
	        return service.findNameAll();
	    }
	 	
	 	
	   @GetMapping(path = {"/all"}, produces=MediaType.APPLICATION_JSON_VALUE)
	   public List findAll(){
	     return service.findAll();
	   }

	   @GetMapping(path = {"starwars/{id}"}, produces=MediaType.APPLICATION_JSON_VALUE)
	   public ResponseEntity findById(@PathVariable long id){
	      return service.findById(id);
	   }
	   
	   @PostMapping("add")
	   public StarWars create(@RequestBody StarWars starwars){
		   return service.save(starwars);
	   }
	   
	   @PutMapping(value="update/{id}")
	   public ResponseEntity update(@PathVariable("id") long id, @RequestBody StarWars starwars) {
		   return service.update(id, starwars);
	   }
	   @DeleteMapping(path ={"delete/{id}"})
	   public ResponseEntity<?> delete(@PathVariable long id) {
		  return service.delete(id);
	   }
}
