package cz.cvut.fel.dbs.db;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "purchase")
public class Purchase{

    @Id
    @Column(name = "viewerid", nullable = false)
    private String viewerid;

    @Column(name = "ticketid", nullable = false)
    private String ticketid;

    public void setViewerid(String viewerid) {
        this.viewerid = viewerid;
    }

    public String getViewerid() {
        return viewerid;
    }

    public void setTicketid(String ticketid) {
        this.ticketid = ticketid;
    }

    public String getTicketid() {
        return ticketid;
    }

    @Override
    public String toString() {
        return "Purchase{" +
                "viewerid=" + viewerid + '\'' +
                "ticketid=" + ticketid + '\'' +
                '}';
    }
}
