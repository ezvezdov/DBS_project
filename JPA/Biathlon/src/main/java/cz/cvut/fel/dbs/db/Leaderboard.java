package cz.cvut.fel.dbs.db;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name = "leaderboard")
public class Leaderboard{

    @Column(name = "place", nullable = false)
    private Integer place;

    @Column(name = "competition", nullable = false)
    private String competition;


    @Column(name = "biathlete", nullable = false)
    private String biathlete;

    @Id
    @Column(name = "recordid", nullable = false)
    private String recordid;

    @ManyToMany(mappedBy = "leaderboardRecords")
    private Collection<Biathlete> biathletes;

    public void setPlace(Integer place) {
        this.place = place;
    }

    public Integer getPlace() {
        return place;
    }

    public void setCompetition(String competition) {
        this.competition = competition;
    }

    public String getCompetition() {
        return competition;
    }

    public void setBiathlete(String biathlete) {
        this.biathlete = biathlete;
    }

    public String getBiathlete() {
        return biathlete;
    }

    public void setRecordid(String recordid) {
        this.recordid = recordid;
    }

    public String getRecordid() {
        return recordid;
    }

    public Collection<Biathlete> getBiathletes() {
        return biathletes;
    }

    public void setBiathletes(Collection<Biathlete> biathletes) {
        this.biathletes = biathletes;
    }

    @Override
    public String toString() {
        return "Leaderboard{" +
                "place=" + place + '\'' +
                "competition=" + competition + '\'' +
                "biathlete=" + biathlete + '\'' +
                "recordid=" + recordid + '\'' +
                '}';
    }
}
