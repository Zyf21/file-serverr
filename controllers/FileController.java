package file.server.controllers;

import file.server.models.File;
import file.server.models.FileHolder;
import file.server.services.implement.FileService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/api/files")
@Log4j2
public class FileController {

	@Autowired
	private FileService fileService;

	@PostMapping
	public File addFile (@RequestBody File file){
		log.debug("addFile: site = {}", file);
		return fileService.addFile(file);
	}
	@GetMapping("/{fileId}")
	public File getFileById(@PathVariable Long fileId){
		log.debug("getFileById: site = {}", fileId);
		return fileService.getFileById(fileId);

	}

	@PostMapping("/uploadFile")
	public Long uploadFile(@RequestParam("file") MultipartFile file)  {
		return fileService.storeFile(file);
	}

	@GetMapping("/downloadFile/{fileId}")
	public ResponseEntity<byte[]> downloadFile(@PathVariable Long fileId) throws IOException {
		// Load file from database
		FileHolder file = fileService.downloadFileById(fileId);
		return ResponseEntity.ok()
				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\""
						+ file.getFileName())
				.body(file.getBytes());
	}
}
