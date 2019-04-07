package listener;

import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;

import utils.LoginCache;



public class AdminSessionAtrListener implements HttpSessionAttributeListener {

	private static final String LOGIN_ADMIN = "admin";
	
	@Override
	public void attributeAdded(HttpSessionBindingEvent hsbe) {
		// TODO Auto-generated method stub
        String attrName = hsbe.getName();
		
		if(attrName.equals(LOGIN_ADMIN)) {
			HttpSession session1 = hsbe.getSession();
			String attrVal = (String)hsbe.getValue();
			String sessionId1 = session1.getId();
			
			//����Ƿ���ͬ���û�����session
			String sessionId2 = LoginCache.getInstance().getSessionIdByUsername(attrVal);
			if(sessionId2==null) {
				
			}else {
				HttpSession session2 = LoginCache.getInstance().getSessionBySessionId(sessionId2);
				//���ԭ��session���������
				session2.invalidate();
				
			}
			
			//��session��Ӧ��Ϣ�Ž�LoginCache��
			LoginCache.getInstance().setSessionIdByUsername(attrVal, sessionId1);
			LoginCache.getInstance().setSessionBySessionId(sessionId1, session1);
		}

	}

	@Override
	public void attributeRemoved(HttpSessionBindingEvent event) {
		// TODO Auto-generated method stub

	}

	@Override
	public void attributeReplaced(HttpSessionBindingEvent event) {
		// TODO Auto-generated method stub

	}

}
