package ru.itmo.wp.model.domain;

import ru.itmo.wp.model.service.UserService;

import java.io.Serializable;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Article implements Serializable {
    private long id;
    private long userId;
    private String title;
    private String text;
    private String userLogin;
    private Date creationTime;
    private boolean hidden;


    public Article() {
    }

    public Article(long userId, String title, String text, boolean hidden) {
        this.userId = userId;
        this.title = title;
        this.text = text;
        this.hidden = hidden;
    }


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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

    public boolean isHidden() {
        return hidden;
    }

    public void setHidden(boolean hidden) {
        this.hidden = hidden;
    }

    public String getUserLogin() {
        return userLogin;
    }

    public void setUserLogin(String userLogin) {
        this.userLogin = userLogin;
    }
}
