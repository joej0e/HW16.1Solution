package mate.academy.internetshop.controller;

import mate.academy.internetshop.lib.Inject;
import mate.academy.internetshop.model.Item;
import mate.academy.internetshop.service.BucketService;
import mate.academy.internetshop.service.ItemService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class DeleteFromCartController extends HttpServlet {
    @Inject
    private static BucketService bucketService;
    @Inject
    private static ItemService itemService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        Item item = itemService.get(Long.parseLong(
                req.getParameter("item_id")));
        bucketService.deleteItem(Long.parseLong(
                req.getParameter("bucket_id")), item);
        resp.sendRedirect(req.getContextPath() + "/servlet/bucket");
    }
}



