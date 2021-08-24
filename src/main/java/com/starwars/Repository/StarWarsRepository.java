package com.starwars.Repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.starwars.Entity.StarWars;

@Repository

public interface StarWarsRepository extends JpaRepository <StarWars, Long> {
	
	@Query(value = "select * FROM star.tbl_starwars c WHERE LOWER(c.nome) like %:searchTerm% ", nativeQuery = true)
	    Page<StarWars> search(@Param("searchTerm") String searchTerm, Pageable pageable);

}
