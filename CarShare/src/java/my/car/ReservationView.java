/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package my.car;

import boundry.ReservationFacade;
import entities.Reservation;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.component.UIComponent;
import javax.faces.component.UIInput;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 *
 * @author Merca
 */
@ManagedBean(name="ReservationView")
@RequestScoped
public class ReservationView {
    @EJB
    private ReservationFacade reservationFacade;   
    
    //<editor-fold defaultstate="collapsed" desc="private members">    
    private Reservation current;    
    private List<Reservation> reservations;
    @ManagedProperty(value="#{MemberView}")
    private MemberView member;
    @ManagedProperty(value="#{CarView}")
    private CarView carview;
    
    private Date minDate = new Date();
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="setters and getters">
    
    public List<Reservation> getReservations() {
        return reservations;
    }

    public void setReservations(List<Reservation> reservations) {
        this.reservations = reservations;
    }

    private ReservationFacade getFacade() {
        return reservationFacade;
    }

    public MemberView getMember() {
        return member;
    }

    public void setMember(MemberView member) {
        this.member = member;
    }

    public CarView getCarview() {
        return carview;
    }

    public void setCarview(CarView carview) {
        this.carview = carview;
    }
    
    public Reservation getSelected() {
        if (current == null) {
            current = new Reservation();
        }
        return current;
    }
    public void setSelected(Reservation reservation){
        current = reservation;
    }

    public Date getMinDate() {
        return minDate;
    }

    public void setMinDate(Date minDate) {
        this.minDate = minDate;
    }
    
    //</editor-fold>
    
    /**
     * Creates a new instance of ReservationView
     */
    public ReservationView() {  
        this.reservations = new ArrayList<Reservation>();
    }
    
    @PostConstruct
    public void init(){
        if(member.getMember() == null) {
           
        } 
         this.reservations = this.reservationFacade.findByMember(member.getMember());
    }    
    

    
    public void create() { 
        
        if(current.getDateFrom()==null || current.getDateTo()==null){
             FacesContext.getCurrentInstance().addMessage("reservationMsg", new FacesMessage(FacesMessage.SEVERITY_ERROR,"Date", "No dates"));
             
        }
        else {
            try {
                current.setCar(carview.getSelectedCar());
                current.setMember(member.getMember());
                if(getFacade().validateReservation(current))
                {
                    getFacade().create(current);
                    FacesContext.getCurrentInstance().addMessage("reservationMsg", new FacesMessage(FacesMessage.SEVERITY_INFO, "Reservation created succesfully", 
                            "Reservation created succesfully"));
                    this.clearDates();
                }
                else
                {
                    FacesContext.getCurrentInstance().addMessage("reservationMsg", new FacesMessage(FacesMessage.SEVERITY_ERROR, "Reservation can not be created", 
                            "Reservation can not be created on those dates"));
                }

            } catch (Exception e) {
                //TODO create the failure
            }
        }
    }
    
    public void clearDates(){
        current.setDateFrom(null);
        current.setDateTo(null);
    }

    public int getNumberOfReservations(){
        return reservationFacade.findAll().size();
    }
    //<editor-fold defaultstate="collapsed" desc="Valitations">
    public void validateDateFrom(FacesContext context, UIComponent toValidate, Object value){
        Date valid = (Date)value;
        if(valid.before(new Date())){
            ((UIInput)toValidate).setValid(false);
            FacesContext.getCurrentInstance().addMessage("reservationMsg", new FacesMessage(FacesMessage.SEVERITY_ERROR,"From date", "From date is in past!"));
        }
        else if(current.getDateTo() != null && valid.after(current.getDateTo())){
            ((UIInput)toValidate).setValid(false);
            FacesContext.getCurrentInstance().addMessage("reservationMsg", new FacesMessage(FacesMessage.SEVERITY_ERROR,"From date", "From date is after to date!"));
        }
    }
    //</editor-fold>
    
    
    @FacesConverter(forClass = Reservation.class)
    public static class ReservationViewConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            ReservationView controller = (ReservationView) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "ReservationView");
            return controller.reservationFacade.find(getKey(value));
        }

        java.lang.Long getKey(String value) {
            java.lang.Long key;
            key = Long.valueOf(value);
            return key;
        }

        String getStringKey(java.lang.Long value) {
            StringBuilder sb = new StringBuilder();
            sb.append(value);
            return sb.toString();
        }

        @Override
        public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
            if (object == null) {
                return null;
            }
            if (object instanceof Reservation) {
                Reservation o = (Reservation) object;
                return getStringKey(o.getId());
            } else {
                throw new IllegalArgumentException("object " + object + " is of type " + object.getClass().getName() + "; expected type: " + Reservation.class.getName());
            }
        }
    }
    
}
