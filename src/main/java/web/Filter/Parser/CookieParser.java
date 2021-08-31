package web.Filter.Parser;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

public class CookieParser {
    public static String getToken(HttpServletRequest httpRequest){
        Cookie[] cookies = httpRequest.getCookies();
        if(cookies != null){
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("user-token")) {
                    return cookie.getValue();
                }
            }
        }
        return null;
    }
}
