package com.aswaraj.controller;

import java.io.ByteArrayOutputStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.aswaraj.service.S3Service;

@RestController
public class DownloadFileController {

	@Autowired
	S3Service s3Service;
	
	@GetMapping("/api/image/{imageType}/{imageId}")
	public ResponseEntity<byte[]> downloadFile(@PathVariable("imageType") String imageType,@PathVariable("imageId") String imageId){
		String keyName = imageType +"_"+ imageId + ".jpg";

		ByteArrayOutputStream outputStream = s3Service.downloadFile(keyName);
		
		return ResponseEntity.ok()
				.contentType(MediaType.IMAGE_JPEG)
				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\""+keyName+"\"")
				.body(outputStream.toByteArray());
	}
}
