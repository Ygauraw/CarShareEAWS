/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package boundry;

import entities.DamageReport;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Merca
 */
@Stateless
public class DamageReportFacade extends AbstractFacade<DamageReport> {
    @PersistenceContext(unitName = "CarSharePU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public DamageReportFacade() {
        super(DamageReport.class);
    }
    
}
