package utils;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;


public class LoginCache {
	//用private修饰构造方法————单例模式，确保他是一个实例，在外部不能被实例化为多个对象
		private LoginCache() {
			
		}
		
		private static LoginCache instance = new LoginCache();
		
		private Map<String, String> loginUserSession = new HashMap<String, String>();
		private Map<String, HttpSession> loginSession = new HashMap<String, HttpSession>();
		
		public static LoginCache getInstance() {
			return instance;
		}
		
		public void setSessionIdByUsername(String username, String sessionId) {
			loginUserSession.put(username, sessionId);
		}
		
		public void setSessionBySessionId(String sessionId, HttpSession session) {
			loginSession.put(sessionId, session);
		}
		
		public String getSessionIdByUsername(String username) {
			return loginUserSession.get(username);
		}
		
		public HttpSession getSessionBySessionId(String SessionId) {
			return loginSession.get(SessionId);
		}
}
