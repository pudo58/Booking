package com.insmart.app.controller;

import com.insmart.app.bean.ResponseData;
import com.insmart.app.model.Token;
import com.insmart.app.model.User;
import com.insmart.app.service.RoleService;
import com.insmart.app.service.UserService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Authorization;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.tholv.Utils.DateUtils;
import org.tholv.Utils.DoubleUtils;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@CrossOrigin(origins = "http://localhost")
@RequestMapping("/api/user")
public class UserController {
    @Autowired
     UserService userService;

    @Autowired
    private RoleService roleService;

    @PostMapping("/create")
    @SecurityRequirement(name = "javainuseapi")
    // @ApiOperation(value = "", authorizations = { @Authorization(value="admin") }) fucking shit swagger
    public User save(@RequestBody User user) {
        user.setCode(UUID.randomUUID().toString());
        if(user.getRole() == null){
            user.setRole(roleService.findById(2L));
        }
        user.setEnable(0);
        if(!user.getPassword().equals(user.getRePassword()))
            throw new RuntimeException("Password is not match");
        return userService.save(user);
    }
    @GetMapping("/get/{id}/{token}")
    @SecurityRequirement(name = "javainuseapi")
    public User findById(@PathVariable Long id,@PathVariable String token) {
        return userService.findById(id,token);
    }
    @DeleteMapping("/delete/{id}/{token}")
    @SecurityRequirement(name = "javainuseapi")
    public void deleteById(@PathVariable Long id,@PathVariable String token) {
        userService.deleteById(id,token);
    }
    @GetMapping("/get/{token}")
    @SecurityRequirement(name = "javainuseapi")
    public java.util.List<User> findAll(@PathVariable String token) {

        return userService.findAll(token);
    }
    @PutMapping("/update/{id}/{token}")
    @SecurityRequirement(name = "javainuseapi")
    public User update(@RequestBody User user,@PathVariable Long id, @PathVariable String token) throws IOException {
        User user1 = userService.findById(id,token);
        user1.setEmail(user.getEmail());
        user1.setPhoneNumber(user.getPhoneNumber());
        return userService.update(user1,token);
    }
    @SecurityRequirement(name = "javainuseapi")
    @GetMapping("/get/{page}/{size}")
    public org.springframework.data.domain.Page<User> findAll(@PathVariable Optional<Integer> page, @PathVariable int size) {
        return userService.findAll(page.orElse(0), size);
    }
    @PostMapping("/login")
    public com.insmart.app.bean.ResponseData login(@RequestBody User user) {
        return userService.login(user);
    }
    @GetMapping("/f/{param}")
    @SecurityRequirement(name = "javainuseapi")
    public List<User> findByUsernameOrEmail(@PathVariable String param) {
        return userService.findByUsernameOrEmail(param);
    }
    @DeleteMapping("/deleteAll/{ids}")
    @SecurityRequirement(name = "javainuseapi")
    public void deleteAll(@PathVariable List<Long> ids) {
        userService.deleteAllBatch(ids);
    }
    // logout
    @GetMapping("/logout/{code}")
    @SecurityRequirement(name = "javainuseapi")
    public ResponseData logout(@PathVariable String code) throws RuntimeException{
        return userService.logout(code);
    }
}
