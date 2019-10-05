package mate.academy.internetshop.controller;

import mate.academy.internetshop.lib.Inject;
import mate.academy.internetshop.model.Bucket;
import mate.academy.internetshop.model.Role;
import mate.academy.internetshop.model.User;
import mate.academy.internetshop.service.BucketService;
import mate.academy.internetshop.service.UserService;
import mate.academy.internetshop.util.HashUtil;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class RegisterController extends HttpServlet {
    @Inject
    private static BucketService bucketService;
    @Inject
    private static UserService userService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/views/register.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        User user = new User();
        user.setName(req.getParameter("name"));
        user.setSurname(req.getParameter("surname"));
        user.setLogin(req.getParameter("login"));
        String password = req.getParameter("psw");
        user.addRole(Role.of("USER"));
        byte[] salt = HashUtil.getSalt();
        user.setSalt(salt);
        String hashedPassword = HashUtil.hashPassword(password, salt);
        user.setPassword(hashedPassword);

        User newUser = userService.add(user);
        Bucket newBucket = new Bucket();
        newBucket.setUserId(user.getId());
        Bucket bucket = bucketService.create(newBucket);
        user.setBucketId(bucket.getId());
        HttpSession session = req.getSession();
        session.setAttribute("userId", user.getId());
        Cookie cookie = new Cookie("MATE", newUser.getToken());
        resp.addCookie(cookie);
        resp.sendRedirect(req.getContextPath() + "/shop");
    }
}

