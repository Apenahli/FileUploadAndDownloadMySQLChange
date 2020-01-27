package com.example.service;

import java.io.IOException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import com.example.model.DBFile;
import com.example.repository.DBFileRepository;

@Service
public class DBFileStorageService {

	@Autowired
	private DBFileRepository dbFileRepository;

	public DBFile storeFile(MultipartFile file) {

		String fileName = StringUtils.cleanPath(file.getOriginalFilename());

		try {
			DBFile dbFile = new DBFile(fileName, file.getContentType(), file.getBytes());

			return dbFileRepository.save(dbFile);

		} catch (IOException ex) {
			return null;
		}

	}

	public DBFile getFile(String fileId) {
		return dbFileRepository.findById(fileId).get();
	}

	public List<DBFile> findAll() {

		return (List<DBFile>) dbFileRepository.findAll();
	}
}
