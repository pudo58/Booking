package com.insmart.app.utils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CookieUtils {
    public static Cookie createCookie(String name, String value, int maxAge, HttpServletResponse res) {
        Cookie cookie = new Cookie(name, value);
        cookie.setPath("/");
        cookie.setMaxAge(maxAge);
        res.addCookie(cookie);
        return cookie;
    }
    public static Cookie getCookies(String name, HttpServletRequest req) {
        Cookie[]cookie = req.getCookies();
        if(cookie != null) {
            for(Cookie x : cookie) {
                if(x.getName().equalsIgnoreCase(name)) {
                    return x;
                }
            }
        }
        return null;
    }
}
