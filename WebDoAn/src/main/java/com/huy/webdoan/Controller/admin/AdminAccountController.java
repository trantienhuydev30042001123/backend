package com.huy.webdoan.Controller.admin;

import com.huy.webdoan.dto.UserDTO;
import com.huy.webdoan.dto.response.ResponseMessage;
import com.huy.webdoan.model.LogIn.Role;
import com.huy.webdoan.model.LogIn.RoleName;
import com.huy.webdoan.model.LogIn.User;
import com.huy.webdoan.model.Product;
import com.huy.webdoan.model.Size;
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

@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
@RestController
@RequestMapping(Contanst.Api.Path.ADMIN + "/account")
@RequiredArgsConstructor
public class AdminAccountController {
    @Autowired
    private AccountServiceImpl accountService;
    @Autowired
    RoleServiceImpl roleService;
    @PostMapping("create")
    public ResponseEntity<?> createAccount(@RequestBody UserDTO userDTO) {
        if (accountService.existsByUserName(userDTO.getUsername())){
            return new ResponseEntity<>(new ResponseMessage("no_user"), HttpStatus.OK);
        }
        if (accountService.existsByEmail(userDTO.getEmail())){
            return new ResponseEntity<>(new ResponseMessage("no_email"), HttpStatus.OK);
        }
        User user1 = new User(userDTO.getName(), userDTO.getUsername(), userDTO.getEmail());
        Set<String> stlRoles = userDTO.getRoles();
        Set<Role> roles = new HashSet<>();
        stlRoles.forEach(role -> {
            switch (role){
                case "admin" :
                    Role adminRole = roleService.findByName(RoleName.ADMIN).orElseThrow(()-> new RuntimeException("Role nt found"));
                    roles.add(adminRole);
                    break;
                default:
                    Role userRole = roleService.findByName(RoleName.USER).orElseThrow(()-> new RuntimeException("Role nt found"));
                    roles.add(userRole);
            }
        });
        user1.setRoles(roles);
        accountService.save(user1);
        return new ResponseEntity<>(new ResponseMessage("yes"), HttpStatus.OK);
    }
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
        user1.get().setEmail(user.getUsername());
        user1.get().setAvatar(user.getUsername());
        accountService.save(user1.get());
        return new ResponseEntity<>(new ResponseMessage("Update Success"), HttpStatus.OK);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteAccount(@PathVariable Long id){
        Optional<User> user = accountService.findById(id);

        if(!user.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        accountService.delete(user.get().getId());
        return new ResponseEntity<>(new ResponseMessage("Delete Success"), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> detailAccount(@PathVariable Long id){
        Optional<User> user = accountService.findById(id);

        if(!user.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new  ResponseEntity<> (user, HttpStatus.OK);
    }
    @GetMapping()
    public ResponseEntity<?> GetListAccount(){
        List<User> user = accountService.finAll();
        if (user.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(user, HttpStatus.OK);
    }
    @GetMapping("/roles")
    public ResponseEntity<?> GetListRole(){
        List<Role> roles = accountService.finAllRoles();
        if (roles.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(roles, HttpStatus.OK);
    }
}
