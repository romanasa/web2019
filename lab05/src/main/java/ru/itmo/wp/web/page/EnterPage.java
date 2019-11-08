package ru.itmo.wp.web.page;

import ru.itmo.wp.model.domain.Event;
import ru.itmo.wp.model.domain.User;
import ru.itmo.wp.model.exception.ValidationException;
import ru.itmo.wp.model.service.EventService;
import ru.itmo.wp.model.service.UserService;
import ru.itmo.wp.web.exception.RedirectException;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/** @noinspection unused*/
public class EnterPage extends Page {
    private final UserService userService = new UserService();
    private final EventService eventService = new EventService();

    private void action() {
        // No operations.
    }

    private void enter(HttpServletRequest request, Map<String, Object> view) throws ValidationException {
        String emailOrLogin = request.getParameter("emailOrLogin");
        String password = request.getParameter("password");

        User user;
        if (emailOrLogin.contains("@")) {
            user = userService.validateAndFindByEmailAndPassword(emailOrLogin, password);
        } else {
            user = userService.validateAndFindByLoginAndPassword(emailOrLogin, password);
        }

        Event event = new Event();
        event.setUserId(user.getId());
        event.setType(Event.TYPE.ENTER);
        eventService.add(event);

        request.getSession().setAttribute("user", user);
        request.getSession().setAttribute("message", "Hello, " + user.getLogin());

        throw new RedirectException("/index");
    }
}
