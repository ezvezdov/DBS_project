package cz.cvut.fel.dbs.db;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "names")
public class Names{

    @Id
    @Column(name = "email", nullable = false)
    private String email;

    @Id
    @Column(name = "name", nullable = false)
    private String name;

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "Names{" +
                "email=" + email + '\'' +
                "name=" + name + '\'' +
                '}';
    }
}
