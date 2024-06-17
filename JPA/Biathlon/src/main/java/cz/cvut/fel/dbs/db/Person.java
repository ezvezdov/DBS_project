package cz.cvut.fel.dbs.db;

import javax.persistence.*;


@Entity
@Table(name = "person")
public class Person{

    @Id
    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "surname", nullable = false)
    private String surname;

    @Column(name = "sex")
    private String sex;

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getSurname() {
        return surname;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getSex() {
        return sex;
    }

    @Override
    public String toString() {
        return "Person{" +
                "email=" + email + '\'' +
                "surname=" + surname + '\'' +
                "sex=" + sex + '\'' +
                '}';
    }
}
