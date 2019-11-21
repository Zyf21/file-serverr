package file.server.services;

import file.server.exceptions.FileStorageException;


import file.server.exceptions.FileStorageException;
import file.server.exceptions.MyFileNotFoundException;
import file.server.models.File;
import file.server.models.FileHolder;
import file.server.repositories.FileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Timestamp;
import java.util.Optional;

@Service
public class FileService {

	@Autowired
	private FileRepository fileRepository;

	@Value("${files.storage-path}")
	private String storagePath;


	public Long storeFile(MultipartFile file, Long employeeId) {
		// Normalize file name
		String fileName = StringUtils.cleanPath(file.getOriginalFilename());
		//String fileName = org.apache.commons.codec.digest.DigestUtils.sha256Hex(fileName1);

		try {
			// Check if the file's name contains invalid characters
			if(fileName.contains("..")) {
				throw new FileStorageException("Sorry! Filename contains invalid path sequence " + fileName);
			}

			String filePath = storagePath + "/" + org.apache.commons.codec.digest.DigestUtils.sha256Hex(fileName+new Timestamp(System.currentTimeMillis()));
			File dbFile = File.builder()
					.fileName(fileName)
					.employeeId(employeeId)
					.path(filePath)
					.build();

			Files.write(Paths.get(filePath), file.getBytes());
			return fileRepository.save(dbFile).getFileId();
		} catch (IOException ex) {
			throw new FileStorageException("Could not store file " + fileName + ". Please try again!", ex);
		}
	}

	public FileHolder getFile(Long fileId) throws IOException {

		File file = fileRepository.findById(fileId)
				.orElseThrow(() -> new MyFileNotFoundException("File not found with id " + fileId));
		byte[] bytes = Files.readAllBytes(Paths.get(file.getPath()));

		return new FileHolder(file.getFileName(), bytes);

	}

}
