/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package my.car;

import boundry.DamageReportFacade;
import entities.Car;
import entities.DamageReport;
import entities.Member;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

/**
 *
 * @author Merca
 */
@ManagedBean(name="DamageReportView")
@RequestScoped
public class DamageReportView {
    
    //<editor-fold defaultstate="collapsed" desc="private members">
    @EJB
    private DamageReportFacade damageReportFacade;
    
    private DamageReport current;
    //</editor-fold>    
    
    //<editor-fold defaultstate="collapsed" desc="setters and getters">
    public DamageReportFacade getDamageReportFacade() {
        return damageReportFacade;
    }

    public void setDamageReportFacade(DamageReportFacade damageReportFacade) {
        this.damageReportFacade = damageReportFacade;
    }

    public DamageReport getCurrent() {
        return current;
    }

    public void setCurrent(DamageReport current) {
        this.current = current;
    }
    //</editor-fold>
    
    /**
     * Creates a new instance of DamageReportView
     */
    public DamageReportView() {
        this.current = new DamageReport();
    }
    
    public void create(){
        
         getDamageReportFacade().create(current);
    }
    
}
