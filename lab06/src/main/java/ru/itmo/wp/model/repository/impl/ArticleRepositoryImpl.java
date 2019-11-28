package ru.itmo.wp.model.repository.impl;

import ru.itmo.wp.model.database.DatabaseUtils;
import ru.itmo.wp.model.domain.Article;
import ru.itmo.wp.model.domain.User;
import ru.itmo.wp.model.exception.RepositoryException;
import ru.itmo.wp.model.exception.ValidationException;
import ru.itmo.wp.model.repository.ArticleRepository;
import ru.itmo.wp.model.repository.UserRepository;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ArticleRepositoryImpl implements ArticleRepository {
    private final DataSource DATA_SOURCE = DatabaseUtils.getDataSource();

    @Override
    public Article find(long id) {
        try (Connection connection = DATA_SOURCE.getConnection()) {
            try (PreparedStatement statement = connection.prepareStatement("SELECT * FROM Article WHERE id=?")) {
                statement.setLong(1, id);
                try (ResultSet resultSet = statement.executeQuery()) {
                    return toArticle(statement.getMetaData(), resultSet);
                }
            }
        } catch (SQLException e) {
            throw new RepositoryException("Can't find Article.", e);
        }
    }

    @Override
    public List<Article> findAll() {
        List<Article> articles = new ArrayList<>();
        try (Connection connection = DATA_SOURCE.getConnection()) {
            try (PreparedStatement statement = connection.prepareStatement("SELECT * FROM Article ORDER BY id DESC")) {
                try (ResultSet resultSet = statement.executeQuery()) {
                    Article article;
                    while ((article = toArticle(statement.getMetaData(), resultSet)) != null) {
                        articles.add(article);
                    }
                }
            }
        } catch (SQLException e) {
            throw new RepositoryException("Can't find Article.", e);
        }
        return articles;
    }

    @Override
    public List<Article> findAllShown() {
        List<Article> articles = new ArrayList<>();
        try (Connection connection = DATA_SOURCE.getConnection()) {
            try (PreparedStatement statement = connection.prepareStatement("SELECT * FROM Article WHERE hidden=false")) {
                try (ResultSet resultSet = statement.executeQuery()) {
                    Article article;
                    while ((article = toArticle(statement.getMetaData(), resultSet)) != null) {
                        articles.add(article);
                    }
                }
            }
        } catch (SQLException e) {
            throw new RepositoryException("Can't find Article.", e);
        }
        Collections.reverse(articles);
        return articles;
    }

    @Override
    public List<Article> findAllByUserId(long userId) {
        List<Article> articles = new ArrayList<>();
        try (Connection connection = DATA_SOURCE.getConnection()) {
            try (PreparedStatement statement = connection.prepareStatement("SELECT * FROM Article WHERE userId=?")) {
                statement.setLong(1, userId);
                try (ResultSet resultSet = statement.executeQuery()) {Article article;
                    while ((article = toArticle(statement.getMetaData(), resultSet)) != null) {
                        articles.add(article);
                    }
                }
            }
        } catch (SQLException e) {
            throw new RepositoryException("Can't find Article.", e);
        }
        return articles;

    }


    @Override
    public void update(long id, boolean hidden) throws ValidationException {
        Article article = find(id);
        if (article == null) {
            throw new ValidationException("Invalid article id");
        }
        try (Connection connection = DATA_SOURCE.getConnection()) {
            try (PreparedStatement statement = connection.prepareStatement("UPDATE Article SET hidden=? Where id=?")) {
                statement.setBoolean(1, hidden);
                statement.setLong(2, id);
                if (statement.executeUpdate() != 1) {
                    throw new RepositoryException("Can't updateHidden Article.");
                }
            }
        } catch (SQLException e) {
            throw new RepositoryException("Can't updateHidden Article.", e);
        }
    }


    @Override
    public void save(Article article) {
        try (Connection connection = DATA_SOURCE.getConnection()) {
            try (PreparedStatement statement = connection.prepareStatement("INSERT INTO `Article` (`userId`, `title`, `text`, `hidden`, `creationTime`) VALUES (?, ?, ?, ?, NOW())", Statement.RETURN_GENERATED_KEYS)) {
                statement.setLong(1, article.getUserId());
                statement.setString(2, article.getTitle());
                statement.setString(3, article.getText());
                statement.setBoolean(4, article.isHidden());
                if (statement.executeUpdate() != 1) {
                    throw new RepositoryException("Can't save Article.");
                } else {
                    ResultSet generatedKeys = statement.getGeneratedKeys();
                    if (generatedKeys.next()) {
                        article.setId(generatedKeys.getLong(1));
                        article.setCreationTime(find(article.getId()).getCreationTime());
                    } else {
                        throw new RepositoryException("Can't save Article [no autogenerated fields].");
                    }
                }
            }
        } catch (SQLException e) {
            throw new RepositoryException("Can't save Article.", e);
        }
    }


    private Article toArticle(ResultSetMetaData metaData, ResultSet resultSet) throws SQLException {
        if (!resultSet.next()) {
            return null;
        }

        Article article = new Article();
        for (int i = 1; i <= metaData.getColumnCount(); i++) {
            switch (metaData.getColumnName(i)) {
                case "id":
                    article.setId(resultSet.getLong(i));
                    break;
                case "userId":
                    article.setUserId(resultSet.getLong(i));
                    break;
                case "title":
                    article.setTitle(resultSet.getString(i));
                    break;
                case "text":
                    article.setText(resultSet.getString(i));
                    break;
                case "hidden":
                    article.setHidden(resultSet.getBoolean(i));
                    break;
                case "creationTime":
                    article.setCreationTime(resultSet.getTimestamp(i));
                    break;
                case "userLogin":
                    article.setUserLogin(resultSet.getString(i));
                    break;
                default:
                    // No operations.
            }
        }

        return article;
    }

}
