/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aprodher.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Lorena Franco
 */
@Entity
@Table(name = "concurso")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Concurso.findAll", query = "SELECT c FROM Concurso c")
    , @NamedQuery(name = "Concurso.findByUsuMod", query = "SELECT c FROM Concurso c WHERE c.usuMod = :usuMod")
    , @NamedQuery(name = "Concurso.findByUsuAlta", query = "SELECT c FROM Concurso c WHERE c.usuAlta = :usuAlta")
    , @NamedQuery(name = "Concurso.findByFechaAlta", query = "SELECT c FROM Concurso c WHERE c.fechaAlta = :fechaAlta")
    , @NamedQuery(name = "Concurso.findByFechaMod", query = "SELECT c FROM Concurso c WHERE c.fechaMod = :fechaMod")
    , @NamedQuery(name = "Concurso.findByEstado", query = "SELECT c FROM Concurso c WHERE c.estado = :estado")
    , @NamedQuery(name = "Concurso.findById", query = "SELECT c FROM Concurso c WHERE c.id = :id")
    , @NamedQuery(name = "Concurso.findByFecha", query = "SELECT c FROM Concurso c WHERE c.fecha = :fecha")
    , @NamedQuery(name = "Concurso.findByNombre", query = "SELECT c FROM Concurso c WHERE c.nombre = :nombre")
    , @NamedQuery(name = "Concurso.findByDireccion", query = "SELECT c FROM Concurso c WHERE c.direccion = :direccion")})
public class Concurso implements Serializable {

    @OneToMany(mappedBy = "concurso", fetch = FetchType.LAZY)
    private List<ConcursoAcademia> concursoAcademiaList;

    private static final long serialVersionUID = 1L;

    @Column(name = "usu_mod")
    private String usuMod;
    

    @Column(name = "usu_alta")
    private String usuAlta;
    
    @Column(name = "fecha_alta")
    @Temporal(TemporalType.DATE)
    private Date fechaAlta;
    
    @Column(name = "fecha_mod")
    @Temporal(TemporalType.DATE)
    private Date fechaMod;
    
    @Size(max = 2)
    @Column(name = "estado")
    private String estado;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
   
    @Column(name = "fecha")
    @Temporal(TemporalType.DATE)
    private Date fecha;

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "direccion")
    private String direccion;
    
    @Column(name = "vigente")
    private Boolean vigente;

    public Concurso() {
    }

    public Concurso(Integer id) {
        this.id = id;
    }

    public String getUsuMod() {
        return usuMod;
    }

    public void setUsuMod(String usuMod) {
        this.usuMod = usuMod;
    }

    public String getUsuAlta() {
        return usuAlta;
    }

    public void setUsuAlta(String usuAlta) {
        this.usuAlta = usuAlta;
    }

    public Date getFechaAlta() {
        return fechaAlta;
    }

    public void setFechaAlta(Date fechaAlta) {
        this.fechaAlta = fechaAlta;
    }

    public Date getFechaMod() {
        return fechaMod;
    }

    public void setFechaMod(Date fechaMod) {
        this.fechaMod = fechaMod;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
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

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public Boolean getVigente() {
        return vigente;
    }

    public void setVigente(Boolean vigente) {
        this.vigente = vigente;
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
        if (!(object instanceof Concurso)) {
            return false;
        }
        Concurso other = (Concurso) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.aprodher.aprodherweb.Concurso[ id=" + id + " ]";
    }

    @XmlTransient
    public List<ConcursoAcademia> getConcursoAcademiaList() {
        return concursoAcademiaList;
    }

    public void setConcursoAcademiaList(List<ConcursoAcademia> concursoAcademiaList) {
        this.concursoAcademiaList = concursoAcademiaList;
    }
    
}
