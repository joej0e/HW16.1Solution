package mate.academy.internetshop.web.filters;


import mate.academy.internetshop.lib.Inject;
import mate.academy.internetshop.model.Role;
import mate.academy.internetshop.model.User;
import mate.academy.internetshop.service.RoleService;
import mate.academy.internetshop.service.UserService;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

public class AuthorizationFilter implements Filter {
    private static final String EMPTY_STRING = "";
    @Inject
    private static UserService userService;
    @Inject
    private static RoleService roleService;
    private Map<String, Role> protectedUrls = new HashMap<>();

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        protectedUrls.put("/servlet/users", Role.of("ADMIN"));
        protectedUrls.put("/servlet/showAllOrders", Role.of("USER"));
        protectedUrls.put("/servlet/deleteUser", Role.of("ADMIN"));
        protectedUrls.put("/servlet/bucket", Role.of("USER"));
        protectedUrls.put("/servlet/deleteFromBucket", Role.of("USER"));
        protectedUrls.put("/servlet/completeOrder", Role.of("USER"));
        protectedUrls.put("/user/addToBucket", Role.of("USER"));
        protectedUrls.put("/user/deleteOrder", Role.of("USER"));
    }

    @Override
    public void doFilter(
            ServletRequest servletRequest,
            ServletResponse servletResponse, FilterChain filterChain)
            throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse resp = (HttpServletResponse) servletResponse;

        Cookie[] cookies = req.getCookies();
        if (cookies == null) {
            processUnAuthenticated(req, resp);
            return;
        }

        String requestedUrl = req.getRequestURI()
                .replace(req.getContextPath(), EMPTY_STRING);
        Role roleName = protectedUrls.get(requestedUrl);
        if (roleName == null) {
            processAuthenticated(filterChain, req, resp);
            return;
        }

        String token = null;
        for (Cookie cookie : req.getCookies()) {
            if (cookie.getName().equals("MATE")) {
                token = cookie.getValue();
                break;
            }
        }

        if (token == null) {
            processUnAuthenticated(req, resp);
            return;
        } else {
            Optional<User> user = userService.getByToken(token);
            if (user.isPresent()) {
                if (verifyRole(user.get(), roleName)) {
                    processAuthenticated(filterChain, req, resp);
                    return;
                } else {
                    processDenied(req, resp);
                    return;
                }
            } else {
                processUnAuthenticated(req, resp);
                return;
            }
        }
    }

    private boolean verifyRole(User user, Role roleName) {
        Set<Role> roles = roleService.getRoles(user);
        for (Role role : roles) {
            if (role.getRoleName().name().equals(roleName.getRoleName().name())) {
                return true;
            }
        }
        return false;
    }

    private void processDenied(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/views/accessDenied.jsp").forward(req, resp);
    }

    private void processUnAuthenticated(
            HttpServletRequest req, HttpServletResponse resp)
            throws IOException {
        resp.sendRedirect(req.getContextPath() + "/login");
    }

    private void processAuthenticated(FilterChain chain,
                                      HttpServletRequest req, HttpServletResponse resp)
            throws IOException, ServletException {
        chain.doFilter(req, resp);
    }

}

