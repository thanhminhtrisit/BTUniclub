package com.cyber08.uniclub.controller;

import com.cyber08.uniclub.payload.response.BaseResponse;
import com.cyber08.uniclub.services.ProductServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductServices productServices;

    @PostMapping
    public ResponseEntity<?> insertProduct(@RequestParam MultipartFile file, String name, String description,
                                           double price, int idSize, int idColor, int quantity) {

        productServices.insertProduct(file, name, description, price, idSize, idColor, quantity);

        return ResponseEntity.ok("Hello insert product");
    }

    @GetMapping
    public ResponseEntity<?> getAllProducts(@RequestParam int pageNumber, int pageSize) {
        BaseResponse baseResponse = new BaseResponse();
        baseResponse.setMessage("success");
        baseResponse.setCode(200);
        baseResponse.setData(productServices.getAllProducts(pageNumber, pageSize));

        return ResponseEntity.ok(baseResponse);
    }
}
