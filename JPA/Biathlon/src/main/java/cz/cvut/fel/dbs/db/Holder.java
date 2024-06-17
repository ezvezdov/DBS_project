package cz.cvut.fel.dbs.db;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "holder")
public class Holder{

    @Id
    @Column(name = "competition", nullable = false)
    private String competition;

    @Id
    @Column(name = "stadium_name", nullable = false)
    private String stadiumName;

    @Id
    @Column(name = "stadium_city", nullable = false)
    private String stadiumCity;

    @Id
    @Column(name = "stadium_country", nullable = false)
    private String stadiumCountry;

    public void setCompetition(String competition) {
        this.competition = competition;
    }

    public String getCompetition() {
        return competition;
    }

    public void setStadiumName(String stadiumName) {
        this.stadiumName = stadiumName;
    }

    public String getStadiumName() {
        return stadiumName;
    }

    public void setStadiumCity(String stadiumCity) {
        this.stadiumCity = stadiumCity;
    }

    public String getStadiumCity() {
        return stadiumCity;
    }

    public void setStadiumCountry(String stadiumCountry) {
        this.stadiumCountry = stadiumCountry;
    }

    public String getStadiumCountry() {
        return stadiumCountry;
    }

    @Override
    public String toString() {
        return "Holder{" +
                "competition=" + competition + '\'' +
                "stadiumName=" + stadiumName + '\'' +
                "stadiumCity=" + stadiumCity + '\'' +
                "stadiumCountry=" + stadiumCountry + '\'' +
                '}';
    }
}
