package com.huy.webdoan.Controller.user;

import com.huy.webdoan.dto.request.OrderInput;
import com.huy.webdoan.model.Order;
import com.huy.webdoan.model.OrderDetail;
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
import java.util.Map;

@CrossOrigin(origins = {"http://localhost:4200", "https://feda-c1fi.vercel.app/"}, maxAge = 3600)
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
    @GetMapping("/{userId}")
    public ResponseEntity<?> GetListOrder(@PathVariable Long userId){
        List<Map> orders = oderService.finAll(userId);
        return new ResponseEntity<>(orders, HttpStatus.OK);
    }
    @GetMapping("/orderDetal")
    public ResponseEntity<?> GetListOrderDetail(){
        List<OrderDetail> orderDetails = oderService.getlist();
        return new ResponseEntity<>(orderDetails, HttpStatus.OK);
    }
}
