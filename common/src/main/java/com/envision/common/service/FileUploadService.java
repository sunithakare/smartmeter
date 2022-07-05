package com.envision.common.service;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.envision.common.database.dao.FileUploadDAO;
import com.envision.common.database.objects.FileUploadTable;
import com.envision.common.modal.FileDetails;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class FileUploadService {

	@Autowired
	FileUploadDAO fileUploadDAO;

	@Value("${fileUplaodPath}")
	String fileUplaodPath;
	
	@Transactional
	public FileDetails saveFile(byte[] fileByte,String fileName) throws IOException {

		FileUploadTable fileUploadTable=new FileUploadTable();
		fileUploadTable.setFilename(fileName);
		fileUploadDAO.save(fileUploadTable);
		
		String filePath=fileUplaodPath+fileUploadTable.getId();
		
		File uploadedFile=new File(filePath);

		uploadedFile.mkdirs();
	
		filePath=filePath+"/"+fileName;
		uploadedFile=new File(filePath);
		
		Files.write(uploadedFile.toPath(), fileByte, StandardOpenOption.CREATE_NEW);
//		
//		 BufferedOutputStream stream =
//                 new BufferedOutputStream(new FileOutputStream(uploadedFile);
//         stream.write(fileByte);
//         stream.close();
		
		
		fileUploadTable.setFilepath(filePath);

		fileUploadTable=fileUploadDAO.saveAndFlush(fileUploadTable);
		
		return new FileDetails(fileUploadTable.getId(), fileUploadTable.getFilename());
		
	}
	
	
	public FileUploadTable findFile(Long id) throws FileNotFoundException {
		// TODO Auto-generated method stub
		Optional<FileUploadTable> file = fileUploadDAO.findById(id);
		if (file.isPresent()) {
			return file.get();
		}else {
			throw new FileNotFoundException("File Not Found");
		}
	}
}
