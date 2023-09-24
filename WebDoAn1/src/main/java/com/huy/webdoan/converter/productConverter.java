package com.huy.webdoan.converter;

import com.huy.webdoan.dto.productDTO;
import com.huy.webdoan.model.Product;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class productConverter {
    public Product toModel1(productDTO productDTO){
        Product product = new Product();
        product.setName(productDTO.getName());
        product.setImage(productDTO.getImage());
        product.setImage2(productDTO.getImage2());
        product.setImage3(productDTO.getImage3());
        product.setPrice(productDTO.getPrice());
        product.setDiscount(productDTO.getDiscount());
        product.setDescription(productDTO.getDescription());
        product.setAvailable(productDTO.getAvailable());
        return product;
    }

    public productDTO toDTO(Product product){
        productDTO productDTO = new productDTO();
        productDTO.setId(product.getId());
        productDTO.setName(product.getName());
        productDTO.setImage(product.getImage());
        productDTO.setImage2(product.getImage2());
        productDTO.setImage3(product.getImage3());
        productDTO.setPrice(product.getPrice());
        productDTO.setDiscount(product.getDiscount());
        productDTO.setDescription(product.getDescription());
        productDTO.setAvailable(product.getAvailable());
        productDTO.setCategory(product.getCategory());
        productDTO.setRatings(product.getRating());
        return productDTO;
    }
    public productDTO toDTO1(Product product){
        productDTO productDTO = new productDTO();
        if(product.getId() != null){
            productDTO.setId(product.getId());
        }
        productDTO.setName(product.getName());
        productDTO.setImage(product.getImage());
        productDTO.setImage2(product.getImage2());
        productDTO.setImage3(product.getImage3());
        productDTO.setPrice(product.getPrice());
        productDTO.setDiscount(product.getDiscount());
        productDTO.setDescription(product.getDescription());
        productDTO.setAvailable(product.getAvailable());
        return productDTO;
    }



}
