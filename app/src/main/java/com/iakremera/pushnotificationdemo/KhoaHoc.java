package com.iakremera.pushnotificationdemo;

/**
 * Created by Diep_Chelsea on 04/10/2015.
 */
public class KhoaHoc {
    String subject;
    String teacher;
    String grade;
    String end;
    String start;
    String day;
    String total;
    String stt;
    String ca;

    public KhoaHoc(String subject, String teacher, String grade, String end, String day, String start, String total, String stt, String ca) {
        this.subject = subject;
        this.teacher = teacher;
        this.grade = grade;
        this.end = end;
        this.day = day;
        this.start = start;
        this.total = total;
        this.stt = stt;
        this.ca = ca;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public void setTeacher(String teacher) {
        this.teacher = teacher;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public void setEnd(String end) {
        this.end = end;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public void setStart(String start) {
        this.start = start;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public void setStt(String stt) {
        this.stt = stt;
    }

    public void setCa(String ca) {
        this.ca = ca;
    }
}