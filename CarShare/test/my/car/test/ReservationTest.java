/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package my.car.test;

import entities.Car;
import entities.Member;
import entities.Reservation;
import java.util.Date;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author Merca
 */
public class ReservationTest {
    
    private static EntityManagerFactory emf;
    private static EntityManager em;
    private static EntityTransaction tx;
    
    public ReservationTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
        emf = Persistence.createEntityManagerFactory("CarSharePU");
        em = emf.createEntityManager();
    }
    
    @AfterClass
    public static void tearDownClass() {
        em.close();
        emf.close();
    }
    
    @Before
    public void setUp() {
        tx = em.getTransaction();
    }
    
    @After
    public void tearDown() {
    }
    @Test
    public void createReservation() {
        Member m = em.find(Member.class, 1);
        Car c = em.find(Car.class, "SU71741");
        Date from = new Date(2012, 9, 29);
        Date to = new Date(2012, 10, 15);
 
        Reservation res = new Reservation();
        res.setCar(c);
        res.setMember(m);
        res.setDateFrom(new java.sql.Date(from.getDate()));
        res.setDateTo(new java.sql.Date(to.getDate()));
        
 
        tx.begin();
        em.persist(res);
        tx.commit();
        
        Reservation newRes = em.find(Reservation.class, 1);
        
 
        Assert.assertNotNull("Reservation should not be NULL", newRes);
        Assert.assertEquals("reservervation id should be", (long)newRes.getId(), (long)1);
    }
}
