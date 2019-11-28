package ru.itmo.wp.model.domain;

import java.io.Serializable;
import java.util.Date;

public class ArticleView implements Serializable {
    private Article article;
    private User user;

    public ArticleView(Article article, User user) {
        this.article = article;
        this.user = user;
    }

    public Article getArticle() {
        return article;
    }

    public void setArticle(Article article) {
        this.article = article;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
