package ru.itmo.wp.web.page;

import ru.itmo.wp.model.domain.Article;
import ru.itmo.wp.model.domain.User;
import ru.itmo.wp.model.exception.ValidationException;
import ru.itmo.wp.model.service.ArticleService;
import ru.itmo.wp.model.service.UserService;
import ru.itmo.wp.web.exception.RedirectException;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/** @noinspection unused*/
public class ArticlePage {
    private final ArticleService articleService = new ArticleService();

    private void action(HttpServletRequest request, Map<String, Object> view) {
        User user = (User) request.getSession().getAttribute("user");
        if (user == null) {
            request.getSession().setAttribute("message", "You must enter");
            throw new RedirectException("/index");
        }
    }

    private void createArticle(HttpServletRequest request, Map<String, Object> view) throws ValidationException {
        User user = (User) request.getSession().getAttribute("user");
        if (user == null) {
            request.getSession().setAttribute("message", "You're trying to cheat!");
            throw new RedirectException("/index");
        }
        String title = request.getParameter("title");
        boolean hidden = request.getParameter("hidden").equals("true");
        String text = request.getParameter("text");
        articleService.create(new Article(user.getId(), title, text, hidden));
        request.getSession().setAttribute("message", "Your article was created");

        throw new RedirectException("/index");
    }
}
