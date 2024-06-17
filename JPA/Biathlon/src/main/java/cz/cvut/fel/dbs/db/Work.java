package cz.cvut.fel.dbs.db;

import javax.persistence.*;

@Entity
@Table(name = "work")
public class Work{

    @Id
    @Column(name = "employe", nullable = false)
    private String employe;

    @Id
    @Column(name = "stadium_name", nullable = false)
    private String stadiumName;

    @Id
    @Column(name = "stadium_city", nullable = false)
    private String stadiumCity;

    @Id
    @Column(name = "stadium_country", nullable = false)
    private String stadiumCountry;

    public void setEmploye(String employe) {
        this.employe = employe;
    }

    public String getEmploye() {
        return employe;
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
        return "Work{" +
                "employe=" + employe + '\'' +
                "stadiumName=" + stadiumName + '\'' +
                "stadiumCity=" + stadiumCity + '\'' +
                "stadiumCountry=" + stadiumCountry + '\'' +
                '}';
    }
}
