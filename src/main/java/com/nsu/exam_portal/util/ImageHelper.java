package com.nsu.exam_portal.util;

import com.nsu.exam_portal.exception.FileNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Set;
import java.util.UUID;

@Component
public class ImageHelper {

    private static final Logger logger = LoggerFactory.getLogger(ImageHelper.class);

    private static final Set<String> ALLOWED_EXTENSIONS = Set.of(".jpg", ".jpeg", ".png", ".gif");

    @Value("${upload_dir}")
    private String uploadDir;

    public String saveImage(MultipartFile file) {
        if (file == null || file.isEmpty()) {
            throw new FileNotFoundException("File is empty or null");
        }

        try {
            Path uploadPath = Paths.get(uploadDir).toAbsolutePath().normalize();
            Files.createDirectories(uploadPath); // Handles already existing dirs safely

            String originalFilename = file.getOriginalFilename();
            String extension = getFileExtension(originalFilename);

            if (!ALLOWED_EXTENSIONS.contains(extension.toLowerCase())) {
                throw new FileNotFoundException("Invalid file type: " + extension);
            }

            String uniqueFileName = UUID.randomUUID().toString() + extension;
            Path targetPath = uploadPath.resolve(uniqueFileName);

            file.transferTo(targetPath.toFile());
            logger.info("File saved at: {}", targetPath);

            // Return relative path (assumes /img/ is mapped to static upload directory)
            return "/img/" + uniqueFileName;

        } catch (IOException e) {
            logger.error("File upload failed", e);
            throw new RuntimeException("Could not save file. Please try again.");
        }
    }

    private String getFileExtension(String fileName) {
        if (fileName == null || fileName.lastIndexOf('.') == -1) {
            return "";
        }
        return fileName.substring(fileName.lastIndexOf("."));
    }
}
