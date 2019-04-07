package utils;

import javax.servlet.http.Cookie;

/**
 * Cookie查找工具类
 *
 */
public class CookieUtils {
	
	public static Cookie findCookie(Cookie[] cookies, String name) {
		if(cookies==null) {
			//没有携带Cookie
			return null;
		} else {  //有Cookie
			for(Cookie c : cookies) {
				if(c.getName().equals(name)) {
					return c;
				}
			}
			return null;
		}
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
	}

}
