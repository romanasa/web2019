package ru.itmo.wp.model.service;

import com.google.common.base.Strings;
import com.google.common.hash.Hashing;
import ru.itmo.wp.model.domain.Article;
import ru.itmo.wp.model.domain.User;
import ru.itmo.wp.model.exception.ValidationException;
import ru.itmo.wp.model.repository.ArticleRepository;
import ru.itmo.wp.model.repository.UserRepository;
import ru.itmo.wp.model.repository.impl.ArticleRepositoryImpl;
import ru.itmo.wp.model.repository.impl.UserRepositoryImpl;

import java.nio.charset.StandardCharsets;
import java.util.List;

/** @noinspection UnstableApiUsage*/
public class ArticleService {
    private final ArticleRepository articleRepository = new ArticleRepositoryImpl();


    public void create(Article article) throws ValidationException {
        if (article.getTitle().length() < 5) {
            throw new ValidationException("Title must be at least 5 symbols");
        }
        if (article.getTitle().length() > 30) {
            throw new ValidationException("Title must be no more than 30 symbols");
        }
        if (article.getText().length() < 10) {
            throw new ValidationException("Text must be at least 10 symbols");
        }
        if (article.getText().length() > 256) {
            throw new ValidationException("Text must be no more than 256 symbols");
        }
        articleRepository.save(article);
    }

    public Article find(long id) {
        return articleRepository.find(id);
    }

    public List<Article> findAll() {
        return articleRepository.findAll();
    }

    public List<Article> findAllShown() {
        return articleRepository.findAllShown();
    }

    public List<Article> findMyArticles(long userId) {
        return articleRepository.findAllByUserId(userId);
    }

    public void updateHidden(long id, boolean hidden) throws ValidationException {
        articleRepository.update(id, hidden);
    }
}
