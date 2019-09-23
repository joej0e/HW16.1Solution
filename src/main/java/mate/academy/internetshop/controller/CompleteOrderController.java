package mate.academy.internetshop.controller;

import mate.academy.internetshop.lib.Inject;
import mate.academy.internetshop.model.Bucket;
import mate.academy.internetshop.model.Order;
import mate.academy.internetshop.model.User;
import mate.academy.internetshop.service.BucketService;
import mate.academy.internetshop.service.OrderService;
import mate.academy.internetshop.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CompleteOrderController extends HttpServlet {
    @Inject
    private static BucketService bucketService;
    @Inject
    private static OrderService orderService;
    @Inject
    private static UserService userService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        Bucket bucket = bucketService.get(Long.parseLong(
                req.getParameter("bucket_id")));
        if (bucket.getItems().size() != 0) {
            Long userId = (Long) req.getSession(true).getAttribute("userId");
            User user = userService.get(userId);
            Order order = new Order(bucket.getItems(), user);
            orderService.add(order);
            user.getOrders().add(order);
            bucketService.clear(bucket);
        }
        resp.sendRedirect(req.getContextPath() + "/servlet/showAllOrders");
    }
}

