/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package boundry;

import entities.Member;
import entities.Reservation;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author Merca Skole
 */
@Stateless
public class ReservationFacade extends AbstractFacade<Reservation> {
    @PersistenceContext(unitName = "CarShareEAWS-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ReservationFacade() {
        super(Reservation.class);
        
    }   

    public List<Reservation> findByMember(Member member) {
        StringBuilder sb = new StringBuilder();
        sb.append("select r from Reservation r ");
        sb.append("where r.member_id = :memberid ");       
        
        Query q = em.createQuery(sb.toString());
        q.setParameter("memberid", member);
        List<Reservation> res = q.getResultList();
        return res;
    }
    
    public boolean validateReservation(Reservation r){
        try{
            StringBuilder sb = new StringBuilder();
            sb.append("select r from Reservation r ");
            sb.append("where r.carid = :car ");
            sb.append("and (:from between r.dateFrom and r.dateTo ");
            sb.append("or :to between r.dateFrom and r.dateTo) ");
            Query q = em.createQuery(sb.toString());
            q.setParameter("car", r.getCar());
            q.setParameter("from", r.getDateFrom());
            q.setParameter("to", r.getDateTo());
            return q.getSingleResult() == null ? true : false;
        }
        catch(Exception e){
            System.out.println("bad format of query");
            return false;
        }
        
    }
    
    
}
