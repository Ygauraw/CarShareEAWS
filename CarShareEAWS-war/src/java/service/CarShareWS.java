/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import boundry.ReservationFacade;
import entities.Member;
import entities.Reservation;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.jws.Oneway;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

/**
 *
 * @author Merca
 */
@WebService(serviceName = "CarShareWS")
@Stateless()
public class CarShareWS {
    @EJB
    private ReservationFacade ejbRef;// Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Web Service Operation")

    @WebMethod(operationName = "create")
    @Oneway
    public void create(@WebParam(name = "entity") Reservation entity) {
        ejbRef.create(entity);
    }

    @WebMethod(operationName = "edit")
    @Oneway
    public void edit(@WebParam(name = "entity") Reservation entity) {
        ejbRef.edit(entity);
    }

    @WebMethod(operationName = "remove")
    @Oneway
    public void remove(@WebParam(name = "entity") Reservation entity) {
        ejbRef.remove(entity);
    }

    @WebMethod(operationName = "find")
    public Reservation find(@WebParam(name = "id") Object id) {
        return ejbRef.find(id);
    }

    @WebMethod(operationName = "findAll")
    public List<Reservation> findAll() {
        return ejbRef.findAll();
    }

    @WebMethod(operationName = "findRange")
    public List<Reservation> findRange(@WebParam(name = "range") int[] range) {
        return ejbRef.findRange(range);
    }

    @WebMethod(operationName = "count")
    public int count() {
        return ejbRef.count();
    }

    @WebMethod(operationName = "findByMember")
    public List<Reservation> findByMember(@WebParam(name = "member") Member member) {
        return ejbRef.findByMember(member);
    }

    @WebMethod(operationName = "validateReservation")
    public boolean validateReservation(@WebParam(name = "r") Reservation r) {
        return ejbRef.validateReservation(r);
    }
    
}
