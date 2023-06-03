package com.huy.webdoan.Controller.admin;

import com.huy.webdoan.dto.response.ResponseMessage;
import com.huy.webdoan.model.Category;
import com.huy.webdoan.model.LogIn.User;
import com.huy.webdoan.service.impl.CategoryService;
import com.huy.webdoan.utils.Contanst;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
@RestController
@RequestMapping(Contanst.Api.Path.ADMIN + "/category")
@RequiredArgsConstructor
public class AdminCategoryController {
    @Autowired
    CategoryService categoryService;
    @PostMapping("create")
    public ResponseEntity<?> createCategory(@RequestBody Category category){
        if(category.getName().trim().isEmpty()){
            new ResponseEntity<>(new ResponseMessage("the name is required"), HttpStatus.OK);
        }
        categoryService.save(category);
        return new ResponseEntity<>(new ResponseMessage("Create Product success"), HttpStatus.OK);
    }
    @GetMapping()
    public List<Category> listCategory(){
        return categoryService.listCategory();
    }

    @PostMapping("/{id}")
    public ResponseEntity<?> updateCategory(@PathVariable Long id, @RequestBody Category category){
        Optional<Category> category1 = categoryService.findById(id);

        if(!category1.isPresent()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        if (category.getName().trim().isEmpty()){
            return  new ResponseEntity<>(new ResponseMessage("the name is required"), HttpStatus.OK);
        }
        category1.get().setName(category.getName());
        category1.get().setDescription(category.getDescription());

        categoryService.save(category1.get());
        return new ResponseEntity<>(new ResponseMessage("Update Success"), HttpStatus.OK);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCategory(@PathVariable Long id){
        Optional<Category> category = categoryService.findById(id);

        if(!category.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        categoryService.delete(category.get().getId());
        return new ResponseEntity<>(new ResponseMessage("Delete Success"), HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<?> detailcategory(@PathVariable Long id){
        Optional<Category> category = categoryService.findById(id);

        if(!category.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new  ResponseEntity<> (category, HttpStatus.OK);
    }
}
