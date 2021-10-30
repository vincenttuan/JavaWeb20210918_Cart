package filter;

import java.io.IOException;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.FilterChain;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.UserDao;
import entity.User;

@WebFilter(value = {"/servlet/cart", "/servlet/cart/submit"})
public class LoginFilter extends HttpFilter {
	private UserDao userDao = new UserDao();
	
	private Boolean ssoCheck(User user) throws ServletException {
		ServletContext context = getServletContext();
		if(context.getAttribute("users") == null) { // 首次加入
			Set<User> users = new LinkedHashSet<>();
			users.add(user);
			context.setAttribute("users", users);
			return true;
		}
		// 驗證是否已登入過 - 非首次加入
		Set<User> users = (LinkedHashSet)context.getAttribute("users");
		Boolean exist = users.stream().filter(u -> u.getName().equals(user.getName())).findAny().isPresent();
		if(exist) {
			return false;
		} else {
			users.add(user);
			context.setAttribute("users", users);
			return true;
		}
	}
	
	@Override
	protected void doFilter(HttpServletRequest req, HttpServletResponse res, FilterChain chain)
			throws IOException, ServletException {
		
		// 是否有 user 的 session 物件資料
		HttpSession session = req.getSession();
		if(session.getAttribute("user") == null) {
			// 是否有帶入登入資訊 ?
			String name = req.getParameter("name");
			String password = req.getParameter("password");
			// 1. 驗證 username / password
			if(name != null && password != null) {
				
				User user = userDao.loginCheck(name, password);
				if(user != null) { // login success
					
					// 2. 確認驗證碼
					String authCode_session = session.getAttribute("authCode").toString();
					String authCode = req.getParameter("authCode");
					if(authCode.equals(authCode_session)) { // authCode 驗證通過
						
						// 3. SSO 驗證
						if(ssoCheck(user)) { // SSO 驗證通過
							session.setAttribute("user", user);
							chain.doFilter(req, res);
						} else { // SSO 驗證不通過
							throw new ServletException("此帳號已經登入過");
						}
						
					} else { // authCode 驗證不通過
						throw new ServletException("驗證碼錯誤");
					}
					return;
				}
			}
			RequestDispatcher rd = req.getRequestDispatcher("/form/login.jsp");
			rd.forward(req, res);
			
		} else { // by pass
			chain.doFilter(req, res);
		}
		
	}
	
	
	
}
