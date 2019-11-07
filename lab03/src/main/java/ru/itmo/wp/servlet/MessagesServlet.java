package ru.itmo.wp.servlet;


import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

public class MessagesServlet extends HttpServlet {

    class Message {
        private String user, text;
        Message(String user, String text) {
            this.user = user;
            this.text = text;
        }
    }

    private ArrayList<Message> messages = new ArrayList<>();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse responce) throws IOException {
        responce.setContentType("application/json");
        String uri = request.getRequestURI();
        if ("/message/auth".equals(uri)) {
            auth(request, responce);
        } else if ("/message/add".equals(uri)) {
            add(request, responce);
        } else if ("/message/findAll".equals(uri)) {
            findAll(request, responce);
        }
    }

    private void findAll(HttpServletRequest request, HttpServletResponse responce) throws IOException {

        String json = new Gson().toJson(messages);
        byte[] utf8JsonString = json.getBytes(StandardCharsets.UTF_8);
        responce.getOutputStream().write(utf8JsonString, 0, utf8JsonString.length);
        responce.getWriter().flush();
    }

    private void add(HttpServletRequest request, HttpServletResponse responce) {
        HttpSession session = request.getSession();
        String text = request.getParameter("text");
        String user = (String) session.getAttribute("user");
        messages.add(new Message(user, text));
    }

    private void auth(HttpServletRequest request, HttpServletResponse responce) throws IOException {
        HttpSession session = request.getSession();
        Object userInSession = session.getAttribute("user");
        String user;
        if (userInSession == null) {
            user = request.getParameter("user");
            if (user != null) {
                session.setAttribute("user", user);
            } else {
                user = "";
            }
        } else {
            user = userInSession.toString();
        }
        String json = new Gson().toJson(user);
        byte[] utf8JsonString = json.getBytes(StandardCharsets.UTF_8);
        responce.getOutputStream().write(utf8JsonString, 0, utf8JsonString.length);
        responce.getWriter().flush();
    }
}
