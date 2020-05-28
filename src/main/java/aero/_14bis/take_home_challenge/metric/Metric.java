package aero._14bis.take_home_challenge.metric;

import java.util.Date;

public class Metric {
    private int value;
    private Date date;

    public Metric(int value, Date date){
        this.value = value;
        this.date = date;
    }

    public int getValue() {
        return value;
    }

    public Date getDate() {
        return date;
    }

}
