package com.envision.useraccessmanagement;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.util.UUID;

import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

@Component
public class UseraccessmanagementUtilities {

	 final DefaultResourceLoader loader = new DefaultResourceLoader();
	
	public String getFormattedRegistrationEmail(UUID uuid,String refId) throws Exception {
		

		  Resource resource = loader.getResource("classpath:UAMRegEmail.html");
		    File myFile = resource.getFile();
//		  InputStream stream = resource.getInputStream();
		  String content=new String(Files.readAllBytes(myFile.toPath()));
		  
		  content=content.replace("${{UUID_REF}}", uuid.toString());
		  content=content.replace("${{RefId}}", refId);
		  return content;
	}
}