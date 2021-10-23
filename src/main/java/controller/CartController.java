package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.CartDao;
import entity.Order;
import entity.User;

@WebServlet("/servlet/cart")
public class CartController extends HttpServlet {
	private CartDao cartDao = new CartDao();
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String type = req.getParameter("type");
		type = (type == null) ? "" : type;
		RequestDispatcher rd = null;
		HttpSession session = req.getSession(false);
		switch (type) {
			case "1": // 查看購物車
				rd = req.getRequestDispatcher("/form/cart.jsp");
				req.setAttribute("products", cartDao.queryProducts());
				break;
			case "2": // 根據 user.id 查看訂單紀錄
				rd = req.getRequestDispatcher("/form/record.jsp");
				User user = (User)session.getAttribute("user");
				List<Order> orders = cartDao.queryOrdersByUserId(user.getId());
				req.setAttribute("orders", orders);
				req.setAttribute("products", cartDao.queryProducts());
				break;
			default:
				rd = req.getRequestDispatcher("/form/index.jsp");
				req.setAttribute("products", cartDao.queryProducts());
		}
		if(rd != null) {
			rd.forward(req, resp);
		}
	}

	// 加入到購物車
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 因為已經有 session user 物件, 所以不需要再判斷是否表單有傳 user id
		String[] data = req.getParameterValues("data");
		// 將資料存入到 Session 中 (購物車)
		HttpSession session = req.getSession();
		session.setAttribute("data", data);
		resp.sendRedirect(getServletContext().getContextPath() + "/servlet/cart?type=1");
	}
	
	
	
}
