package ru.itmo.wp.web.page;

import ru.itmo.wp.model.domain.Event;
import ru.itmo.wp.model.domain.User;
import ru.itmo.wp.model.exception.ValidationException;
import ru.itmo.wp.model.repository.EventRepository;
import ru.itmo.wp.model.repository.impl.EventRepositoryImpl;
import ru.itmo.wp.model.service.UserService;
import ru.itmo.wp.web.exception.RedirectException;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/** @noinspection unused*/
public class LogoutPage extends Page {
    private final UserService userService = new UserService();
    private final EventRepository eventRepository = new EventRepositoryImpl();

    private void action(HttpServletRequest request) {
        User user = (User) request.getSession().getAttribute("user");

        Event event = new Event();
        event.setUserId(user.getId());
        event.setType(Event.TYPE.LOGOUT);
        eventRepository.save(event);

        request.getSession().removeAttribute("user");

        request.getSession().setAttribute("message", "Good bye. Hope to see you soon!");
        throw new RedirectException("/index");
    }
}
