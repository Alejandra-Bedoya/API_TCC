package com.example.BODEGASTCCAPI.repositorios;

import com.example.BODEGASTCCAPI.modelos.Remitente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IRemitenteRepositorio extends JpaRepository<Remitente, Long> {

    // Buscar remitentes por nombre (coincidencia exacta)
    List<Remitente> findByNombres(String nombres);

    // Buscar remitentes que contengan una palabra o frase en su nombre (coincidencia parcial)
    List<Remitente> findByNombresContaining(String nombres);

    // Buscar remitentes por departamento
    List<Remitente> findByDepartamento(String departamento);

    // Buscar remitentes por método de pago
    List<Remitente> findByMetodoPago(String metodoPago);

    // Buscar remitentes por departamento y municipio
    List<Remitente> findByDepartamentoAndMunicipio(String departamento, String municipio);
}
