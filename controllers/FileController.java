package file.server.controllers;

import file.server.models.FileHolder;
import file.server.services.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
public class FileController {

	@Autowired
	private file.server.services.FileService FileService;

	@PostMapping("/uploadFile")
	public Long uploadFile(@RequestParam("file") MultipartFile file, @RequestParam Long employeeId)  {
		return FileService.storeFile(file,employeeId);
	}

	@GetMapping("/downloadFile/{fileId}")
	public ResponseEntity<byte[]> downloadFile(@PathVariable Long fileId) throws IOException {
		// Load file from database
		FileHolder file = FileService.getFile(fileId);

		return ResponseEntity.ok()
				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\""
						+ file.getFileName())
				.body(file.getBytes());
	}
}
