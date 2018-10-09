/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.snpp.proyectofinal.entidades;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author estela
 */
@Entity
@Table(name = "parentesco_familiar")
@NamedQueries({
    @NamedQuery(name = "ParentescoFamiliar.findAll", query = "SELECT p FROM ParentescoFamiliar p")})
public class ParentescoFamiliar implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "idparentesco_familiar")
    private Integer idparentescoFamiliar;
    @Column(name = "descripcion")
    private String descripcion;
    @JoinColumn(name = "alumno", referencedColumnName = "idalumno")
    @ManyToOne(optional = false)
    private Alumno alumno;
    @JoinColumn(name = "encargado", referencedColumnName = "idencargado")
    @ManyToOne(optional = false)
    private Encargado encargado;

    public ParentescoFamiliar() {
    }

    public ParentescoFamiliar(Integer idparentescoFamiliar) {
        this.idparentescoFamiliar = idparentescoFamiliar;
    }

    public Integer getIdparentescoFamiliar() {
        return idparentescoFamiliar;
    }

    public void setIdparentescoFamiliar(Integer idparentescoFamiliar) {
        this.idparentescoFamiliar = idparentescoFamiliar;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Alumno getAlumno() {
        return alumno;
    }

    public void setAlumno(Alumno alumno) {
        this.alumno = alumno;
    }

    public Encargado getEncargado() {
        return encargado;
    }

    public void setEncargado(Encargado encargado) {
        this.encargado = encargado;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idparentescoFamiliar != null ? idparentescoFamiliar.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ParentescoFamiliar)) {
            return false;
        }
        ParentescoFamiliar other = (ParentescoFamiliar) object;
        if ((this.idparentescoFamiliar == null && other.idparentescoFamiliar != null) || (this.idparentescoFamiliar != null && !this.idparentescoFamiliar.equals(other.idparentescoFamiliar))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "edu.snpp.proyectofinal.entidades.ParentescoFamiliar[ idparentescoFamiliar=" + idparentescoFamiliar + " ]";
    }
    
}
