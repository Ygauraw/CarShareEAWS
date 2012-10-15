/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package my.car;

import boundry.CarTypeFacade;
import entities.CarType;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

/**
 *
 * @author Merca Skole
 */
@ManagedBean(name="CarTypeView")
@RequestScoped
public class CarTypeView{
    @EJB
    private CarTypeFacade carTypeFacade;
    
    private CarType carType;
    private List<CarType> allTypes;
    
    @PostConstruct
    public void init() {
        allTypes = carTypeFacade.findAll();
    }
    
    public List<CarType> getAllTypes() {
        return allTypes;
    }

    public void setAllTypes(List<CarType> allTypes) {
        this.allTypes = allTypes;
    }

    public CarType getCarType() {
        return carType;
    }

    public void setCarType(CarType carType) {
        this.carType = carType;
    }

    /**
     * Creates a new instance of CarTypeView
     */
    public CarTypeView() {
    }
    
}
