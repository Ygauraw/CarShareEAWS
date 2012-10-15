/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Merca
 */
@Entity
@Table(name = "car")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Car.findAll", query = "SELECT c FROM Car c"),
    @NamedQuery(name = "Car.findByCarid", query = "SELECT c FROM Car c WHERE c.carid = :carid"),
    @NamedQuery(name = "Car.findByAvailable", query = "SELECT c FROM Car c WHERE c.available = :available"),
    @NamedQuery(name = "Car.findByAvailabledate", query = "SELECT c FROM Car c WHERE c.availabledate = :availabledate"),
    @NamedQuery(name = "Car.findByBrand", query = "SELECT c FROM Car c WHERE c.brand = :brand"),
    @NamedQuery(name = "Car.findByModel", query = "SELECT c FROM Car c WHERE c.model = :model")})
public class Car implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "carid")
    private String carid;
    @Column(name = "available")
    private Boolean available;
    @Column(name = "availabledate")
    @Temporal(TemporalType.DATE)
    private Date availabledate;
    @Size(max = 255)
    @Column(name = "brand")
    private String brand;
    @Size(max = 255)
    @Column(name = "model")
    private String model;
    @JoinColumn(name = "cartype", referencedColumnName = "id")
    @ManyToOne
    private Cartype cartype;
    @OneToMany(mappedBy = "carCarid")
    private Collection<Reservation> reservationCollection;

    public Car() {
    }

    public Car(String carid) {
        this.carid = carid;
    }

    public String getCarid() {
        return carid;
    }

    public void setCarid(String carid) {
        this.carid = carid;
    }

    public Boolean getAvailable() {
        return available;
    }

    public void setAvailable(Boolean available) {
        this.available = available;
    }

    public Date getAvailabledate() {
        return availabledate;
    }

    public void setAvailabledate(Date availabledate) {
        this.availabledate = availabledate;
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

    public Cartype getCartype() {
        return cartype;
    }

    public void setCartype(Cartype cartype) {
        this.cartype = cartype;
    }

    @XmlTransient
    public Collection<Reservation> getReservationCollection() {
        return reservationCollection;
    }

    public void setReservationCollection(Collection<Reservation> reservationCollection) {
        this.reservationCollection = reservationCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (carid != null ? carid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Car)) {
            return false;
        }
        Car other = (Car) object;
        if ((this.carid == null && other.carid != null) || (this.carid != null && !this.carid.equals(other.carid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "service.Car[ carid=" + carid + " ]";
    }
    
}
