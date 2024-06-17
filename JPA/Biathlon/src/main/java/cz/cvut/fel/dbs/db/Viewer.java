package cz.cvut.fel.dbs.db;

import javax.persistence.*;

@Entity
@Table(name = "viewer")
public class Viewer{

    @Id
    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "viewerid", nullable = false)
    private String viewerid;


    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public void setViewerid(String viewerid) {
        this.viewerid = viewerid;
    }

    public String getViewerid() {
        return viewerid;
    }

    @Override
    public String toString() {
        return "Viewer{" +
                "email=" + email + '\'' +
                "viewerid=" + viewerid + '\'' +
                '}';
    }
}
