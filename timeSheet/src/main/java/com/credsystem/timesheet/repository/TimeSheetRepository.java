package com.credsystem.timesheet.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.credsystem.timesheet.entity.TimeSheet;

public interface TimeSheetRepository extends MongoRepository<TimeSheet, String> {

	@Query("{usuario: ?0, ano: ?1, mes :?2 }")
	TimeSheet find(String id,int ano, int mes);

	@Query("{usuario: ?0 }")
	List<TimeSheet> find(String id);
	
	
}
