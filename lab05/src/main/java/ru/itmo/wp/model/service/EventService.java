package ru.itmo.wp.model.service;

import ru.itmo.wp.model.domain.Event;
import ru.itmo.wp.model.domain.Talk;
import ru.itmo.wp.model.repository.EventRepository;
import ru.itmo.wp.model.repository.TalkRepository;
import ru.itmo.wp.model.repository.impl.EventRepositoryImpl;
import ru.itmo.wp.model.repository.impl.TalkRepositoryImpl;

/** @noinspection UnstableApiUsage*/
public class EventService {
    private final EventRepository eventRepository = new EventRepositoryImpl();

    public void add(Event event) {
        eventRepository.save(event);
    }
}
