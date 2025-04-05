package com.cyber08.uniclub.services;

import com.cyber08.uniclub.dto.ProductDTO;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ProductServices {
    void insertProduct(MultipartFile file, String name, String description,
                       double price, int idSize, int idColor, int quantity);
    List<ProductDTO> getAllProducts(int pageNumber, int pageSize);
}
