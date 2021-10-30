package listener;

import java.util.LinkedHashSet;
import java.util.Set;

import javax.servlet.ServletContext;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import entity.User;

@WebListener
public class SSOSessionListener implements HttpSessionListener {

	@Override
	public void sessionCreated(HttpSessionEvent se) {
		System.out.println("有 session 物件被創建 ... " + se.getSession().getId());
	}

	@Override
	public void sessionDestroyed(HttpSessionEvent se) {
		System.out.println("有 session 物件被移除 ... " + se.getSession().getId());
		HttpSession session = se.getSession();
		ServletContext context = se.getSession().getServletContext();
		
		// SSO 移除此帳號
		if(session.getAttribute("user") != null && context.getAttribute("users") != null) {
			User user = (User)session.getAttribute("user");
			Set<User> users = (LinkedHashSet)context.getAttribute("users");
			System.out.println("SSOSessionListener SSO users 移除前: " + users);
			users.remove(user); // 記得在 User 物件要加入 equals() 與 hashCode()
			System.out.println("SSOSessionListener SSO users 移除後: " + users);
			context.setAttribute("users", users); // 回存
		}
	}
	
}
