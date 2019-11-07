package ru.itmo.wp.model.repository.impl;

import ru.itmo.wp.model.domain.Talk;
import ru.itmo.wp.model.repository.TalkRepository;

import java.sql.*;
import java.util.List;

public class TalkRepositoryImpl extends BasicRepositoryImpl<Talk> implements TalkRepository {

    private Talk findById(long id) {
        return find("SELECT * FROM Talk WHERE id=?", id);
    }

    @Override
    public List<Talk> findAll() {
        return findAll("SELECT * FROM Talk ORDER BY id DESC");
    }

    @Override
    public List<Talk> findByUserId(long id) {
        return findAll("SELECT * FROM Talk WHERE sourceUserId=? OR targetUserId=?", id, id);
    }

    @Override
    Talk toType(ResultSetMetaData metaData, ResultSet resultSet) throws SQLException {
        if (!resultSet.next()) {
            return null;
        }
        Talk talk = new Talk();
        for (int i = 1; i <= metaData.getColumnCount(); i++) {
            switch (metaData.getColumnName(i)) {
                case "id":
                    talk.setId(resultSet.getLong(i));
                    break;
                case "sourceUserId":
                    talk.setSourceUserId(resultSet.getLong(i));
                    break;
                case "targetUserId":
                    talk.setTargetUserId(resultSet.getLong(i));
                    break;
                case "text":
                    talk.setText(resultSet.getString(i));
                    break;
                case "creationTime":
                    talk.setCreationTime(resultSet.getTimestamp(i));
                    break;
                default:
                    // No operations.
            }
        }
        return talk;
    }

    @Override
    void setIdAndCreationTime(Talk talk, ResultSet generatedKeys) throws SQLException {
        talk.setId(generatedKeys.getLong(1));
        talk.setCreationTime(findById(talk.getId()).getCreationTime());
    }

    @Override
    public void save(Talk talk) {
        save("INSERT INTO `Talk` (`sourceUserId`, `targetUserId`, `text`, `creationTime`) VALUES (?, ?, ?, NOW())",
                talk, talk.getSourceUserId(), talk.getTargetUserId(), talk.getText());
    }
}
