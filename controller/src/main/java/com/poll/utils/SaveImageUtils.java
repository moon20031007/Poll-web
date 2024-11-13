package com.poll.utils;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

public class SaveImageUtils {
    public static String saveImage(MultipartFile file, String position) throws IOException {
        byte[] bytes = file.getBytes();
        String originalFilename = file.getOriginalFilename();
        assert originalFilename != null;
        String fileExtension = originalFilename.substring(originalFilename.lastIndexOf("."));
        String newFileName = UUID.randomUUID().toString() + fileExtension;
//        Path path = Paths.get("view", "src", "main", "resources", "static", position, newFileName);
        Path path = Paths.get("view", "static", position, newFileName);
        Files.write(path, bytes);
        return newFileName;
    }

    public static void deleteImage(String fileName, String position) throws IOException {
//        Path path = Paths.get("view", "src", "main", "resources", "static", position, fileName);
        Path path = Paths.get("view", "static", position, fileName);
        Files.deleteIfExists(path);
    }
}
