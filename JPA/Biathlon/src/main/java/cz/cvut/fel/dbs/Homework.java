package cz.cvut.fel.dbs;

import cz.cvut.fel.dbs.db.*;
import javax.persistence.*;
import java.util.List;

public class Homework {
    EntityManager em;
    DAO<Leaderboard> LeaderboardDAO;
    DAO<Biathlete> BiathleteDAO;
    DAO<Media> MediaDAO;
    DAO<Person> PersonDAO;
    DAO<Login> LoginDAO;
    DAO<Site> SiteDAO;
    DAO<Ticket> TicketDAO;

    public Homework(EntityManager em) {
        this.em = em;

        LeaderboardDAO = new DAO(Leaderboard.class, em);
        BiathleteDAO = new DAO(Biathlete.class, em);
        MediaDAO = new DAO(Media.class, em);
        PersonDAO = new DAO(Person.class, em);
        LoginDAO = new DAO(Login.class, em);
        SiteDAO = new DAO(Site.class, em);
        TicketDAO = new DAO(Ticket.class, em);
    }
    private Long getPointsForPlace(int place) {
        Long new_points = 0L;
        if (place > 5 && place < 41) {
            new_points = (long) (45 - place);
        }
        if (place < 6) {
            new_points = (long) (150 - 20 * place);
        }
        return new_points;
    }

    public void addMedia(String biathleteID, String link){
        Media m = new Media();
        m.setBiathlete(biathleteID);
        m.setLink(link);
        MediaDAO.insert(m);
    }

    public void changePersonSurname(String email, String newSurname){
        Person p = PersonDAO.getByID(email);
        p.setSurname(newSurname);
        PersonDAO.update(p);
    }

    public void createNewLogin(String mail,String username, String password){
        Login l = new Login();
        l.setUsername(username);
        l.setPassword(password);

        Site s = new Site();
        s.setEmail(mail);
        s.setUsername(username);

        LoginDAO.insert(l);
        SiteDAO.insert(s);
    }

    public void renameSector(String oldValue, String newValue){
        List<Ticket> tl = TicketDAO.selectWithOneValue("sector",oldValue);
        for(int i = 0; i < tl.size(); i++){
            Ticket t = tl.get(i);
            t.setSector(newValue);
            TicketDAO.update(t);
        }
    }

    public void disqualificate(String competitionID, int placeToDel){
        String jpql = "SELECT l FROM Leaderboard l WHERE l.place >:cur_place AND l.competition = :cur_comp";
        TypedQuery<Leaderboard> query = em.createQuery(jpql, Leaderboard.class);
        query.setParameter("cur_comp", competitionID);
        query.setParameter("cur_place", placeToDel);
        List<Leaderboard> ll = query.getResultList();

        List<Leaderboard> toRemoveLeaderboard = LeaderboardDAO.selectWithTwoValues("competition", competitionID, "place", placeToDel);
        LeaderboardDAO.delete(toRemoveLeaderboard.get(0));

        int place;
        Long new_points;
        String biathleteid;
        for(int i = 0; i < ll.size(); i++ ){
            Leaderboard l = ll.get(i);
            place = l.getPlace();
            biathleteid = l.getBiathlete();
            Biathlete biathlete = BiathleteDAO.selectWithOneValue("biathleteid",biathleteid).get(0);
            new_points = biathlete.getPoints() - this.getPointsForPlace(place) + this.getPointsForPlace(place-1);

            l.setPlace(place-1);
            LeaderboardDAO.update(l);

            biathlete.setPoints(new_points);
            BiathleteDAO.update(biathlete);
        }
    }
}
