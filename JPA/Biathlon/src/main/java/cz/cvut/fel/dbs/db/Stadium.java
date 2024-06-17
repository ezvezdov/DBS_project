package cz.cvut.fel.dbs.db;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "stadium")
public class Stadium{

    @Id
    @Column(name = "name", nullable = false)
    private String name;

    @Id
    @Column(name = "city", nullable = false)
    private String city;

    @Id
    @Column(name = "country", nullable = false)
    private String country;

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCity() {
        return city;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCountry() {
        return country;
    }

    @Override
    public String toString() {
        return "Stadium{" +
                "name=" + name + '\'' +
                "city=" + city + '\'' +
                "country=" + country + '\'' +
                '}';
    }
}
