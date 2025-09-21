package com.example.beststore.service;

import com.example.beststore.models.product;
import com.example.beststore.models.productDto;
import com.example.beststore.repository.productRepo;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;  // ✅ CORRECT import
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class productService {

    private final productRepo repo;

    public productService(productRepo repo) {
        this.repo = repo;
    }

    public List<product> getAllProducts() {
        return repo.findAll();
    }

    public product createProduct(productDto dto) throws IOException {  // ✅ Removed @Valid (not needed here)
        product product = new product();
        product.setName(dto.getName());
        product.setBrand(dto.getBrand());
        product.setPrice(dto.getPrice());
        product.setCategory(dto.getCategory());
        product.setDescription(dto.getDescription());
        product.setCreationDate(new Date());

        // ✅ Fixed null check order
        if (dto.getImage() != null && !dto.getImage().isEmpty()) {
            String imageFileName = saveImageFile(dto.getImage());
            product.setImageFileName(imageFileName);
        }

        return repo.save(product);
    }

    private String saveImageFile(MultipartFile image) throws IOException {
        // ✅ No casting needed
        Path uploadPath = Paths.get("uploads/images");

        if (!Files.exists(uploadPath)) {
            Files.createDirectories(uploadPath);
        }

        String originalFilename = image.getOriginalFilename();

        // ✅ Simplified extension extraction
        String fileExtension = "";
        if (originalFilename != null && originalFilename.contains(".")) {
            fileExtension = originalFilename.substring(originalFilename.lastIndexOf("."));
        }

        // ✅ Generate unique filename
        String newFileName = UUID.randomUUID().toString() + fileExtension;

        // ✅ No casting needed
        Path filePath = uploadPath.resolve(newFileName);

        // ✅ Simplified file copy
        Files.copy(image.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);

        return newFileName;
    }
}