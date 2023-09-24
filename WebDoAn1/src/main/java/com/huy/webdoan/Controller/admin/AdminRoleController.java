package com.huy.webdoan.Controller.admin;

import com.huy.webdoan.dto.response.ResponseMessage;
import com.huy.webdoan.model.LogIn.Role;
import com.huy.webdoan.model.LogIn.User;
import com.huy.webdoan.service.IRoleService;
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
@RequestMapping(Contanst.Api.Path.ADMIN + "/role")
@RequiredArgsConstructor
public class AdminRoleController {
    @Autowired
    private IRoleService iRoleService;

    @GetMapping()
    public ResponseEntity<?> getListRole(){
        List<Role> role = iRoleService.finAll();
        if (role.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(role, HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<?> detailRole(@PathVariable Long id){
        Optional<Role> role = iRoleService.findById(id);

        if(!role.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new  ResponseEntity<> (role, HttpStatus.OK);
    }
    @PostMapping("create")
    public ResponseEntity<?> createRole(@RequestBody Role role) {
        iRoleService.save(role);
        return new ResponseEntity<>(new ResponseMessage("Create success"), HttpStatus.OK);
    }
    @PostMapping("/{id}")
    public ResponseEntity<?> updateRole(@PathVariable Long id, @RequestBody Role role) {
        Optional<Role> role1 = iRoleService.findById(id);
        if (!role1.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        role1.get().setName(role.getName());
        return new ResponseEntity<>(new ResponseMessage("Update Success"), HttpStatus.OK);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteRole(@PathVariable Long id){
        Optional<Role> role = iRoleService.findById(id);

        if(!role.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        iRoleService.delete(role.get().getId());
        return new ResponseEntity<>(new ResponseMessage("Delete Success"), HttpStatus.OK);
    }
}
