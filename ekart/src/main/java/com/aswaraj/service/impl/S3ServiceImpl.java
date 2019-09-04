package com.aswaraj.service.impl;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.amazonaws.AmazonClientException;
import com.amazonaws.AmazonServiceException;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.S3Object;
import com.aswaraj.service.S3Service;

@Service
public class S3ServiceImpl implements S3Service {

	private Logger logger = LoggerFactory.getLogger(S3ServiceImpl.class);

	@Autowired
	private AmazonS3 s3Client;

	@Value("${aws_s3_bucket}")
	private String bucketName;

	@Override
	public ByteArrayOutputStream downloadFile(String keyName) {
		try {
			S3Object s3Object = s3Client.getObject(bucketName, keyName);
			InputStream is = s3Object.getObjectContent();
			ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
			int len;
			byte[] buffer = new byte[4096];

			while ((len = is.read(buffer, 0, buffer.length)) != -1) {
				outputStream.write(buffer, 0, len);
			}

			return outputStream;
		} catch (IOException e) {
			logger.error("IOException: " + e.getMessage());
		} catch (AmazonServiceException ase) {
			logger.info("sCaught an AmazonServiceException from GET requests, rejected reasons:");
			logger.info("Error Message:    " + ase.getMessage());
			logger.info("HTTP Status Code: " + ase.getStatusCode());
			logger.info("AWS Error Code:   " + ase.getErrorCode());
			logger.info("Error Type:       " + ase.getErrorType());
			logger.info("Request ID:       " + ase.getRequestId());
			throw ase;
		} catch (AmazonClientException ace) {
			logger.info("Caught an AmazonClientException: ");
			logger.info("Error Message: " + ace.getMessage());
			throw ace;
		}

		return null;
	}

}
