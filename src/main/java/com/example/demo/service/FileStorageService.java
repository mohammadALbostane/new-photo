package com.example.demo.service;



import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
public class FileStorageService {
    private final Path rootLocation = Paths.get("/app/uploads");

    public FileStorageService() {
        try {
            Files.createDirectories(rootLocation);
        } catch (IOException e) {
            throw new RuntimeException("Could not initialize storage!", e);
        }
    }

    public String saveFile(MultipartFile file) {
        try {
            Path destinationFile = this.rootLocation.resolve(file.getOriginalFilename());
            Files.copy(file.getInputStream(), destinationFile);
            return file.getOriginalFilename();
        } catch (Exception e) {
            throw new RuntimeException("Failed to store file.", e);
        }
    }

    public Path loadFile(String filename) {
        return rootLocation.resolve(filename);
    }
}
