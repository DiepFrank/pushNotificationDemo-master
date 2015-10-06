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

    public KhoaHoc(String subject, String teacher, String grade, String start, String end, String day) {
        this.subject = subject;
        this.teacher = teacher;
        this.grade = grade;
        this.end = end;
        this.start = start;
        this.day = day;
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

    public void setStart(String start) {
        this.start = start;
    }

    public void setEnd(String end) {
        this.end = end;
    }

    public void setDay(String day) {
        this.day = day;
    }
}
