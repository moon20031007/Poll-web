package com.poll.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@RestController
public class ImageController {

    private final Path rootLocation = Paths.get("src/main/resources/static/image");

    @PostMapping("/uploadImage")
    public ResponseEntity<String> handleFileUpload(@RequestParam(value = "file") MultipartFile file) {
        try {
            if (!file.isEmpty()) {
                byte[] bytes = file.getBytes();
                Path path = this.rootLocation.resolve(getUniqueFileName(file.getOriginalFilename()));
                Files.write(path, bytes);
                return ResponseEntity.ok("OK");
            } else {
                return ResponseEntity.badRequest().body("未选择文件");
            }
        } catch (IOException e) {
            return ResponseEntity.status(500).body("无法上传文件");
        }
    }

    private String getUniqueFileName(String originalFilename) {
        return UUID.randomUUID().toString() + "_" + originalFilename;
    }
}
