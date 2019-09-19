package mate.academy.internetshop.controller;

import mate.academy.internetshop.lib.Inject;
import mate.academy.internetshop.model.User;
import mate.academy.internetshop.service.OrderService;
import mate.academy.internetshop.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class GetAllOrdersController extends HttpServlet {
    private static final Long SESSION_USER_ID = 0L;
    @Inject
    private static UserService userService;
    @Inject
    private static OrderService orderService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        User user = userService.get(SESSION_USER_ID);
        req.setAttribute("user", user);
        req.setAttribute("orders", user.getOrders());
        req.getRequestDispatcher("WEB-INF/views/orders.jsp").forward(req, resp);
    }
}