package com.insmart.app.controller;

import com.insmart.app.bean.ResponseData;
import com.insmart.app.model.Token;
import com.insmart.app.service.TokenService;
import com.insmart.app.utils.GenerateToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("http://localhost")
@RequestMapping("/api/token")
public class TokenController {
    @Autowired
    private TokenService tokenService;
    @PostMapping("/create")
    public Token save(@RequestBody Token token) {
        token.setCode(GenerateToken.generateNewToken(25));
        return tokenService.save(token);
    }
    @GetMapping("/get/{id}")
    public Token findById(@PathVariable Long id) {
        return tokenService.findById(id);
    }
    @DeleteMapping("/delete/{id}")
    public void deleteById(@PathVariable Long id) {
        tokenService.deleteById(id);
    }
    @GetMapping("/get")
    public java.util.List<Token> findAll() {
        return tokenService.findAll();
    }
    @PutMapping("/update")
    public Token update(@RequestBody Token token) {
        return tokenService.update(token);
    }
    @GetMapping("/get/page/{page}")
    public org.springframework.data.domain.Page<Token> findAll(@PathVariable int page) {
        return tokenService.findAll(page, 20);
    }
    @GetMapping("/get/code/{code}")
    public ResponseData findByCode(@PathVariable String code) {
         if(tokenService.findByCode(code) == null)
                return new ResponseData("fail", null, null,"Token not found", HttpStatus.NO_CONTENT.value());
            else
                return new ResponseData("success", tokenService.findByCode(code).getCode(), tokenService.findByCode(code),"Success",HttpStatus.OK.value());
    }
}
