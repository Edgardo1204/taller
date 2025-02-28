/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Jesus
 */
@Entity
@Table(name = "detalle_servicio")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "DetalleServicio.findAll", query = "SELECT d FROM DetalleServicio d"),
    @NamedQuery(name = "DetalleServicio.findById", query = "SELECT d FROM DetalleServicio d WHERE d.id = :id"),
    @NamedQuery(name = "DetalleServicio.findByCantidad", query = "SELECT d FROM DetalleServicio d WHERE d.cantidad = :cantidad"),
    @NamedQuery(name = "DetalleServicio.findByPrecio", query = "SELECT d FROM DetalleServicio d WHERE d.precio = :precio")})
public class DetalleServicio implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID")
    private Integer id;
    @Column(name = "CANTIDAD")
    private Integer cantidad;
    @Column(name = "PRECIO")
    private Integer precio;
    @JoinColumn(name = "ID_HOJA", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private HojaServicio idHoja;
    @JoinColumn(name = "ID_PROD_SERV", referencedColumnName = "ID")
    @ManyToOne
    private Inventario idProdServ;

    public DetalleServicio() {
    }

    public DetalleServicio(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    public Integer getPrecio() {
        return precio;
    }

    public void setPrecio(Integer precio) {
        this.precio = precio;
    }

    public HojaServicio getIdHoja() {
        return idHoja;
    }

    public void setIdHoja(HojaServicio idHoja) {
        this.idHoja = idHoja;
    }

    public Inventario getIdProdServ() {
        return idProdServ;
    }

    public void setIdProdServ(Inventario idProdServ) {
        this.idProdServ = idProdServ;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DetalleServicio)) {
            return false;
        }
        DetalleServicio other = (DetalleServicio) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "modelo.DetalleServicio[ id=" + id + " ]";
    }
    
}
