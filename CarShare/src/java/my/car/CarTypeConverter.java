/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package my.car;


import boundry.CarTypeFacade;
import entities.CarType;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;

/**
 *
 * @author Merca Skole
 */
@ManagedBean
@RequestScoped
public class CarTypeConverter implements Converter{
    @EJB
    private CarTypeFacade carTypeFacade;
    
    /**
     * Creates a new instance of CarTypeConverter
     */
    public CarTypeConverter() {
    }

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        if (value == null || value.isEmpty()) {
            return null;
        }

        try {
            return carTypeFacade.find(Integer.valueOf(value));
        } catch (Exception e) {
            throw new ConverterException(new FacesMessage(String.format("Cannot convert %s to CarType", value)), e);
        }
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if (!(value instanceof CarType)) {
            return null;
        }

        return String.valueOf(((CarType) value).getId());
    }
}
