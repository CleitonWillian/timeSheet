package com.credsystem.timesheet.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.credsystem.timesheet.entity.TimeSheetEntity;

public interface TimeSheetRepository extends MongoRepository<TimeSheetEntity, String> {

	@Query("{usuario: ?0, ano: ?1, mes :?2 }")
	TimeSheetEntity find(String id,int ano, int mes);

	@Query("{usuario: ?0 }")
	List<TimeSheetEntity> find(String id);
	
	
}
