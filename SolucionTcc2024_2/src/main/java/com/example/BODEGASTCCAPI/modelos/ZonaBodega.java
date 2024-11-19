package com.example.BODEGASTCCAPI.modelos;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;

import java.util.List;

@Entity
@Table(name = "zonas")
public class ZonaBodega {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_zona")
    private Long idZona;

    @Column(name = "nombre_zona", nullable = false)
    @NotBlank(message = "El nombre de la zona no puede estar vacío")
    private String nombreZona;

    @Column(name = "capacidad_maxima_volumen", nullable = false)
    @NotNull(message = "La capacidad máxima de volumen es obligatoria")
    @PositiveOrZero(message = "La capacidad máxima de volumen debe ser un valor positivo o cero")
    private Double capacidadMaximaVolumen;

    @Column(name = "capacidad_maxima_peso", nullable = false)
    @NotNull(message = "La capacidad máxima de peso es obligatoria")
    @PositiveOrZero(message = "La capacidad máxima de peso debe ser un valor positivo o cero")
    private Double capacidadMaximaPeso;

    @Column(name = "capacidad_volumen_ocupado", nullable = false)
    @PositiveOrZero(message = "La capacidad de volumen ocupado debe ser un valor positivo o cero")
    private Double capacidadVolumenOcupado = 0.0; // Inicializado en 0.0

    @Column(name = "capacidad_peso_ocupado", nullable = false)
    @PositiveOrZero(message = "La capacidad de peso ocupado debe ser un valor positivo o cero")
    private Double capacidadPesoOcupado = 0.0; // Inicializado en 0.0

    @OneToMany(mappedBy = "zonabodega")
    @JsonManagedReference
    private List<Mercancia> mercancias;

    // Constructor vacío (requerido por JPA)
    public ZonaBodega() {}

    // Constructor con todos los parámetros
    public ZonaBodega(Long idZona, String nombreZona, Double capacidadMaximaVolumen, Double capacidadMaximaPeso, Double capacidadVolumenOcupado, Double capacidadPesoOcupado) {
        this.idZona = idZona;
        this.nombreZona = nombreZona;
        this.capacidadMaximaVolumen = capacidadMaximaVolumen;
        this.capacidadMaximaPeso = capacidadMaximaPeso;
        this.capacidadVolumenOcupado = capacidadVolumenOcupado;
        this.capacidadPesoOcupado = capacidadPesoOcupado;
    }

    // Getters y Setters
    public Long getIdZona() {
        return idZona;
    }

    public void setIdZona(Long idZona) {
        this.idZona = idZona;
    }

    public String getNombreZona() {
        return nombreZona;
    }

    public void setNombreZona(String nombreZona) {
        this.nombreZona = nombreZona;
    }

    public Double getCapacidadMaximaVolumen() {
        return capacidadMaximaVolumen;
    }

    public void setCapacidadMaximaVolumen(Double capacidadMaximaVolumen) {
        this.capacidadMaximaVolumen = capacidadMaximaVolumen;
    }

    public Double getCapacidadMaximaPeso() {
        return capacidadMaximaPeso;
    }

    public void setCapacidadMaximaPeso(Double capacidadMaximaPeso) {
        this.capacidadMaximaPeso = capacidadMaximaPeso;
    }

    public Double getCapacidadVolumenOcupado() {
        return capacidadVolumenOcupado;
    }

    public void setCapacidadVolumenOcupado(Double capacidadVolumenOcupado) {
        this.capacidadVolumenOcupado = capacidadVolumenOcupado;
    }

    public Double getCapacidadPesoOcupado() {
        return capacidadPesoOcupado;
    }

    public void setCapacidadPesoOcupado(Double capacidadPesoOcupado) {
        this.capacidadPesoOcupado = capacidadPesoOcupado;
    }

    public List<Mercancia> getMercancias() {
        return mercancias;
    }

    public void setMercancias(List<Mercancia> mercancias) {
        this.mercancias = mercancias;
    }
}
