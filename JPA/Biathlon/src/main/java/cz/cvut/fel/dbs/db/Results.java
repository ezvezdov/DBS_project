package cz.cvut.fel.dbs.db;

import javax.persistence.*;

@Entity
@Table(name = "results")
public class Results{

    @Id
    @Column(name = "competition", nullable = false)
    private String competition;

    @Id
    @Column(name = "recordid", nullable = false)
    private String recordid;

//    @OneToOne
//    @JoinColumn(name="recordid")
//    private Leaderboard leaderboard;


    public void setCompetition(String competition) {
        this.competition = competition;
    }

    public String getCompetition() {
        return competition;
    }

    public void setRecordid(String recordid) {
        this.recordid = recordid;
    }

    public String getRecordid() {
        return recordid;
    }

    @Override
    public String toString() {
        return "Results{" +
                "competition=" + competition + '\'' +
                "recordid=" + recordid + '\'' +
                '}';
    }
}
