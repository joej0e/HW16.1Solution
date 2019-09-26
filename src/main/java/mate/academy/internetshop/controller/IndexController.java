package mate.academy.internetshop.controller;

import mate.academy.internetshop.lib.Inject;
import mate.academy.internetshop.model.Item;
import mate.academy.internetshop.service.ItemService;
import org.apache.log4j.Logger;

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
        Item item = itemService.create(new Item("zebra", 45.));
        Item itemFromDB = itemService.get(item.getId());
        Logger logger = Logger.getLogger(IndexController.class);
        logger.debug("Item from DB = " + item.toString());
        itemFromDB.setPrice(111000.);
        Item updatedItem = itemService.update(itemFromDB);
        logger.debug("Updated item = " + updatedItem.toString());
        itemService.delete(updatedItem.getId());
        req.getRequestDispatcher("/WEB-INF/views/index.jsp").forward(req, resp);
    }
}

