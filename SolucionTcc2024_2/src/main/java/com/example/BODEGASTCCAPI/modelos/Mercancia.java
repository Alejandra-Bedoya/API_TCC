package com.example.BODEGASTCCAPI.modelos;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;

import java.time.LocalDate;

@Entity
@Table(name = "mercancias")
public class Mercancia {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "iup")
    private Long iup;

    @Column(name = "volumen", nullable = false)
    @Positive(message = "El volumen debe ser un número positivo")
    private Double volumen;

    @Column(name = "peso", nullable = false)
    @Positive(message = "El peso debe ser un número positivo")
    private Double peso;

    @Column(name = "nombre_mercancia", nullable = true, length = 50)
    @Pattern(regexp = "^[A-Za-zÀ-ÿ\\s]{1,50}$", message = "El nombre solo debe contener letras y espacios, máximo 50 caracteres")
    private String nombre;

    @Column(name = "tipo_destinatario", nullable = false)
    private String tipoDestinatario;

    @Column(name = "nombre_destinatario", nullable = false)
    @NotBlank(message = "El nombre del destinatario no puede estar vacío")
    private String nombreDestinatario;

    @Column(name = "departamento", nullable = false)
    private String departamento;

    @Column(name = "ciudad", nullable = false)
    private String ciudad;

    @Column(name = "direccion", nullable = false)
    private String direccion;

    @Column(name = "fecha_ingreso", nullable = false)
    @NotNull(message = "La fecha de ingreso es obligatoria")
    private LocalDate fechaIngreso;

    @Column(name = "fecha_salida", nullable = true)
    private LocalDate fechaSalida;

    @ManyToOne
    @JoinColumn(name = "fk_zonabodega", referencedColumnName = "id_zona")
    @JsonBackReference
    private ZonaBodega zonabodega;

    public Mercancia() {}

    public Mercancia(Long iup, Double volumen, Double peso, String nombre, String tipoDestinatario, String nombreDestinatario, String departamento, String ciudad, String direccion, LocalDate fechaIngreso, LocalDate fechaSalida, ZonaBodega zonabodega) {
        this.iup = iup;
        this.volumen = volumen;
        this.peso = peso;
        this.nombre = nombre;
        this.tipoDestinatario = tipoDestinatario;
        this.nombreDestinatario = nombreDestinatario;
        this.departamento = departamento;
        this.ciudad = ciudad;
        this.direccion = direccion;
        this.fechaIngreso = fechaIngreso;
        this.fechaSalida = fechaSalida;
        this.zonabodega = zonabodega;
    }

    public Long getIup() {
        return iup;
    }

    public void setIup(Long iup) {
        this.iup = iup;
    }

    public Double getVolumen() {
        return volumen;
    }

    public void setVolumen(Double volumen) {
        this.volumen = volumen;
    }

    public Double getPeso() {
        return peso;
    }

    public void setPeso(Double peso) {
        this.peso = peso;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTipoDestinatario() {
        return tipoDestinatario;
    }

    public void setTipoDestinatario(String tipoDestinatario) {
        this.tipoDestinatario = tipoDestinatario;
    }

    public String getNombreDestinatario() {
        return nombreDestinatario;
    }

    public void setNombreDestinatario(String nombreDestinatario) {
        this.nombreDestinatario = nombreDestinatario;
    }

    public String getDepartamento() {
        return departamento;
    }

    public void setDepartamento(String departamento) {
        this.departamento = departamento;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public LocalDate getFechaIngreso() {
        return fechaIngreso;
    }

    public void setFechaIngreso(LocalDate fechaIngreso) {
        this.fechaIngreso = fechaIngreso;
    }

    public LocalDate getFechaSalida() {
        return fechaSalida;
    }

    public void setFechaSalida(LocalDate fechaSalida) {
        if (fechaSalida != null && fechaIngreso != null && fechaSalida.isBefore(fechaIngreso)) {
            throw new IllegalArgumentException("La fecha de salida no puede ser anterior a la fecha de ingreso");
        }
        this.fechaSalida = fechaSalida;
    }

    public ZonaBodega getZonabodega() {
        return zonabodega;
    }

    public void setZonabodega(ZonaBodega zonabodega) {
        this.zonabodega = zonabodega;
    }
}
