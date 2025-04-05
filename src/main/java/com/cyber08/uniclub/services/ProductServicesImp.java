package com.cyber08.uniclub.services;

import com.cyber08.uniclub.dto.ProductDTO;
import com.cyber08.uniclub.entity.Color;
import com.cyber08.uniclub.entity.Product;
import com.cyber08.uniclub.entity.Size;
import com.cyber08.uniclub.entity.Varient;
import com.cyber08.uniclub.exception.InsertException;
import com.cyber08.uniclub.respository.ProductRepository;
import com.cyber08.uniclub.respository.VarientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductServicesImp implements ProductServices {

    @Autowired
    private FilesStorageService filesStorageService;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private VarientRepository varientRepository;

    @Transactional
    @Override
    public void insertProduct(MultipartFile file, String name, String description,
                              double price, int idSize, int idColor, int quantity) {
        try{
            Product product = new Product();
            product.setName(name);
            product.setDescription(description);
            product.setPrice(price);
            Product productInsert = productRepository.save(product);
//        filesStorageService.save(file);

            Color color = new Color();
            color.setId(idColor);

            Size size = new Size();
            size.setId(idSize);

            Varient varient = new Varient();
            varient.setProduct(productInsert);
            varient.setColor(color);
            varient.setSize(size);
            varient.setQuantity(quantity);
            varient.setImages(file.getOriginalFilename());

            filesStorageService.save(file);
            varientRepository.save(varient);
        } catch (Exception e) {
            throw new InsertException("Product: "+e.getMessage());
        }
    }

    @Override
    public List<ProductDTO> getAllProducts(int pageNumber, int pageSize) {
//        List<Product> products = productRepository.findAll();
//        List<ProductDTO> productDTOs = new ArrayList<>();
//
//        for (Product product : products) {
//            ProductDTO productDTO = new ProductDTO();
//            productDTO.setName(product.getName());
//            productDTO.setPrice(product.getPrice());
//
//            if(!product.getVarients().isEmpty()){
//                productDTO.setUrlImage(product.getVarients().get(0).getImages());
//            }
//            productDTOs.add(productDTO);
//        }
//      StreamApi:
//      map <==> foreach
        Pageable page = PageRequest.of(pageNumber, pageSize);
        List<ProductDTO> productDTOs = productRepository.findAll(page).stream().
                map(product -> {
                    ProductDTO productDTO = new ProductDTO();
                    productDTO.setName(product.getName());
                    productDTO.setPrice(product.getPrice());

                    if(!product.getVarients().isEmpty()){
                    productDTO.setUrlImage("http://localhost:8080/file/"+product.getVarients().get(0).getImages());
                    }

                    return productDTO;
                }).toList();

        return productDTOs;
    }
}
