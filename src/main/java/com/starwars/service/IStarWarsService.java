package com.starwars.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.starwars.Entity.StarWars;

public interface IStarWarsService {
	public List findAll();

	StarWars save(StarWars starwars);

	public ResponseEntity findById(long id);

	public ResponseEntity update(long id, StarWars starwars);

	public ResponseEntity<?> delete(long id);

}
