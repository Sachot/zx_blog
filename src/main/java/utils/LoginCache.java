package utils;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;


public class LoginCache {
	//��private���ι��췽��������������ģʽ��ȷ������һ��ʵ�������ⲿ���ܱ�ʵ����Ϊ�������
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
