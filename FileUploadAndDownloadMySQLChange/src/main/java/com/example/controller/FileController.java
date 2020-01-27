package com.example.controller;

import java.util.Arrays;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;
import com.example.model.DBFile;
import com.example.service.DBFileStorageService;

@Controller
public class FileController {

	@Autowired
	private DBFileStorageService dBFileStorageService;

	@GetMapping("/")
	public ModelAndView test() {

		ModelAndView model = new ModelAndView("test/index");
		
		model.addObject("employees", dBFileStorageService.findAll());

		return model;
	}

	@PostMapping("/uploadFile")
	public RedirectView uploadFile(@RequestParam("file") MultipartFile file) {

		dBFileStorageService.storeFile(file);

		RedirectView redirectView = new RedirectView("/");

		return redirectView;
	}
	
	@PostMapping("/uploadMultipleFiles")
	public RedirectView uploadMultipleFiles(@RequestParam("files") MultipartFile[] files) {
		RedirectView redirectView = new RedirectView("/");
		
		 Arrays.asList(files).stream().map(file -> uploadFile(file)).collect(Collectors.toList());
		

		return redirectView;
	}

	@GetMapping("/downloadFile/{fileId}")
	public ResponseEntity<Resource> downloadFile(@PathVariable String fileId) {
		// Load file from database
		DBFile dbFile = dBFileStorageService.getFile(fileId);

		return ResponseEntity.ok().contentType(MediaType.parseMediaType(dbFile.getFileType()))
				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + dbFile.getFileName() + "\"")
				.body(new ByteArrayResource(dbFile.getData()));
	}

}
