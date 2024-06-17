package cz.cvut.fel.dbs;

import javax.persistence.*;

public class Main {

    public static void main(String[] args) {
        EntityManagerFactory  emf = Persistence.createEntityManagerFactory("BiathlonPU");
        EntityManager em = emf.createEntityManager();

        //1.  I have @ManyToMany relationship in classes Leaderboard and Biathlete
        //2.  I tried to implement inheritance, but program didn't work after this.
        //    ↓ Inheritance should look like this ↓
        //    Person.java:
        //          @Entity
        //          @Table(name = "person")
        //          @DiscriminatorColumn(name="person_type")
        //          public class Person{...}
        //    Biathlete.java:
        //          @Entity
        //          @Table(name = "biathlete")
        //          @DiscriminatorValue(name = "B")
        //          public class Biathlete extends Person{...}
        //    Staff.java:
        //          @Entity
        //          @Table(name = "staff")
        //          @DiscriminatorValue(name = "S")
        //          public class Staff extends Person{...}
        //    Viewer.java:
        //          @Entity
        //          @Table(name = "viewer")
        //          @DiscriminatorValue(name = "V")
        //          public class Viewer extends Person{...}

        Homework hw = new Homework(em);

        // function that disqualifies competitor from leaderboard with competitionID and placeToDel
//        hw.disqualificate("kHvD4bH88gJaX9va",3);

        // function that adds new biathlete social web
//        hw.addMedia("hH6lcTe6C63ZiMY6","facebook.com/test");

        // Change surname of person
//        hw.changePersonSurname("jtod7rt6@outlook.gov","ABSB");

        // Creates new login
//        hw.createNewLogin("iy3hvjzz@outlook.edu","feafFEfaef","feafeaga245");

        // Rename sectors in Ticket from V26 to N26
//        hw.renameSector("V26","N26");

        em.close();
        emf.close();
    }
}