package com.company.model;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Date;

public class InfoMessage {
    private String userAgent;
    private String creationTime;
    private String nowTime;
    public void setMessage(HttpServletRequest request) {
        HttpSession session = request.getSession();
        userAgent="User-Agent: " + request.getHeader("User-Agent");
        creationTime ="Creation-Time: " + new Date(session.getCreationTime());
        nowTime ="Now-Time: " + new Date().toString();
    }

    public String getUserAgent() {
        return userAgent;
    }

    public String getCreationTime() {
        return creationTime;
    }

    public String getNowTime() {
        return nowTime;
    }
}
