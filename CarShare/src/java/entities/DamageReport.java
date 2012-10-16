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
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Merca
 */
@Entity
@Table(name = "damageReport")
@XmlRootElement
public class DamageReport implements Serializable {
    private static final long serialVersionUID = 1L;
    
    // <editor-fold defaultstate="collapsed" desc="table columns"> 
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    //@NotNull(message="To date is required")
    @Temporal(TemporalType.DATE)
    private Date dateTo;
    
    private boolean permDamage;
    
    private String damageText;    
    
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

    public Date getDateTo() {
        return dateTo;
    }

    public void setDateTo(Date dateTo) {
        this.dateTo = dateTo;
    }

    public boolean isPermDamage() {
        return permDamage;
    }

    public String getDamageText() {
        return damageText;
    }

    public void setDamageText(String damageText) {
        this.damageText = damageText;
    }

    public void setPermDamage(boolean permDamage) {
        this.permDamage = permDamage;
    }

    public Car getCarid() {
        return carid;
    }

    public void setCarid(Car carid) {
        this.carid = carid;
    }

    public Member getMember_id() {
        return member_id;
    }

    public void setMember_id(Member member_id) {
        this.member_id = member_id;
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
        if (!(object instanceof DamageReport)) {
            return false;
        }
        DamageReport other = (DamageReport) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.DamageReport[ id=" + id + " ]";
    }
    
}
