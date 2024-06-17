package cz.cvut.fel.dbs.db;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.sql.Date;
import java.sql.Time;

@Entity
@Table(name = "competition")
public class Competition{

    @Id
    @Column(name = "competitionid", nullable = false)
    private String competitionid;

    @Column(name = "type", nullable = false)
    private String type;

    @Column(name = "date", nullable = false)
    private Date date;

    @Column(name = "time", nullable = false)
    private Time time;

    public void setCompetitionid(String competitionid) {
        this.competitionid = competitionid;
    }

    public String getCompetitionid() {
        return competitionid;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Date getDate() {
        return date;
    }

    public void setTime(Time time) {
        this.time = time;
    }

    public Time getTime() {
        return time;
    }

    @Override
    public String toString() {
        return "Competition{" +
                "competitionid=" + competitionid + '\'' +
                "type=" + type + '\'' +
                "date=" + date + '\'' +
                "time=" + time + '\'' +
                '}';
    }
}
