package com.huy.webdoan.Controller.admin;

import com.huy.webdoan.dto.response.ResponseMessage;
import com.huy.webdoan.model.LogIn.User;
import com.huy.webdoan.model.Size;
import com.huy.webdoan.service.impl.SizeServiceImpl;
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
@RequestMapping(Contanst.Api.Path.ADMIN + "/size")
@RequiredArgsConstructor
public class AdminSizeController {
    @Autowired
    SizeServiceImpl sizeService;

    @PostMapping("create")
    public ResponseEntity<?> createSize( @RequestBody Size size) {
        if (sizeService.existsBySize(size.getSize())) {
            return new ResponseEntity<>(new ResponseMessage("size đã tồn tại"), HttpStatus.OK);
        }
        sizeService.save(size);
        return new ResponseEntity<>(new ResponseMessage("Create size success"), HttpStatus.OK);
    }
    @PostMapping("/{id}")
    public ResponseEntity<?> updateSize(@PathVariable Long id, @RequestBody Size size) {
        Optional<Size> size1 = sizeService.findById(id);
        if (!size1.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        if (sizeService.existsBySize(size.getSize())) {
            return new ResponseEntity<>(new ResponseMessage("size đã tồn tại"), HttpStatus.OK);
        }
        size1.get().setSize(size.getSize());
        sizeService.save(size1.get());
        return new ResponseEntity<>(new ResponseMessage("Update Success"), HttpStatus.OK);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteSize(@PathVariable Long id){
        Optional<Size> size = sizeService.findById(id);

        if(!size.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        sizeService.delete(size.get().getId());
        return new ResponseEntity<>(new ResponseMessage("Delete Success"), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> detailSize(@PathVariable Long id){
        Optional<Size> size = sizeService.findById(id);

        if(!size.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new  ResponseEntity<> (size, HttpStatus.OK);
    }
    @GetMapping()
    public ResponseEntity<?> GetListSize(){
        List<Size> size = sizeService.finAll();
        if (size.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(size, HttpStatus.OK);
    }
}
