package cz.cvut.fel.dbs.db;

import javax.persistence.*;

@Entity
@Table(name = "staff")
public class Staff{

    @Column(name = "email", nullable = false)
    private String email;

    @Id
    @Column(name = "employeeid", nullable = false)
    private String employeeid;

    @Column(name = "position", nullable = false)
    private String position;


    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public void setEmployeeid(String employeeid) {
        this.employeeid = employeeid;
    }

    public String getEmployeeid() {
        return employeeid;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getPosition() {
        return position;
    }

    @Override
    public String toString() {
        return "Staff{" +
                "email=" + email + '\'' +
                "employeeid=" + employeeid + '\'' +
                "position=" + position + '\'' +
                '}';
    }
}
