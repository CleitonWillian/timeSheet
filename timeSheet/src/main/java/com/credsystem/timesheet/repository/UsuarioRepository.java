package com.credsystem.timesheet.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.credsystem.timesheet.entity.Usuario;

public interface UsuarioRepository extends MongoRepository<Usuario, String> {

	@Query("{ 'usuario' : ?0}")
	Usuario find(String usuario);
	
	

}
