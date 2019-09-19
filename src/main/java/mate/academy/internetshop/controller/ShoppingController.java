package mate.academy.internetshop.controller;

import mate.academy.internetshop.lib.Inject;
import mate.academy.internetshop.model.Item;
import mate.academy.internetshop.model.User;
import mate.academy.internetshop.service.ItemService;
import mate.academy.internetshop.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class ShoppingController extends HttpServlet {
    private static final Long SESSION_USER_ID = 0L;
    @Inject
    private static UserService userService;
    @Inject
    private static ItemService itemService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        if (userService.getAll().size() == 0) {
            req.getRequestDispatcher("WEB-INF/views/index.jsp").forward(req, resp);
        }
        User user = userService.get(SESSION_USER_ID);
        List<Item> items = itemService.getAll();
        req.setAttribute("user", user);
        req.setAttribute("items", items);
        req.getRequestDispatcher("WEB-INF/views/shop.jsp").forward(req, resp);
    }
}