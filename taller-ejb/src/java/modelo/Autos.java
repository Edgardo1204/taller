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
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Jesus
 */
@Entity
@Table(name = "autos")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Autos.findAll", query = "SELECT a FROM Autos a"),
    @NamedQuery(name = "Autos.findById", query = "SELECT a FROM Autos a WHERE a.id = :id"),
    @NamedQuery(name = "Autos.findByMarca", query = "SELECT a FROM Autos a WHERE a.marca = :marca"),
    @NamedQuery(name = "Autos.findByModelo", query = "SELECT a FROM Autos a WHERE a.modelo = :modelo"),
    @NamedQuery(name = "Autos.findByA\u00f1o", query = "SELECT a FROM Autos a WHERE a.a\u00f1o = :a\u00f1o"),
    @NamedQuery(name = "Autos.findByColor", query = "SELECT a FROM Autos a WHERE a.color = :color"),
    @NamedQuery(name = "Autos.findByPlacas", query = "SELECT a FROM Autos a WHERE a.placas = :placas"),
    @NamedQuery(name = "Autos.findByKm", query = "SELECT a FROM Autos a WHERE a.km = :km"),
    @NamedQuery(name = "Autos.findByVin", query = "SELECT a FROM Autos a WHERE a.vin = :vin"),
    @NamedQuery(name = "Autos.findByEstado", query = "SELECT a FROM Autos a WHERE a.estado = :estado")})
public class Autos implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID")
    private Integer id;
    @Size(max = 255)
    @Column(name = "MARCA")
    private String marca;
    @Size(max = 255)
    @Column(name = "MODELO")
    private String modelo;
    @Column(name = "A\u00d1O")
    private Integer año;
    @Size(max = 255)
    @Column(name = "COLOR")
    private String color;
    @Size(max = 255)
    @Column(name = "PLACAS")
    private String placas;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "KM")
    private Double km;
    @Size(max = 255)
    @Column(name = "VIN")
    private String vin;
    @Column(name = "ESTADO")
    private Boolean estado;
    @JoinColumn(name = "ID_CLIENTE", referencedColumnName = "ID")
    @ManyToOne
    private Clientes idCliente;

    public Autos() {
    }

    public Autos(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public Integer getAño() {
        return año;
    }

    public void setAño(Integer año) {
        this.año = año;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getPlacas() {
        return placas;
    }

    public void setPlacas(String placas) {
        this.placas = placas;
    }

    public Double getKm() {
        return km;
    }

    public void setKm(Double km) {
        this.km = km;
    }

    public String getVin() {
        return vin;
    }

    public void setVin(String vin) {
        this.vin = vin;
    }

    public Boolean getEstado() {
        return estado;
    }

    public void setEstado(Boolean estado) {
        this.estado = estado;
    }

    public Clientes getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(Clientes idCliente) {
        this.idCliente = idCliente;
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
        if (!(object instanceof Autos)) {
            return false;
        }
        Autos other = (Autos) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "modelo.Autos[ id=" + id + " ]";
    }
    
}
