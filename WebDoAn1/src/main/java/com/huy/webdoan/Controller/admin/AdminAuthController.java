package com.huy.webdoan.Controller.admin;

import com.huy.webdoan.dto.request.SignIn;
import com.huy.webdoan.dto.request.SignUp;
import com.huy.webdoan.dto.response.ResponseMessage;
import com.huy.webdoan.dto.response.jwtResponse;
import com.huy.webdoan.model.LogIn.Role;
import com.huy.webdoan.model.LogIn.RoleName;
import com.huy.webdoan.model.LogIn.User;
import com.huy.webdoan.security.jwt.jwtProvider;
import com.huy.webdoan.security.userprincal.UserPrinciple;
import com.huy.webdoan.service.impl.RoleServiceImpl;
import com.huy.webdoan.service.impl.UserServiceImpl;
import com.huy.webdoan.utils.Contanst;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashSet;
import java.util.Set;

@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
@RestController
@RequestMapping(Contanst.Api.Path.ADMIN)
@RequiredArgsConstructor
public class AdminAuthController {
    @Autowired
    UserServiceImpl userService;
    @Autowired
    RoleServiceImpl roleService;
    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    jwtProvider jwtProvider;

    @PostMapping("/signup")
    public ResponseEntity<?> register(@Valid @RequestBody SignUp signUp){
        if (userService.existsByUsername(signUp.getUsername())){
            return new ResponseEntity<>(new ResponseMessage("no_user"), HttpStatus.OK);
        }
        if (userService.existsAllByEmail(signUp.getEmail())){
           return new ResponseEntity<>(new ResponseMessage("no_email"), HttpStatus.OK);
        }
        User user = new User(signUp.getName(), signUp.getUsername(), signUp.getEmail(), passwordEncoder.encode(signUp.getPassword()));
        Set<String> stlRoles = signUp.getRoles();
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
        user.setRoles(roles);
        userService.save(user);
        return new ResponseEntity<>(new ResponseMessage("yes"), HttpStatus.OK);
    }

    @PostMapping("/signin")
    public ResponseEntity<?> login(@Valid @RequestBody SignIn signIn){
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(signIn.getUsername(),signIn.getPassword())
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String token = jwtProvider.createToken(authentication);
        UserPrinciple userPrinciple = (UserPrinciple) authentication.getPrincipal();
        return ResponseEntity.ok(new jwtResponse(token,userPrinciple.getId(), userPrinciple.getName(), userPrinciple.getAuthorities()));
    }
}
