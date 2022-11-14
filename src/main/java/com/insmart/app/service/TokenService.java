package com.insmart.app.service;

import com.insmart.app.model.Token;
import com.insmart.app.repository.TokenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

@Service
public class TokenService {
    @Autowired
    private TokenRepository tokenRepository;
    public Token save(Token token) {
        token.setStatus(1);
        return tokenRepository.save(token);

    }
    public Token findById(Long id) {
        return tokenRepository.findById(id).orElseThrow();
    }
    public void deleteById(Long id) {
        tokenRepository.deleteById(id);
    }
    public java.util.List<Token> findAll() {
        return tokenRepository.findAll();
    }
    public Token update(Token token) {
        return tokenRepository.save(token);
    }
    public Page<Token> findAll(int page, int size) {
        return tokenRepository.findAll(org.springframework.data.domain.PageRequest.of(page, size));
    }
    public Token findByCode(String code) {
        return tokenRepository.findByCode(code);
    }
}
