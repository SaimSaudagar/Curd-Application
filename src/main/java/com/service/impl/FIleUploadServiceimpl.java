package com.service.impl;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import com.model.UploadFile;
import com.repository.FileUploadRepository;
import com.service.FileUploadService;

@Service
public class FIleUploadServiceimpl implements FileUploadService{

	@Autowired
	private FileUploadRepository fileUploadRepository;
	
	private String uploadFolderPath = "C:\\Users\\Lenovo\\Desktop\\Uploaded\\check";
	
	@Override
	public void uploadFile(MultipartFile file, UploadFile uploadFile) {
		try {
			byte[] data = file.getBytes();
			Path path = Paths.get(uploadFolderPath + file.getOriginalFilename());
			Files.write(path, data);
		} catch (IOException e) {
			e.printStackTrace();
		}	
		
		UploadFile uploadFile2 = new UploadFile();
		try {
			uploadFile2.setFile(file.getBytes());
			uploadFile2.setFileType(file.getContentType());
			uploadFile2.setFileName(file.getOriginalFilename());
			uploadFile2.setFirstName(uploadFile.getFirstName());
			uploadFile2.setLastName(uploadFile.getLastName());
			uploadFile2.setEmail(uploadFile.getEmail());
			uploadFile2.setAge(uploadFile.getAge());
			uploadFile2.setUsername(uploadFile.getUsername());
			uploadFile2.setPassword(uploadFile.getPassword());
			uploadFile2.setPhoneNum(uploadFile.getPhoneNum());
			
			fileUploadRepository.save(uploadFile2);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public UploadFile findByID(Long ID) {
		
		if(fileUploadRepository.existsById(ID)) {
			return fileUploadRepository.findById(ID).get();
		}
		
		return null;
	}

	@Override
	public UploadFile delete(Long ID) {
		UploadFile uploadFile = fileUploadRepository.findById(ID).get();
		fileUploadRepository.delete(uploadFile);
		return uploadFile;
	}
}
