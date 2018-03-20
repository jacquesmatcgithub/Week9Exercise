package edu.matc.persistence;

import edu.matc.entity.Order;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Root;
import java.util.List;

public class OrderDao {

    private final Logger logger = LogManager.getLogger(this.getClass());

    SessionFactory sessionFactory = SessionFactoryProvider.getSessionFactory();
    /**
     * Gets all orders.
     *
     * @return the all orders
     */
    public List<Order> getAllOrders() {

        Session session = sessionFactory.openSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Order> query = builder.createQuery(Order.class);
        Root<Order> root = query.from(Order.class);
        List<Order> orders = session.createQuery(query).getResultList();

        session.close();

        return orders;

    }



    /**
     * Gets a order by ID
     * @param searchTerm
     * @return a order
     */
    public Order getById(int id) {

        Session session = sessionFactory.openSession();

        // This uses the unique ID (@Id) defined in the order.java
        // When searching by the unique id you don't have to set up
        // all the CriteriaBuilder stuff.
        Order order = session.get(Order.class, id);

        session.close();

        return order;

    }

    /**
     * update order
     * @param order  Order to be inserted or updated
     */
    public void saveOrUpdate(Order order) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.saveOrUpdate(order);
        transaction.commit();
        session.close();
    }

    /**
     * update order
     * @param order  Order to be inserted or updated
     */
    public int insert(Order order) {
        int id = 0;
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        id = (int)session.save(order);
        transaction.commit();
        session.close();
        return id;
    }

    /**
     * Delete a order
     * @param order Order to be deleted
     */
    public void delete(Order order) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.delete(order);
        transaction.commit();
        session.close();
    }


    /** Return a list of all orders
     *
     * @return All orders
     */
    public List<Order> getAll() {

        Session session = sessionFactory.openSession();

        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Order> query = builder.createQuery( Order.class );
        Root<Order> root = query.from(Order.class );
        List<Order> orders = session.createQuery( query ).getResultList();

        logger.debug("The list of orders " + orders);
        session.close();

        return orders;
    }

    /**
     * Get order by property (exact match)
     * sample usage: getByPropertyEqual("lastname", "Curry")
     */
    public List<Order> getByPropertyEqual(String propertyName, String value) {
        Session session = sessionFactory.openSession();

        logger.debug("Searching for order with " + propertyName + " = " + value);

        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Order> query = builder.createQuery( Order.class );
        Root<Order> root = query.from(Order.class );
        query.select(root).where(builder.equal(root.get(propertyName), value));
        List<Order> orders = session.createQuery( query ).getResultList();

        session.close();
        return orders;
    }

    /**
     * Get order by property (like)
     * sample usage: getByPropertyLike("lastname", "C")
     */
    public List<Order> getByPropertyLike(String propertyName, String value) {
        Session session = sessionFactory.openSession();

        logger.debug("Searching for order with {} = {}",  propertyName, value);

        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Order> query = builder.createQuery( Order.class );
        Root<Order> root = query.from( Order.class );
        Expression<String> propertyPath = root.get(propertyName);

        query.where(builder.like(propertyPath, "%" + value + "%"));

        List<Order> orders = session.createQuery( query ).getResultList();
        session.close();
        return orders;
    }

}

