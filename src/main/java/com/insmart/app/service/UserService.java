package com.insmart.app.service;
import com.insmart.app.bean.ResponseData;
import com.insmart.app.model.Token;
import com.insmart.app.model.User;
import com.insmart.app.repository.TokenRepository;
import com.insmart.app.repository.UserRepository;
import com.insmart.app.utils.Bcript;
import com.insmart.app.utils.CookieUtils;
import com.insmart.app.utils.GenerateToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.tholv.SecurityUtils.CheckMail;
import org.tholv.Utils.DateUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private TokenRepository tokenRepository;
    @Autowired
    HttpServletResponse response ;
    @Autowired
    Bcript bCryptPasswordEncoder;
    public User save(User user) {
        if(user.getEmail() != null) {
            if(!CheckMail.getInstance().checkEmail(user.getEmail())) {
                throw new RuntimeException("Email is not valid");
            }
        }
        user.setPassword(bCryptPasswordEncoder.passwordEncoder().encode(user.getPassword()));
        return userRepository.save(user);
    }
    public User findById(Long id,String token) {
        if(token == null) {
            throw new RuntimeException("Token is null");
        }
        Token token1 = tokenRepository.findByCode(token);
        if(token1 == null) {
            throw new RuntimeException("Token is not valid");
        }
        return userRepository.findById(id).orElseThrow();
    }
    public void deleteById(Long id,String token) {
        if(token == null) {
            throw new RuntimeException("Token is null");
        }
        Token token1 = tokenRepository.findByCode(token);
        if(token1 == null) {
            throw new RuntimeException("Token is not valid");
        }
        userRepository.deleteById(id);
    }
    public User update(User user,String token) {
        if(token == null) {
            throw new RuntimeException("Token is null");
        }
        Token token1 = tokenRepository.findByCode(token);
        if(token1 == null) {
            throw new RuntimeException("Token is not valid");
        }
        if(!CheckMail.getInstance().checkEmail(user.getEmail())) {
           throw new RuntimeException("Email is not valid");
        }
        return userRepository.save(user);
    }
    public List<User> findAll(String token) {
        if(token == null) {
            throw new RuntimeException("Token is null");
        }
        Token token1 = tokenRepository.findByCode(token);
        if(token1 == null) {
            throw new RuntimeException("Token is not valid");
        }
        return userRepository.findAll().stream().filter(u -> u.getEnable() == 0).collect(Collectors.toList());
    }
    public Page<User> findAll(int page, int size) {
        return userRepository.findAll(org.springframework.data.domain.PageRequest.of(page, size));
    }
    public ResponseData login(User user)  {
       for(User u: userRepository.findAll()) {
           if(u.getUsername().equals(user.getUsername()) && bCryptPasswordEncoder.passwordEncoder().matches(user.getPassword(), u.getPassword())) {

               Token token = tokenRepository.findByUserId(u.getId());
               if(token == null) {
                   Token token1 = new Token();
                   token1.setUser(u);
                   token1.setEffectFrom(new Date());
                   token1.setEffectUntil(DateUtils.getTheLastDayOfTheMonth(DateUtils.getCurrentDate()));
                   token1.setCode(GenerateToken.generateNewToken(24));
                   tokenRepository.save(token1);
               }
               if(token != null) {
                          tokenRepository.delete(token);
                          Token token1 = new Token();
                          token1.setUser(u);
                          token1.setEffectFrom(new Date());
                          token1.setEffectUntil(DateUtils.getTheLastDayOfTheMonth(DateUtils.getCurrentDate()));
                          token1.setCode(GenerateToken.generateNewToken(20));
                          tokenRepository.save(token1);
               }
               return new ResponseData<>("Login success",tokenRepository.findByUserId(u.getId()).getCode(), u ,"Successfully",HttpStatus.OK.value());
           }
       }
         return new ResponseData<>("Login failed", null ,null, "Failed",HttpStatus.BAD_REQUEST.value());
    }
    public void deleteAllBatch(List<Long> ids){
        userRepository.deleteAllByIdInBatch(ids);
    }
    public ResponseData logout(String code) {
        Token token = tokenRepository.findByCode(code);
        if(token != null) {
            tokenRepository.delete(token);
            return new ResponseData<>("Logout success", null ,null, "Successfully",HttpStatus.OK.value());
        }
        return new ResponseData<>("Logout failed", null ,null, "Failed",HttpStatus.BAD_REQUEST.value());

    }
    public List<User> findByUsernameOrEmail(String username) {
        return userRepository.findByUsernameOrEmail(username);
    }
}
