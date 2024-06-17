package cz.cvut.fel.dbs.db;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "finish")
public class Finish {

    @Id
    @Column(name = "biathlete", nullable = false)
    private String biathlete;

    @Id
    @Column(name = "recordid", nullable = false)
    private String recordid;

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

    @Override
    public String toString() {
        return "Finish{" +
                "biathlete=" + biathlete + '\'' +
                "recordid=" + recordid + '\'' +
                '}';
    }
}
