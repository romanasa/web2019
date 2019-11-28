package ru.itmo.wp.model.repository;

import ru.itmo.wp.model.domain.Article;
import ru.itmo.wp.model.domain.User;
import ru.itmo.wp.model.exception.ValidationException;

import java.util.List;

public interface ArticleRepository {
    Article find(long id);
    List<Article> findAll();
    List<Article> findAllShown();
    List<Article> findAllByUserId(long userId);
    void save(Article article);
    void update(long id, boolean hidden) throws ValidationException;
}
