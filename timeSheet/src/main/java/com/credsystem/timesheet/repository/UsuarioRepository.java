package com.credsystem.timesheet.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.credsystem.timesheet.entity.UsuarioEntity;

public interface UsuarioRepository extends MongoRepository<UsuarioEntity, String> {

	@Query("{ 'usuario' : ?0}")
	UsuarioEntity find(String usuario);
	
	

}
