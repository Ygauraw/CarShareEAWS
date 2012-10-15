/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package boundries;

import entities.Member;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author DELL
 */
@Stateless
public class MemberFacade extends AbstractFacade<Member> {
    @PersistenceContext(unitName = "CarShareEAWS-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public MemberFacade() {
        super(Member.class);
    }
     public List validateUser(String email){
        //boolean val=false;
        System.out.println("Inside memberfacade...."+email);
        Query q=em.createQuery("select u from Member u where u.email=?1");
         
         //int id=Integer.parseInt(username);
      q.setParameter(1,email);
      
       
        
    /* Member m = (Member)q.getSingleResult();
    
     String pwd = m.getPassword();
     System.out.println("22222222222");*/
      List list=q.getResultList();
       return list;
      //  return pwd;
    
    }
     public Member getById(Long id){
        return em.find(Member.class, id);
     }
    
}
