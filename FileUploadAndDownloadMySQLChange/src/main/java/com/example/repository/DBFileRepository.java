package com.example.repository;

import org.springframework.data.repository.CrudRepository;
import com.example.model.DBFile;

public interface DBFileRepository extends CrudRepository<DBFile, String>{
	
	

}
