package com.insmart.app.filter;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.insmart.app.model.User;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.bind.annotation.CrossOrigin;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

@CrossOrigin(origins = "*")
@Scope(value = "session", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class JWTLoginFilter extends AbstractAuthenticationProcessingFilter {
    public static HttpSession session;
    public JWTLoginFilter(String url, AuthenticationManager authManager) {
        super(new AntPathRequestMatcher(url));
        setAuthenticationManager(authManager);
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException, IOException {
        User user = new User();
        Scanner sc = new Scanner(request.getReader());
        String json =  "";
        while(sc.hasNext()){
            json += sc.nextLine();
        }
        ObjectMapper mapper = new ObjectMapper();
        JsonNode rootNode = mapper.readTree(json);
        user.setUsername(rootNode.path("username").asText());
        user.setPassword(rootNode.path("password").asText());
        if(user.getUsername() == null || user.getPassword() == null) {
            throw new RuntimeException("Username or password is empty");
        }
       Authentication authentication = getAuthenticationManager().authenticate(
               new UsernamePasswordAuthenticationToken(
                       user.getUsername(),
                       user.getPassword(),
                       Collections.emptyList()
               )
       );
        return authentication;
    }
    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException failed) throws IOException, ServletException {
        Gson gson = new Gson();
        Map<String, String> map = new HashMap<>();
        map.put("status", HttpStatus.UNAUTHORIZED.value() + "");
        map.put("message", "Username or password is incorrect");
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.getWriter().write(gson.toJson(map));
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
        TokenAuthentication.addAuthentication(response, authResult.getName());
        Gson gson = new Gson();
        Map map = new HashMap();
        String token = response.getHeader(TokenAuthentication.HEADER_STRING).substring(7);
        map.put("status", HttpStatus.OK.value() + "");
        map.put("token", token);
        map.put("role", authResult.getAuthorities());
        gson.toJson(map, response.getWriter());
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.setStatus(HttpServletResponse.SC_OK);
        response.setHeader("Authorization", token);
        session = request.getSession(true);
        session.setAttribute("token", token);
        session.setAttribute("username", authResult.getName());
    }
    //get token with username



}