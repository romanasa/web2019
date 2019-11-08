package ru.itmo.wp.web.page;

import ru.itmo.wp.model.domain.User;
import ru.itmo.wp.model.domain.Talk;
import ru.itmo.wp.model.exception.ValidationException;
import ru.itmo.wp.model.service.TalkService;
import ru.itmo.wp.model.service.UserService;
import ru.itmo.wp.web.exception.RedirectException;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

public class TalksPage extends Page {
    private final UserService userService = new UserService();
    private final TalkService talkService = new TalkService();

    private void action(Map<String, Object> view) {
        User user = getUser();
        if (user == null) {
            setMessage("You must enter to talk to other people!");
            throw new RedirectException("/index");
        }
        view.put("users", userService.findAll());
        view.put("talks", talkService.findByUserId(user.getId()));
    }

    private void sendMessage(HttpServletRequest request) throws ValidationException {
        String targetUserLogin= request.getParameter("targetUserLogin");

        String text = request.getParameter("text");
        if (text.length() > 1023) {
            throw new ValidationException("Text length must be less 1024");
        }

        Talk talk = new Talk();
        talk.setSourceUserId(getUser().getId());
        talk.setTargetUserId(userService.findByLogin(targetUserLogin).getId());
        talk.setText(text);

        talkService.save(talk);
        throw new RedirectException("/talks");
    }
}
