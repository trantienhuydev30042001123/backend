package com.huy.webdoan.Controller.user;

import com.huy.webdoan.dto.UserDTO;
import com.huy.webdoan.dto.response.ResponseMessage;
import com.huy.webdoan.model.LogIn.Role;
import com.huy.webdoan.model.LogIn.RoleName;
import com.huy.webdoan.model.LogIn.User;
import com.huy.webdoan.service.impl.AccountServiceImpl;
import com.huy.webdoan.service.impl.RoleServiceImpl;
import com.huy.webdoan.utils.Contanst;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@CrossOrigin(origins = {"http://localhost:4200", "https://shopgiayhuytt.vercel.app"}, maxAge = 3600)
@RestController
@RequestMapping(Contanst.Api.Path.USER + "/account")
@RequiredArgsConstructor
public class AccountController {
    @Autowired
    private AccountServiceImpl accountService;
    @Autowired
    RoleServiceImpl roleService;
    @PostMapping("/{id}")
    public ResponseEntity<?> updateAccount(@PathVariable Long id, @RequestBody User user) {
        Optional<User> user1 = accountService.findById(id);
        if (!user1.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        if (accountService.existsByUserName(user.getUsername())) {
            return new ResponseEntity<>(new ResponseMessage("Tên đăng nhập đã tồn tại"), HttpStatus.OK);
        }
        if (accountService.existsByEmail(user.getEmail())) {
            return new ResponseEntity<>(new ResponseMessage("Email đã tồn tại"), HttpStatus.OK);
        }
        user1.get().setName(user.getName());
        user1.get().setUsername(user.getUsername());
        user1.get().setEmail(user.getEmail());
//        user1.get().setAvatar(user.getUsername());
        accountService.save(user1.get());
        return new ResponseEntity<>(new ResponseMessage("Update Success"), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> detailAccount(@PathVariable Long id) {
        List<User> userList = accountService.findByIds(id);

        if (userList.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(userList, HttpStatus.OK);
    }
}
