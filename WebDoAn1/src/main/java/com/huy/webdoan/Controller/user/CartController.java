package com.huy.webdoan.Controller.user;

import com.huy.webdoan.dto.response.ResponseMessage;
import com.huy.webdoan.model.Cart;
import com.huy.webdoan.model.Category;
import com.huy.webdoan.model.LogIn.User;
import com.huy.webdoan.model.Product;
import com.huy.webdoan.model.Size;
import com.huy.webdoan.service.ICartService;
import com.huy.webdoan.service.impl.CartServiceImpl;
import com.huy.webdoan.utils.Contanst;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = {"http://localhost:4200", "https://shopgiayhuytt.vercel.app"}, maxAge = 3600)
@RestController
@RequestMapping(Contanst.Api.Path.USER + "/cart")
@RequiredArgsConstructor
public class CartController {
    @Autowired
    private CartServiceImpl cartService;
    @PostMapping("/{id}/{userId}/{size}")
    public ResponseEntity<?> addToCart(@PathVariable Long id ,@PathVariable Long userId, @PathVariable Integer size){
        cartService.addToCart(id,userId, size);
        return new ResponseEntity<>(new ResponseMessage("add Success"), HttpStatus.OK);
    }
    @GetMapping("/{userId}")
    public ResponseEntity<?> getListCart(@PathVariable Long userId) {
        List<Cart> carts = cartService.findByUserId(userId);
        if (carts.isEmpty()) {
            return ResponseEntity.ok(new ArrayList<>()); // Trả về một mảng rỗng []
        }
        return ResponseEntity.ok(carts);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteOder(@PathVariable Long id){
        Optional<Cart> cart = cartService.findById(id);

        if(!cart.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        cartService.delete(cart.get().getId());
        return new ResponseEntity<>(new ResponseMessage("Delete Success"), HttpStatus.OK);
    }
    @PostMapping("/delete")
    public ResponseEntity<?> deleteAllCart(@RequestBody Long[] cartIdArray){
        cartService.deleteAllListCart(cartIdArray);
        return new ResponseEntity<>(new ResponseMessage("Delete All Success"), HttpStatus.OK);
    }
    @PostMapping("/update/{id}/{quantity}")
    public ResponseEntity<?> updateCart(@PathVariable Long id, @PathVariable int quantity) {
        Optional<Cart> cart = cartService.findById(id);

        if (!cart.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        int newQuantity = cart.get().getQuantity() + quantity;
        cart.get().setQuantity(newQuantity);

        if (newQuantity == 0) {
            cartService.delete(id);
            return new ResponseEntity<>(new ResponseMessage("Cart deleted"), HttpStatus.OK);
        }

        cartService.save(cart.get());
        return new ResponseEntity<>(new ResponseMessage("Update Success"), HttpStatus.OK);
    }
}
