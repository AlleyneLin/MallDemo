package com.alleyne.mall.utils;

import javax.servlet.http.Cookie;

public class CookieUtils {
    public static Cookie getCookieByName(String name, Cookie[] cookies){
        if (cookies != null){
            for (Cookie ck:cookies){
                if (name.equals(ck.getName())){
                    return ck;
                }
            }
        }
        return null;
    }
}
