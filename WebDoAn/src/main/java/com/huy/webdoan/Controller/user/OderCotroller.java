package com.huy.webdoan.Controller.user;

import com.huy.webdoan.dto.request.OrderInput;
import com.huy.webdoan.model.Order;
import com.huy.webdoan.service.impl.OderServiceImpl;
import com.huy.webdoan.service.impl.ProductServiceImpl;
import com.huy.webdoan.utils.Contanst;
import lombok.RequiredArgsConstructor;
import org.aspectj.weaver.ast.Or;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = {"http://localhost:4200", "https://web-app-da.web.app/"}, maxAge = 3600)
@RestController
@RequestMapping(Contanst.Api.Path.USER + "/order")
@RequiredArgsConstructor
public class OderCotroller {
    @Autowired
    private ProductServiceImpl productService;

    @Autowired
    private OderServiceImpl oderService;
    @PostMapping("/placeOrder")
    public void placeOrder(@RequestBody OrderInput orderInput){
        oderService.placeOrder(orderInput);

    }
    @GetMapping()
    public ResponseEntity<?> GetListOrder(){
        List<Order> orders = oderService.finAll();
        return new ResponseEntity<>(orders, HttpStatus.OK);
    }
}
