package ru.itmo.wp.model.repository.impl;

import ru.itmo.wp.model.domain.Event;
import ru.itmo.wp.model.repository.EventRepository;

import java.sql.*;

public class EventRepositoryImpl extends BasicRepositoryImpl<Event> implements EventRepository {
    @Override
    public Event toType(ResultSetMetaData metaData, ResultSet resultSet) throws SQLException {
        if (!resultSet.next()) {
            return null;
        }

        Event event = new Event();
        for (int i = 1; i <= metaData.getColumnCount(); i++) {
            switch (metaData.getColumnName(i)) {
                case "id":
                    event.setId(resultSet.getLong(i));
                    break;
                case "userId":
                    event.setUserId(resultSet.getLong(i));
                    break;
                case "creationTime":
                    event.setCreationTime(resultSet.getTimestamp(i));
                    break;
                case "type":
                    event.setType(Event.TYPE.valueOf(resultSet.getString(i)));
                default:
                    // No operations.
            }
        }
        return event;
    }

    public Event findById(long id) {
        return find("SELECT * FROM Event WHERE id=?", id);
    }

    @Override
    void setIdAndCreationTime(Event event, ResultSet generatedKeys) throws SQLException {
        event.setId(generatedKeys.getLong(1));
        event.setCreationTime(findById(event.getId()).getCreationTime());
    }

    @Override
    public void save(Event event) {
        save("INSERT INTO `Event` (`userId`, `type`, `creationTime`) VALUES (?, ?, NOW())",
                event, event.getUserId(), event.getType().name());
    }
}
