package mate.academy.internetshop.controller;

import mate.academy.internetshop.lib.Inject;
import mate.academy.internetshop.model.Bucket;
import mate.academy.internetshop.model.Item;
import mate.academy.internetshop.service.BucketService;
import mate.academy.internetshop.service.ItemService;
import mate.academy.internetshop.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AddToCartController extends HttpServlet {
    @Inject
    private static ItemService itemService;
    @Inject
    private static UserService userService;
    @Inject
    private static BucketService bucketService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        Long userId = (Long) req.getSession(true).getAttribute("userId");
        Bucket bucket = bucketService
                .get(userService.get(userId).getBucketId());
        Item item = itemService
                .get(Long.parseLong(req.getParameter("item_id")));
        bucketService.addItem(bucket, item);
        resp.sendRedirect(req.getContextPath() + "/shop");
    }
}

