package utils;

import javax.servlet.http.Cookie;

/**
 * Cookie���ҹ�����
 *
 */
public class CookieUtils {
	
	public static Cookie findCookie(Cookie[] cookies, String name) {
		if(cookies==null) {
			//û��Я��Cookie
			return null;
		} else {  //��Cookie
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
