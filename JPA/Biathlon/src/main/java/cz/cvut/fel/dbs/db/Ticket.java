package cz.cvut.fel.dbs.db;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;

@Entity
@Table(name = "ticket")
public class Ticket{

    @Id
    @Column(name = "ticketid", nullable = false)
    private String ticketid;

    @Column(name = "transactionid")
    private String transactionid;

    @Column(name = "sector", nullable = false)
    private String sector;

    @Column(name = "cost", nullable = false)
    private BigDecimal cost;

    public void setTicketid(String ticketid) {
        this.ticketid = ticketid;
    }

    public String getTicketid() {
        return ticketid;
    }

    public void setTransactionid(String transactionid) {
        this.transactionid = transactionid;
    }

    public String getTransactionid() {
        return transactionid;
    }

    public void setSector(String sector) {
        this.sector = sector;
    }

    public String getSector() {
        return sector;
    }

    public void setCost(BigDecimal cost) {
        this.cost = cost;
    }

    public BigDecimal getCost() {
        return cost;
    }

    @Override
    public String toString() {
        return "Ticket{" +
                "ticketid=" + ticketid + '\'' +
                "transactionid=" + transactionid + '\'' +
                "sector=" + sector + '\'' +
                "cost=" + cost + '\'' +
                '}';
    }
}
