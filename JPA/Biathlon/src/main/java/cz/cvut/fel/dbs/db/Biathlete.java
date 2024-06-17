package cz.cvut.fel.dbs.db;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name = "biathlete")
public class Biathlete{

    @Id
    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "biathleteid", nullable = false)
    private String biathleteid;

    @Column(name = "points")
    private Long points;

    @ManyToMany
    private Collection<Leaderboard> leaderboardRecords;


    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public void setBiathleteid(String biathleteid) {
        this.biathleteid = biathleteid;
    }

    public String getBiathleteid() {
        return biathleteid;
    }

    public void setPoints(Long points) {
        this.points = points;
    }

    public Long getPoints() {
        return points;
    }

    public Collection<Leaderboard> getLeaderboardRecords() {
        return leaderboardRecords;
    }

    public void setLeaderboardRecords(Collection<Leaderboard> leaderboardRecords) {
        this.leaderboardRecords = leaderboardRecords;
    }

    @Override
    public String toString() {
        return "Biathlete{" +
                "email=" + email + '\'' +
                "biathleteid=" + biathleteid + '\'' +
                "points=" + points + '\'' +
                '}';
    }
}
