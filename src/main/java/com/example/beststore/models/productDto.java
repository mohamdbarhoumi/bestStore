package com.example.beststore.models;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;
@AllArgsConstructor
@NoArgsConstructor
@Data
public class productDto {
    @NotEmpty(message = "The name is required")
    private String name;
    @NotEmpty(message = "Brand is required")
    private String brand;
    @NotEmpty(message = "Category is required")
    private String category;
    @Min(0)
    private double price;
    @Size(min = 10, message = "The description should be at least 100 characters")
    @Size(max = 100, message = "The description cannot exceed 100 characters")
    private String description;
    private MultipartFile image;



}
