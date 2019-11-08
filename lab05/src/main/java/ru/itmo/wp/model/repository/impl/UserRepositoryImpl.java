package ru.itmo.wp.model.repository.impl;

import ru.itmo.wp.model.domain.User;
import ru.itmo.wp.model.repository.UserRepository;

import java.sql.*;
import java.util.List;

public class UserRepositoryImpl extends BasicRepositoryImpl<User> implements UserRepository {

    @Override
    public User find(long id) {
        return find("SELECT * FROM User WHERE id=?", id);
    }

    @Override
    public User findByLogin(String login) {
        return find("SELECT * FROM User WHERE login=?", login);
    }

    @Override
    public User findByEmail(String email) {
        return find("SELECT * FROM User WHERE email=?", email);
    }

    @Override
    public User findByLoginAndPasswordSha(String login, String passwordSha) {
        return find("SELECT * FROM User WHERE login=? AND passwordSha=?", login, passwordSha);
    }

    @Override
    public User findByEmailAndPasswordSha(String email, String passwordSha) {
        return find("SELECT * FROM User WHERE email=? AND passwordSha=?", email, passwordSha);
    }

    @Override
    public List<User> findAll() {
        return findAll("SELECT * FROM User ORDER BY id DESC");
    }

    @Override
    public int findCount() {
        return findAll().size();
    }

    @Override
    public User toType(ResultSetMetaData metaData, ResultSet resultSet) throws SQLException {
        if (!resultSet.next()) {
            return null;
        }

        User user = new User();
        for (int i = 1; i <= metaData.getColumnCount(); i++) {
            switch (metaData.getColumnName(i)) {
                case "id":
                    user.setId(resultSet.getLong(i));
                    break;
                case "login":
                    user.setLogin(resultSet.getString(i));
                    break;
                case "email":
                    user.setEmail(resultSet.getString(i));
                    break;
                case "creationTime":
                    user.setCreationTime(resultSet.getTimestamp(i));
                    break;
                default:
                    // No operations.
            }
        }

        return user;
    }

    @Override
    void setIdAndCreationTime(User user, ResultSet generatedKeys) throws SQLException {
        user.setId(generatedKeys.getLong(1));
        user.setCreationTime(find(user.getId()).getCreationTime());
    }

    @Override
    public void save(User user, String passwordSha) {
        save("INSERT INTO `User` (`login`, `email`, `passwordSha`, `creationTime`) VALUES (?, ?, ?, NOW())",
                user, user.getLogin(), user.getEmail(), passwordSha);
    }
}
