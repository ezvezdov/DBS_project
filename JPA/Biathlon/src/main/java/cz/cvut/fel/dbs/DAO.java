package cz.cvut.fel.dbs;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;
import java.util.List;

public class DAO<T> {
    private EntityManager em;
    private EntityTransaction et;
    final Class<T> type;

    public DAO(final Class<T> type, EntityManager em) {
        this.em = em;
        this.et = em.getTransaction();
        this.type = type;
    }

    public void insert(T item) {
        et.begin();
        em.persist(item);
        et.commit();
    }

    public void update(T item) {
        et.begin();
        em.merge(item);
        et.commit();
    }

    public void delete(T item) {
        et.begin();
        em.remove(item);
        et.commit();
    }

    public T getByID(Object id) {
        et.begin();
        T item = em.find(this.type, id);
        et.commit();
        return item;
    }

    public List<T> selectAll() {
        String q = "SELECT item FROM " + type.getSimpleName() + " AS item";
        TypedQuery jpql = em.createQuery(q, type);
        List<T> itemsList = jpql.getResultList();
        return itemsList;
    }

    public List<T> selectWithOneValue(String columnName, Object value) {
        String q = "SELECT item FROM " + type.getSimpleName() + " AS item WHERE item." + columnName + "=:val";
        TypedQuery jpql = em.createQuery(q, type);
        jpql.setParameter("val", value);
        List<T> itemsList = jpql.getResultList();
        return itemsList;
    }
    public List<T> selectWithTwoValues(String columnName1, Object value1, String columnName2, Object value2) {
        String q = "SELECT item FROM " + type.getSimpleName() + " AS item WHERE item." + columnName1 + "=:val1 AND item." + columnName2 + " =:val2";
        TypedQuery jpql = em.createQuery(q, type);
        jpql.setParameter("val1", value1);
        jpql.setParameter("val2", value2);
        List<T> itemsList = jpql.getResultList();
        return itemsList;
    }
}