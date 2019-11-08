package ru.itmo.wp.web.page;

import ru.itmo.wp.model.domain.Event;
import ru.itmo.wp.model.domain.User;
import ru.itmo.wp.model.service.EventService;
import ru.itmo.wp.model.service.UserService;
import ru.itmo.wp.web.exception.RedirectException;

import javax.servlet.http.HttpServletRequest;

/** @noinspection unused*/
public class LogoutPage extends Page {
    private final UserService userService = new UserService();
    private final EventService eventService = new EventService();

    private void action(HttpServletRequest request) {
        User user = (User) request.getSession().getAttribute("user");

        Event event = new Event();
        event.setUserId(user.getId());
        event.setType(Event.TYPE.LOGOUT);
        eventService.add(event);

        request.getSession().removeAttribute("user");

        request.getSession().setAttribute("message", "Good bye. Hope to see you soon!");
        throw new RedirectException("/index");
    }
}
