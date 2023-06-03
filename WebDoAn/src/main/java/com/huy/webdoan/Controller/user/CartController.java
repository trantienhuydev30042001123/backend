package com.huy.webdoan.Controller.user;

import com.huy.webdoan.dto.response.ResponseMessage;
import com.huy.webdoan.model.Cart;
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

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = {"http://localhost:4200", "https://web-app-da.web.app/"}, maxAge = 3600)
@RestController
@RequestMapping(Contanst.Api.Path.USER + "/cart")
@RequiredArgsConstructor
public class CartController {
    @Autowired
    private CartServiceImpl cartService;
    @PostMapping("/{id}")
    public ResponseEntity<?> addToCart(@PathVariable Long id){
        cartService.addToCart(id);
        return new ResponseEntity<>(new ResponseMessage("add Success"), HttpStatus.OK);
    }
    @GetMapping()
    public ResponseEntity<?> GetListCart(){
        List<Cart> carts = cartService.finAll();
        if (carts.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(carts, HttpStatus.OK);
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
}
