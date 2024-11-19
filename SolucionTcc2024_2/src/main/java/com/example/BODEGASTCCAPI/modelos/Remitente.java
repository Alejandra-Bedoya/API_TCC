package com.example.BODEGASTCCAPI.modelos;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "remitentes")
public class Remitente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nombres", nullable = false)
    @NotBlank(message = "El campo nombres no puede estar vacío")
    @Size(max = 100, message = "El campo nombres no puede exceder los 100 caracteres")
    private String nombres;

    @Column(name = "departamento", nullable = false)
    @NotBlank(message = "El campo departamento no puede estar vacío")
    @Size(max = 50, message = "El campo departamento no puede exceder los 50 caracteres")
    private String departamento;

    @Column(name = "municipio", nullable = false)
    @NotBlank(message = "El campo municipio no puede estar vacío")
    @Size(max = 50, message = "El campo municipio no puede exceder los 50 caracteres")
    private String municipio;

    @Column(name = "direccion", nullable = false)
    @NotBlank(message = "El campo dirección no puede estar vacío")
    @Size(max = 150, message = "El campo dirección no puede exceder los 150 caracteres")
    private String direccion;

    @Column(name = "metodo_pago", nullable = false)
    @NotBlank(message = "El campo método de pago no puede estar vacío")
    @Size(max = 30, message = "El campo método de pago no puede exceder los 30 caracteres")
    private String metodoPago;

    // Constructor vacío (requerido por JPA)
    public Remitente() {}

    // Constructor completo
    public Remitente(Long id, String nombres, String departamento, String municipio, String direccion, String metodoPago) {
        this.id = id;
        this.nombres = nombres;
        this.departamento = departamento;
        this.municipio = municipio;
        this.direccion = direccion;
        this.metodoPago = metodoPago;
    }

    // Getters y Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getDepartamento() {
        return departamento;
    }

    public void setDepartamento(String departamento) {
        this.departamento = departamento;
    }

    public String getMunicipio() {
        return municipio;
    }

    public void setMunicipio(String municipio) {
        this.municipio = municipio;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getMetodoPago() {
        return metodoPago;
    }

    public void setMetodoPago(String metodoPago) {
        this.metodoPago = metodoPago;
    }
}
