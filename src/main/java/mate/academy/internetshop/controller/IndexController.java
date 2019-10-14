package mate.academy.internetshop.controller;

import mate.academy.internetshop.lib.Inject;
import mate.academy.internetshop.service.BucketService;
import mate.academy.internetshop.service.ItemService;
import mate.academy.internetshop.service.OrderService;
import mate.academy.internetshop.service.RoleService;
import mate.academy.internetshop.service.UserService;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class IndexController extends HttpServlet {

    @Inject
    private static ItemService itemService;
    @Inject
    private static OrderService orderService;
    @Inject
    private static UserService userService;
    @Inject
    private static BucketService bucketService;
    @Inject
    private static RoleService roleService;
    private Logger logger = Logger.getLogger(IndexController.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        bucketService.addItem(44L, 12L);
        req.getRequestDispatcher("/WEB-INF/views/index.jsp").forward(req, resp);
    }
}

