package cz.cvut.fel.dbs.db;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "site")
public class Site{

    @Id
    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "username")
    private String username;

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    @Override
    public String toString() {
        return "Site{" +
                "email=" + email + '\'' +
                "username=" + username + '\'' +
                '}';
    }
}
