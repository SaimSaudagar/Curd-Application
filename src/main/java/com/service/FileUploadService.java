package com.service;

import org.springframework.web.multipart.MultipartFile;
import com.model.UploadFile;

public interface FileUploadService {
	public void uploadFile(MultipartFile file, UploadFile uploadFile);
	public UploadFile findByID(Long ID);
	public UploadFile delete(Long ID);
}
