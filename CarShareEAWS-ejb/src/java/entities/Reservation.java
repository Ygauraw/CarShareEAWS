/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Merca Skole
 */
@Entity
@Table(name = "reservation")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Reservation.findAll", query = "SELECT r FROM Reservation r"),
    @NamedQuery(name = "Reservation.findById", query = "SELECT r FROM Reservation r WHERE r.id = :id"),
    @NamedQuery(name = "Reservation.findByDatefrom", query = "SELECT r FROM Reservation r WHERE r.dateFrom = :datefrom"),
    @NamedQuery(name = "Reservation.findByDateto", query = "SELECT r FROM Reservation r WHERE r.dateTo = :dateto")})
public class Reservation implements Serializable {
    private static final long serialVersionUID = 1L;
    // <editor-fold defaultstate="collapsed" desc="table columns">
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    //@NotNull(message="From date is required")
    @Temporal(TemporalType.DATE)
    private Date dateFrom;
    //@NotNull(message="To date is required")
    @Temporal(TemporalType.DATE)
    private Date dateTo;
    @NotNull(message="You have to choose car")
    @JoinColumn(name = "car_carid", referencedColumnName = "carid")
    @ManyToOne
    private Car carid;    
    @NotNull(message="You are not logged in")
    @JoinColumn(name = "member_id", referencedColumnName = "id")
    @ManyToOne
    private Member member_id;
    //</editor-fold>
    // <editor-fold defaultstate="collapsed" desc="getters and setters">
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getDateFrom() {
        return dateFrom;
    }

    public void setDateFrom(Date dateFrom) {
        this.dateFrom = dateFrom;
    }

    public Date getDateTo() {
        return dateTo;
    }

    public void setDateTo(Date dateTo) {
        this.dateTo = dateTo;
    }

    public Car getCar() {
        return carid;
    }

    public void setCar(Car car) {
        this.carid = car;
    }

    public Member getMember() {
        return member_id;
    }

    public void setMember(Member member) {
        this.member_id = member;
    }
    //</editor-fold>
    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Reservation)) {
            return false;
        }
        Reservation other = (Reservation) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Reservation[ id=" + id + " ]";
    }
    
}
