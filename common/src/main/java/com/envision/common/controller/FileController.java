package com.envision.common.controller;

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;

import javax.servlet.http.HttpServletResponse;

import org.apache.tika.Tika;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.envision.common.database.objects.FileUploadTable;
import com.envision.common.modal.FileDetails;
import com.envision.common.service.FileUploadService;

@RestController
@RequestMapping("file")
public class FileController {

	@Autowired
	FileUploadService fileUploadService;


	@PutMapping(path="upload", consumes = { "multipart/form-data" })
	@ResponseBody
	public FileDetails uploadNewFile(@RequestPart("uploadFile") MultipartFile file) throws Exception {
	
		return fileUploadService.saveFile(file.getBytes(), file.getOriginalFilename());
		
	}
	
	@GetMapping("download")
	public ResponseEntity<Resource> download(
			@RequestParam(value="docId",required = false,defaultValue = "") Long docId, HttpServletResponse response) throws FileNotFoundException {

		try {

			FileUploadTable fileData = fileUploadService.findFile(docId);
			
			byte[] fileByteData=Files.readAllBytes(new File(fileData.getFilepath()).toPath());
			BufferedInputStream bis = new BufferedInputStream(new ByteArrayInputStream(fileByteData));
			
		  response.setHeader("Content-Disposition", "attachment; filename=\"" + fileData.getFilename() + "\"");
			Tika tika=new Tika();  
			ByteArrayResource resource = new ByteArrayResource(fileByteData);
			return ResponseEntity.ok().contentType(MediaType.valueOf(tika.detect(bis)))
					.body(resource);
		} catch (FileNotFoundException e) {
			throw e;
		} catch (Exception e) {
			throw new FileNotFoundException("File Not Found");
		}
	}

}
