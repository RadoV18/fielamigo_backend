package com.fielamigo.app.FielAmigo.utils;

import java.util.Date;

public class DateCount {
    private Date date;
    private Integer count;

    public DateCount() {
    }

    public DateCount(Date date, Integer count) {
        this.date = date;
        this.count = count;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    @Override
    public String toString() {
        return "DateCount [date=" + date + ", count=" + count + "]";
    }

}
