package com.example.BODEGASTCCAPI.repositorios;

import com.example.BODEGASTCCAPI.modelos.Mercancia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface IMercanciaRepositorio extends JpaRepository<Mercancia, Long> {

    // Buscar mercancías por nombre
    List<Mercancia> findByNombre(String nombre);

    // Buscar mercancías por departamento
    List<Mercancia> findByDepartamento(String departamento);

    // Buscar mercancías cuyo peso sea menor o igual a un valor específico
    List<Mercancia> findByPesoLessThanEqual(Double peso);

    // Buscar mercancías dentro de un rango de fechas de ingreso
    List<Mercancia> findByFechaIngresoBetween(LocalDate fechaInicio, LocalDate fechaFin);

}
