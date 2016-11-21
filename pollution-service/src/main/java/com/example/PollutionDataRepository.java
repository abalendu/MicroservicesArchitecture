package com.example;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
interface PollutionDataRepository extends JpaRepository<Pollution, Long> {

	/*@RestResource(path = "by-city")
	Collection<Pollution> findByCity(@Param("rn") String rn);*/
}