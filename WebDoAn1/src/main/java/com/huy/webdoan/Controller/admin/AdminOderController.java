package com.huy.webdoan.Controller.admin;

import com.huy.webdoan.model.Order;
import com.huy.webdoan.service.impl.OderServiceImpl;
import com.huy.webdoan.utils.Contanst;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
@RestController
@RequestMapping(Contanst.Api.Path.ADMIN + "/oder")
@RequiredArgsConstructor
public class AdminOderController {
    @Autowired
    OderServiceImpl oderService;

    @GetMapping()
    public List<Map> listOrder() {
        return oderService.finAll();
    }


    @PostMapping("/{id}")
    public Order deleteOrder(@PathVariable Long id) {
        return oderService.deleteOder(id);
    }

    @PostMapping("/{id}/{status}")
    public Order updateStatus(@PathVariable Long id, @PathVariable Integer status) {
        return oderService.updateStatus(id, status);
    }
}
