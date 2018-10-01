package com.service.dao.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.service.dao.model.ClientEntity;

@Repository
public interface ClientRepository extends CrudRepository<ClientEntity, Long> {
	
	Optional<ClientEntity> findByName(String name);

}
