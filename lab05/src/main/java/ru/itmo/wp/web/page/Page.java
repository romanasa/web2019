package ru.itmo.wp.web.page;

import ru.itmo.wp.model.domain.User;
import ru.itmo.wp.model.service.UserService;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

class Page {
    private final UserService userService = new UserService();
    private HttpServletRequest request;

    private void before(HttpServletRequest request, Map<String, Object> view) {
        this.request = request;
        User user = getUser();
        if (user != null) {
            view.put("user", user);
        }
        view.put("userCount", userService.findCount());
    }

    private void after(HttpServletRequest request, Map<String, Object> view) {
        //No operations.
    }

    private void action() {
        //No operations.
    }

    void setMessage(String message) {
        request.getSession().setAttribute("message", message);
    }

    void setUser(User user) {
        request.getSession().setAttribute("user", user);
    }

    User getUser() {
        return (User) request.getSession().getAttribute("user");
    }
}
