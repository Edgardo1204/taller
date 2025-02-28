/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
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
 * @author Jesus
 */
@Entity
@Table(name = "hoja_servicio")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "HojaServicio.findAll", query = "SELECT h FROM HojaServicio h"),
    @NamedQuery(name = "HojaServicio.findById", query = "SELECT h FROM HojaServicio h WHERE h.id = :id"),
    @NamedQuery(name = "HojaServicio.findByFolio", query = "SELECT h FROM HojaServicio h WHERE h.folio = :folio"),
    @NamedQuery(name = "HojaServicio.findByFecha", query = "SELECT h FROM HojaServicio h WHERE h.fecha = :fecha"),
    @NamedQuery(name = "HojaServicio.findByObservaciones", query = "SELECT h FROM HojaServicio h WHERE h.observaciones = :observaciones")})
public class HojaServicio implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Column(name = "FOLIO")
    private int folio;
    @Size(max = 255)
    @Column(name = "OBSERVACIONES")
    private String observaciones;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idHoja")
    private List<DetalleServicio> detalleServicioList;

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID")
    private Integer id;
    @Column(name = "FECHA")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fecha;
    @JoinColumn(name = "ID_CLIENTE", referencedColumnName = "ID")
    @ManyToOne
    private Clientes idCliente;
    @JoinColumn(name = "ID_EMPLEADO", referencedColumnName = "ID")
    @ManyToOne
    private Empleado idEmpleado;

    public HojaServicio() {
    }

    public HojaServicio(Integer id) {
        this.id = id;
    }

    public HojaServicio(Integer id, int folio) {
        this.id = id;
        this.folio = folio;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }


    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }


    public Clientes getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(Clientes idCliente) {
        this.idCliente = idCliente;
    }

    public Empleado getIdEmpleado() {
        return idEmpleado;
    }

    public void setIdEmpleado(Empleado idEmpleado) {
        this.idEmpleado = idEmpleado;
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
        if (!(object instanceof HojaServicio)) {
            return false;
        }
        HojaServicio other = (HojaServicio) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "modelo.HojaServicio[ id=" + id + " ]";
    }

    public int getFolio() {
        return folio;
    }

    public void setFolio(int folio) {
        this.folio = folio;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    @XmlTransient
    public List<DetalleServicio> getDetalleServicioList() {
        return detalleServicioList;
    }

    public void setDetalleServicioList(List<DetalleServicio> detalleServicioList) {
        this.detalleServicioList = detalleServicioList;
    }
    
}
