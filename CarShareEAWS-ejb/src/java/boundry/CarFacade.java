/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package boundry;

import entities.Car;
import entities.CarType;
import java.util.Date;
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
public class CarFacade extends AbstractFacade<Car> {
    @PersistenceContext(unitName = "CarShareEAWS-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public CarFacade() {
        super(Car.class);
    }
    public Car findCarById(String id){
        Query q = em.createNamedQuery("Car.findByCarid");
        q.setParameter("id", id);
        Car ret = (Car) q.getSingleResult();
        return ret;
    }
    
    public List<Car> findCarByIdList(String id){
        Query q = em.createNamedQuery("Car.findByCarid");
        q.setParameter("id", id);
        List<Car> ret = q.getResultList();
        return ret;
    }
    
    public List<Car> findInPeriod(Date from, Date to){
        StringBuilder sb = new StringBuilder();
        sb.append("select c from Car c ");
        sb.append("where not exists ");
        sb.append("(select r ");
        sb.append("from Reservation r ");
        sb.append("where r.carid = c ");
        sb.append("and (:from between r.dateFrom and r.dateTo ");
        sb.append("or :to between r.dateFrom and r.dateTo)) ");      
        
        Query q = em.createQuery(sb.toString());
        q.setParameter("from", from);
        q.setParameter("to", to);
        List<Car> res = q.getResultList();
        return res;        
    }

    public List<Car> findByCriteria(String carId, String brand, String model, CarType type, Date from, Date to) {
        List<Car> res = null;
        boolean ci=!carId.isEmpty(), br = !brand.isEmpty(),mo = !model.isEmpty(),ty = type != null,df = from!=null,dt = to != null,needAnd=false;        
        StringBuilder sb = new StringBuilder();
        sb.append("select c from Car c ");
        if(ci)
        {   
            sb.append("where upper(c.carid) like upper(:carid)");
        }
        else if(br || mo || ty || df || dt)
        {  
            sb.append("where ");
            if(br){
                sb.append("upper(c.brand) like upper(:brand) "); 
                needAnd = true;
            }
            if(mo){
                if(needAnd){
                    sb.append("and ");
                }
                sb.append("upper(c.model) like upper(:model) "); 
                needAnd = true;
            }
            if(ty){
                if(needAnd){
                    sb.append("and ");
                }
                sb.append("c.carType = :type ");
                needAnd = true;
            }
            if(df || dt){
               if(needAnd){
                    sb.append("and ");
                }
                sb.append(" not exists ");
                sb.append("(select r ");
                sb.append("from Reservation r ");
                sb.append("where r.carid = c ");
                if(df && dt){
                    sb.append("and (:from between r.dateFrom and r.dateTo ");
                    sb.append("or :to between r.dateFrom and r.dateTo)) ");
                }
                else if(df && !dt){
                    sb.append("and :from between r.dateFrom and r.dateTo) ");
                }
                else{
                    sb.append("and :to between r.dateFrom and r.dateTo) ");
                }
            }            
        }
        try{
            Query q = em.createQuery(sb.toString());
            if(ci){
                q.setParameter("carid", carId);
            }
            if(br){
                q.setParameter("brand", brand);
            }
            if(mo){
                q.setParameter("model", model);
            }
            if(ty){
                q.setParameter("cartype", type);
            }
            if(df){
                q.setParameter("from", from);
            }
            if(dt){
                q.setParameter("to", to);
            }
            res = q.getResultList();
        }
        catch(Exception e){
            //TODO
            System.out.println("Error: " + e.getMessage());
        }
        return res;
    }
    
}
