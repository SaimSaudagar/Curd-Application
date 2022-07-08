package com.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import com.model.UploadFile;
import com.response.ResponseSuscessStatus;
import com.service.FileUploadService;

@RestController
@RequestMapping("/api/v1")
public class FileUploadController {

	@Autowired
	private FileUploadService fileUpload;

	@PostMapping("/upload")
	public ResponseSuscessStatus uploadDB(@RequestParam("file") MultipartFile multipartFile,
			@RequestParam String firstName, String lastName, String userName, String passowrd, String email, int age,
			String phoneNum) {
		UploadFile uploadFile = new UploadFile();
		uploadFile.setFirstName(firstName);
		uploadFile.setLastName(lastName);
		uploadFile.setUsername(userName);
		uploadFile.setPassword(passowrd);
		uploadFile.setAge(age);
		uploadFile.setEmail(email);
		uploadFile.setPhoneNum(phoneNum);

		fileUpload.uploadFile(multipartFile, uploadFile);

		return new ResponseSuscessStatus(0, "User Added Successfully", uploadFile.toString());
	}

	@GetMapping("/download/{ID}")
	public ResponseEntity<Resource> downloadFile(@PathVariable Long ID) {
		UploadFile file = fileUpload.findByID(ID);

		if (file != null) {
			return ResponseEntity.ok().contentType(MediaType.parseMediaType(file.getFileType()))
					.header(HttpHeaders.CONTENT_DISPOSITION, "Attactchment; File Name = " + file.getFileName())
					.body(new ByteArrayResource(file.getFile()));
		}

		return ResponseEntity.ok().contentType(null).header(HttpHeaders.CONTENT_DISPOSITION, "User Not Found")
				.body(null);
	}

	@DeleteMapping("/delete/{ID}")
	public ResponseSuscessStatus delete(@PathVariable Long ID) {
		UploadFile file = fileUpload.findByID(ID);

		if (file != null) {
			fileUpload.delete(ID);
			return new ResponseSuscessStatus(0, "Deleted Successfully", file.toString());
		}

		return new ResponseSuscessStatus(0, "User Not Found", null);
	}
}
