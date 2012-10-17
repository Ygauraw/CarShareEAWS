/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package my.car;

import boundry.CarFacade;
import entities.Car;
import entities.CarType;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import org.primefaces.event.ToggleEvent;


/**
 *
 * @author DELL
 */
@ManagedBean(name="CarView")
@RequestScoped
public class CarView { 
    // <editor-fold defaultstate="collapsed" desc="private members">
    @EJB
    private CarFacade carFacade;
    private Car car;    
    private Date fromDate;
    private Date toDate;
    private String carId;
    private String brand;
    private String model;
    private CarType selectedtype;
    private List<Car> result;
    private Car selectedCar;
    //</editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="getters and setters">
    public Car getCar() {
        return car;
    }
    public void setCar(Car car) {
        this.car = car;
    }
    
    public List<Car> getResult() {
        return result;
    }

    public void setResult(List<Car> result) {
        this.result = result;
    }

    public Date getFromDate() {
        return fromDate;
    }

    public void setFromDate(Date fromDate) {
        this.fromDate = fromDate;
    }

    public Date getToDate() {
        return toDate;
    }

    public void setToDate(Date toDate) {
        this.toDate = toDate;
    }

    public String getCarId() {
        return carId;
    }

    public void setCarId(String carId) {
        this.carId = carId;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public CarType getSelectedtype() {
        return selectedtype;
    }

    public void setSelectedtype(CarType type) {
        this.selectedtype = type;
    }      

    public Car getSelectedCar() {
        return selectedCar;
    }

    public void setSelectedCar(Car selectedCar) {
        this.selectedCar = selectedCar;
    }
    
    //</editor-fold>

    /**
     * Creates a new instance of CarView
     */
    public CarView() {
        this.car = new Car();
        if(result == null) {
            result = new ArrayList<Car>();
        }
    }
    @PostConstruct
    public void init(){
        this.result = this.carFacade.findAll();
    }
    
    
    // Returns the total number of cars
    public int getNumberOfCars(){
    return carFacade.findAll().size();
    }
    
    public String postCar(){
        this.carFacade.create(car);
        return "index";        
    }
    
    public boolean existsCar(String id){
        this.carFacade.find(id);        
        return true;
    }
    
    public Car getById(String id){
        return this.carFacade.findCarById(id);
    }
    
    public void findInPeriod(){
        this.result = this.carFacade.findInPeriod(fromDate, toDate);        
    }
    
    public void findByCriteria(){
        this.result = this.carFacade.findByCriteria(carId,brand,model,selectedtype,fromDate,toDate);
    }
    public void getAllCars(){
        this.result = this.carFacade.findAll();
    }
    
    public void getByType(CarType type){
        this.result = this.carFacade.findByType(type);
    }
    
    public void onRowToggle(ToggleEvent event) {  
        FacesMessage msg;  
        msg = new FacesMessage(FacesMessage.SEVERITY_INFO,  
                               "Row State " + event.getVisibility(),  
                               "Model:" + ((Car) event.getSource()).getModel());
          
        FacesContext.getCurrentInstance().addMessage(null, msg);  
    }

    void getByTypeAndAvailable(Car fakeOne, Date dateTo) {
        this.result = this.carFacade.getByTypeAndAvailable(fakeOne,dateTo);
    }
}
