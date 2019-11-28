package ru.itmo.wp.web.page;

import ru.itmo.wp.model.domain.User;
import ru.itmo.wp.model.exception.ValidationException;
import ru.itmo.wp.model.service.ArticleService;
import ru.itmo.wp.web.exception.RedirectException;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

public class MyArticlePage {
    private ArticleService articleService = new ArticleService();

    private void action(HttpServletRequest request, Map<String, Object> view) {
        User user = (User) request.getSession().getAttribute("user");
        if (user == null) {
            request.getSession().setAttribute("message", "You must enter");
            throw new RedirectException("/index");
        }
        view.put("myArticles", articleService.findMyArticles(user.getId()));
    }


    private void setShown(HttpServletRequest request, Map<String, Object> view) throws ValidationException {
        long articleId = getArticleId(request);
        articleService.updateHidden(articleId, false);
    }

    private void setHidden(HttpServletRequest request, Map<String, Object> view) throws ValidationException {
        long articleId = getArticleId(request);
        articleService.updateHidden(articleId, true);
    }

    private long getArticleId(HttpServletRequest request) {
        User user = (User) request.getSession().getAttribute("user");
        if (user == null) {
            request.getSession().setAttribute("message", "You're trying to cheat!");
            throw new RedirectException("/index");
        }
        int articleId = Integer.parseInt(request.getParameter("articleId"));
        if (user.getId() != articleService.find(articleId).getUserId()) {
            request.getSession().setAttribute("message", "You can't change another person's articles");
            throw new RedirectException("/index");
        }
        return articleId;
    }
}
