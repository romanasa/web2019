package ru.itmo.wp.model.domain;

import ru.itmo.wp.model.repository.UserRepository;
import ru.itmo.wp.model.repository.impl.UserRepositoryImpl;

import java.io.Serializable;
import java.util.Date;


public class Talk implements Serializable {
    private long id;
    private long sourceUserId;
    private long targetUserId;
    private String text;
    private Date creationTime;

    private UserRepository userRepository = new UserRepositoryImpl();

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getSourceUserId() {
        return sourceUserId;
    }

    public void setSourceUserId(long sourceUserId) {
        this.sourceUserId = sourceUserId;
    }

    public long getTargetUserId() {
        return targetUserId;
    }

    public void setTargetUserId(long targetUserId) {
        this.targetUserId = targetUserId;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Date getCreationTime() {
        return creationTime;
    }

    public void setCreationTime(Date creationTime) {
        this.creationTime = creationTime;
    }
    public String getSourceUserLogin() {
        return userRepository.find(sourceUserId).getLogin();
    }

    public String getTargetUserLogin() {
        return userRepository.find(targetUserId).getLogin();
    }
}
