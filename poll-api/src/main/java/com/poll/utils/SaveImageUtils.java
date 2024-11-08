package com.poll.utils;

import com.poll.config.WebConfig;
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
            Path path = Path.of(Paths.get(WebConfig.physicalPath) + "/" + position + "/" + newFileName);
            Files.write(path, bytes);
            return newFileName;
    }

    public static void deleteImage(String fileName, String position) throws IOException {
        Path basePath = Paths.get(WebConfig.physicalPath);
        Path path = basePath.resolve(position).resolve(fileName);
        Files.deleteIfExists(path);
    }
}
