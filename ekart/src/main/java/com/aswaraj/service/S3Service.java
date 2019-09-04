package com.aswaraj.service;

import java.io.ByteArrayOutputStream;

public interface S3Service {
	public ByteArrayOutputStream downloadFile(String keyName);
}
