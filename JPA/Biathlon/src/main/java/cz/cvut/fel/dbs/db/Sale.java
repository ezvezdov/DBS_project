package cz.cvut.fel.dbs.db;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "sale")
public class Sale{

    @Id
    @Column(name = "ticketid", nullable = false)
    private String ticketid;

    @Id
    @Column(name = "stadium_name", nullable = false)
    private String stadiumName;

    @Id
    @Column(name = "stadium_city", nullable = false)
    private String stadiumCity;

    @Id
    @Column(name = "stadium_country", nullable = false)
    private String stadiumCountry;

    public void setTicketid(String ticketid) {
        this.ticketid = ticketid;
    }

    public String getTicketid() {
        return ticketid;
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
        return "Sale{" +
                "ticketid=" + ticketid + '\'' +
                "stadiumName=" + stadiumName + '\'' +
                "stadiumCity=" + stadiumCity + '\'' +
                "stadiumCountry=" + stadiumCountry + '\'' +
                '}';
    }
}
