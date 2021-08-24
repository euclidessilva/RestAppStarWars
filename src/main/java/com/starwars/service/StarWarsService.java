package com.starwars.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Sort;

import com.starwars.Entity.StarWars;
import com.starwars.Repository.StarWarsRepository;
@Service
public class StarWarsService implements IStarWarsService{
	
	@Autowired
    private StarWarsRepository repository;
	public Page<StarWars> search(String searchTerm, int page, int size) {
		PageRequest pageRequest = PageRequest.of( page, size, Sort.Direction.ASC, "nome");
        return repository.search(searchTerm.toLowerCase(), pageRequest);
    }

    public Page<StarWars> findNameAll() {
        int page = 0;
        int size = 10;
        PageRequest pageRequest = PageRequest.of(
                page,
                size,
                Sort.Direction.ASC,
                "name");
        return new PageImpl<>(
                repository.findAll(), 
                pageRequest, size);
    }
	@Override
	public StarWars save(StarWars starwars) {
		return repository.save(starwars);
	}

	@Override
	public ResponseEntity findById(long id) {
		return repository.findById(id)
	              .map(record -> ResponseEntity.ok().body(record))
	              .orElse(ResponseEntity.notFound().build());
	}

	@Override
	public ResponseEntity update(long id, StarWars starwars) {
		return repository.findById(id)
	              .map(record -> {
	                  record.setNome(starwars.getNome());
	                  record.setClima(starwars.getClima());
	                  record.setTerreno(starwars.getTerreno());
	                  StarWars updated = repository.save(record);
	                  return ResponseEntity.ok().body(updated);
	              }).orElse(ResponseEntity.notFound().build());
	}

	@Override
	public ResponseEntity<?> delete(long id) {
		return repository.findById(id)
	              .map(record -> {
	                  repository.deleteById(id);
	                  return ResponseEntity.ok().build();
	              }).orElse(ResponseEntity.notFound().build());
	}

	@Override
	public List findAll() {
		 List<StarWars> starwars=(List<StarWars>) repository.findAll(); 
			return starwars;
	}

}
