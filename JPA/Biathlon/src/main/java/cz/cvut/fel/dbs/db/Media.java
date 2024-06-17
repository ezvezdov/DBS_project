package cz.cvut.fel.dbs.db;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "media")
public class Media{

    @Id
    @Column(name = "biathlete", nullable = false)
    private String biathlete;

    @Id
    @Column(name = "link", nullable = false)
    private String link;

    public void setBiathlete(String biathlete) {
        this.biathlete = biathlete;
    }

    public String getBiathlete() {
        return biathlete;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getLink() {
        return link;
    }

    @Override
    public String toString() {
        return "Media{" +
                "biathlete=" + biathlete + '\'' +
                "link=" + link + '\'' +
                '}';
    }
}
