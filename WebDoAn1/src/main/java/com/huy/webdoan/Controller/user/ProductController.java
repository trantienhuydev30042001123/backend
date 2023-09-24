package com.huy.webdoan.Controller.user;

import com.huy.webdoan.dto.Output;
import com.huy.webdoan.dto.productDTO;
import com.huy.webdoan.model.Product;
import com.huy.webdoan.dto.response.ResponseMessage;
import com.huy.webdoan.service.impl.ProductServiceImpl;
import com.huy.webdoan.utils.Contanst;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
@CrossOrigin(origins = {"http://localhost:4200", "https://web-app-da.web.app/"}, maxAge = 3600)
@RestController
@RequestMapping(Contanst.Api.Path.USER + "/product")
@RequiredArgsConstructor
public class ProductController {
    @Autowired
    ProductServiceImpl productServiceIpm;
    @PostMapping("create")
    public ResponseEntity<?> createProduct(@RequestBody Product product){
        if(product.getName().trim().isEmpty()){
            new ResponseEntity<>(new ResponseMessage("the name is required"), HttpStatus.OK);
        }
        productServiceIpm.save(product);
        return new ResponseEntity<>(new ResponseMessage("Create Product success"), HttpStatus.OK);
    }

    @GetMapping()
//    public ResponseEntity<?> GetListProduct(){
//        List<Product> products = productServiceIpm.finAll();
//        if (products.isEmpty()){
//            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//        }
//        return new ResponseEntity<>(products, HttpStatus.OK);
//    }
    public ResponseEntity<?> GetListProduct(
            @RequestParam(defaultValue = "") String searchKey,
            @RequestParam(defaultValue = "0") Integer startprice,
            @RequestParam(defaultValue = "0") Integer endPrice) {
        List<Product> products = productServiceIpm.finAll(searchKey, startprice, endPrice);
        return new ResponseEntity<>(products, HttpStatus.OK);
    }
//        @GetMapping()
//    public Output GetListProduct(@RequestParam int page,
//                                 @RequestParam int limit){
//        Output result = new Output();
//        result.setPage(page);
//        Pageable pageable = PageRequest.of(page - 1 , limit);
//        result.setListResult(productServiceIpm.finAll(pageable));
//        result.setTotalPage((int) Math.ceil((double) (productServiceIpm.totalItem()) /limit));
//        return result;
//    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateProduct(@PathVariable Long id, @RequestBody Product product){
        Optional<Product> product1 = productServiceIpm.findById(id);

        if(!product1.isPresent()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        if (product.getName().trim().isEmpty()){
            return  new ResponseEntity<>(new ResponseMessage("the name is required"), HttpStatus.OK);
        }
        product1.get().setName(product.getName());
        product1.get().setImage(product.getImage());
        product1.get().setImage2(product.getImage());
        product1.get().setImage3(product.getImage());
        product1.get().setPrice(product.getPrice());
        product1.get().setDiscount(product.getDiscount());
        product1.get().setDescription(product.getImage());
        product1.get().setAvailable(product.getAvailable());
        product1.get().setCategory(product.getCategory());

        productServiceIpm.save(product1.get());
        return new ResponseEntity<>(new ResponseMessage("Update Success"), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteProduct(@PathVariable Long id){
        Optional<Product> product = productServiceIpm.findById(id);

        if(!product.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        productServiceIpm.delete(product.get().getId());
        return new ResponseEntity<>(new ResponseMessage("Delete Success"), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> detailProduct(@PathVariable Long id){
        Optional<Product> product = productServiceIpm.findById(id);

        if(!product.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new  ResponseEntity<> (product, HttpStatus.OK);
    }
    @GetMapping("/category/{catId}")
    public ResponseEntity<?> getProductbyCategory(@PathVariable Long catId){
      List<Product> findProductByCategory =  this.productServiceIpm.findProductByCategory(catId);
        return new ResponseEntity<>(findProductByCategory,HttpStatus.ACCEPTED);
    }
    @GetMapping("/Rating/{RatingId}")
    public ResponseEntity<?> getProductbyRating(@PathVariable Long RatingId){
        List<Product> findProductByRating =  this.productServiceIpm.findProductByRating(RatingId);
        return new ResponseEntity<>(findProductByRating,HttpStatus.ACCEPTED);
    }
}
