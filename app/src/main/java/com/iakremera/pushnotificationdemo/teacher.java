package com.iakremera.pushnotificationdemo;

/**
 * Created by Diep_Chelsea on 09/10/2015.
 */
public class teacher {
    String name;
    String subject;
    String birthday;
    String company;
    int avatar;
    String phone;
    public teacher(String name, String subject, String birthday, String company, int avatar,String phone) {
        this.name = name;
        this.subject = subject;
        this.birthday = birthday;
        this.company = company;
        this.avatar = avatar;
        this.phone = phone;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public void setAvatar(int avatar) {
        this.avatar = avatar;
    }

    public void setCompany(String company) {
        this.company = company;
    }
}
