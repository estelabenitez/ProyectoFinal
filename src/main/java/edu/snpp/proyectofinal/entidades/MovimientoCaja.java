/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.snpp.proyectofinal.entidades;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
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

/**
 *
 * @author fredybogado
 */
@Entity
@Table(name = "movimiento_caja")
@NamedQueries({
    @NamedQuery(name = "MovimientoCaja.findAll", query = "SELECT m FROM MovimientoCaja m")})
public class MovimientoCaja implements Serializable {

    @JoinColumn(name = "empleado_idempleado", referencedColumnName = "idempleado")
    @ManyToOne(optional = false)
    private Empleado empleadoIdempleado;

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "idcaja")
    private Integer idcaja;
    @Column(name = "fecha")
    @Temporal(TemporalType.DATE)
    private Date fecha;
    @Column(name = "apertura_caja")
    private Integer aperturaCaja;
    @Column(name = "cierre_caja")
    private Integer cierreCaja;
    @Column(name = "total_entrada")
    private Integer totalEntrada;
    @Column(name = "total_salida")
    private Integer totalSalida;
    @JoinColumn(name = "empleado", referencedColumnName = "idempleado")
    @ManyToOne(optional = false)
    private Empleado empleado;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "caja")
    private List<DetalleCaja> detalleCajaList;

    public MovimientoCaja() {
    }

    public MovimientoCaja(Integer idcaja) {
        this.idcaja = idcaja;
    }

    public Integer getIdcaja() {
        return idcaja;
    }

    public void setIdcaja(Integer idcaja) {
        this.idcaja = idcaja;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Integer getAperturaCaja() {
        return aperturaCaja;
    }

    public void setAperturaCaja(Integer aperturaCaja) {
        this.aperturaCaja = aperturaCaja;
    }

    public Integer getCierreCaja() {
        return cierreCaja;
    }

    public void setCierreCaja(Integer cierreCaja) {
        this.cierreCaja = cierreCaja;
    }

    public Integer getTotalEntrada() {
        return totalEntrada;
    }

    public void setTotalEntrada(Integer totalEntrada) {
        this.totalEntrada = totalEntrada;
    }

    public Integer getTotalSalida() {
        return totalSalida;
    }

    public void setTotalSalida(Integer totalSalida) {
        this.totalSalida = totalSalida;
    }

    public Empleado getEmpleado() {
        return empleado;
    }

    public void setEmpleado(Empleado empleado) {
        this.empleado = empleado;
    }

    public List<DetalleCaja> getDetalleCajaList() {
        return detalleCajaList;
    }

    public void setDetalleCajaList(List<DetalleCaja> detalleCajaList) {
        this.detalleCajaList = detalleCajaList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idcaja != null ? idcaja.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MovimientoCaja)) {
            return false;
        }
        MovimientoCaja other = (MovimientoCaja) object;
        if ((this.idcaja == null && other.idcaja != null) || (this.idcaja != null && !this.idcaja.equals(other.idcaja))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "edu.snpp.proyectofinal.entidades.MovimientoCaja[ idcaja=" + idcaja + " ]";
    }

    public Empleado getEmpleadoIdempleado() {
        return empleadoIdempleado;
    }

    public void setEmpleadoIdempleado(Empleado empleadoIdempleado) {
        this.empleadoIdempleado = empleadoIdempleado;
    }
    
}
