package cz.cvut.fel.dbs.db;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "teammates")
public class Teammates{

    @Id
    @Column(name = "teammate", nullable = false)
    private String teammate;

    @Column(name = "biathlete", nullable = false)
    private String biathlete;

    public void setTeammate(String teammate) {
        this.teammate = teammate;
    }

    public String getTeammate() {
        return teammate;
    }

    public void setBiathlete(String biathlete) {
        this.biathlete = biathlete;
    }

    public String getBiathlete() {
        return biathlete;
    }

    @Override
    public String toString() {
        return "Teammates{" +
                "teammate=" + teammate + '\'' +
                "biathlete=" + biathlete + '\'' +
                '}';
    }
}
