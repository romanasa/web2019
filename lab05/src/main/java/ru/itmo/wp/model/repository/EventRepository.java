package ru.itmo.wp.model.repository;

import ru.itmo.wp.model.domain.Event;

import java.util.List;

public interface EventRepository {
    public Event findById(long id);
    void save(Event event);
}
