package ru.itmo.wp.web.page;

import ru.itmo.wp.model.domain.User;
import ru.itmo.wp.model.domain.Talk;
import ru.itmo.wp.model.repository.TalkRepository;
import ru.itmo.wp.model.repository.impl.TalkRepositoryImpl;
import ru.itmo.wp.model.service.UserService;
import ru.itmo.wp.web.exception.RedirectException;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class TalksPage extends Page {
    private final UserService userService = new UserService();
    private final TalkRepository talkRepository = new TalkRepositoryImpl();

    private void action(Map<String, Object> view) {
        User user = getUser();
        if (user == null) {
            setMessage("You must enter to talk to other people!");
            throw new RedirectException("/index");
        }
        view.put("users", userService.findAll());
        view.put("talks", talkRepository.findByUserId(user.getId()));
    }

    private void sendMessage(HttpServletRequest request) {
        String targetUserLogin= request.getParameter("targetUserLogin");

        Talk talk = new Talk();
        talk.setSourceUserId(getUser().getId());
        talk.setTargetUserId(userService.findByLogin(targetUserLogin).getId());
        talk.setText(request.getParameter("text"));

        talkRepository.save(talk);
        throw new RedirectException("/talks");
    }
}
