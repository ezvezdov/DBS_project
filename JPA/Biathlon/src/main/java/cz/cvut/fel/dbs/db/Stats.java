package cz.cvut.fel.dbs.db;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;

@Entity
@Table(name = "stats")
public class Stats{

    @Id
    @Column(name = "biathlete", nullable = false)
    private String biathlete;

    @Column(name = "average_speed", nullable = false)
    private BigDecimal averageSpeed;

    @Column(name = "average_shooting", nullable = false)
    private Long averageShooting;

    public void setBiathlete(String biathlete) {
        this.biathlete = biathlete;
    }

    public String getBiathlete() {
        return biathlete;
    }

    public void setAverageSpeed(BigDecimal averageSpeed) {
        this.averageSpeed = averageSpeed;
    }

    public BigDecimal getAverageSpeed() {
        return averageSpeed;
    }

    public void setAverageShooting(Long averageShooting) {
        this.averageShooting = averageShooting;
    }

    public Long getAverageShooting() {
        return averageShooting;
    }

    @Override
    public String toString() {
        return "Stats{" +
                "biathlete=" + biathlete + '\'' +
                "averageSpeed=" + averageSpeed + '\'' +
                "averageShooting=" + averageShooting + '\'' +
                '}';
    }
}
