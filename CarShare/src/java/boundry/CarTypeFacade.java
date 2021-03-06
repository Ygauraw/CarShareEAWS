/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package boundry;

import entities.CarType;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Merca Skole
 */
@Stateless
public class CarTypeFacade extends AbstractFacade<CarType> {
    @PersistenceContext(unitName = "CarSharePU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public CarTypeFacade() {
        super(CarType.class);
    }
    
}
