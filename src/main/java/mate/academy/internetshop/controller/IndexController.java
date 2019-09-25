package mate.academy.internetshop.controller;

import mate.academy.internetshop.lib.Inject;
import mate.academy.internetshop.model.Item;
import mate.academy.internetshop.service.ItemService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class IndexController extends HttpServlet {

    @Inject
    private static ItemService itemService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        itemService.update(new Item(12L, "sobaka", 32.));
        itemService.create(new Item("zebra", 45.));
        itemService.get(13L);
        itemService.delete(9L);
        req.getRequestDispatcher("/WEB-INF/views/index.jsp").forward(req, resp);
    }
}

