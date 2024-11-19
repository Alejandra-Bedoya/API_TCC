package com.example.BODEGASTCCAPI.repositorios;

import com.example.BODEGASTCCAPI.modelos.ZonaBodega;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IZonaBodegaRepositorio extends JpaRepository<ZonaBodega, Long> {

    // Buscar zonas de bodega por nombre exacto
    List<ZonaBodega> findByNombreZona(String nombreZona);

    // Buscar zonas de bodega que tengan un nombre que contenga una palabra específica (coincidencia parcial)
    List<ZonaBodega> findByNombreZonaContaining(String nombreZona);

    // Buscar zonas de bodega con capacidad máxima de volumen mayor o igual a un valor específico
    List<ZonaBodega> findByCapacidadMaximaVolumenGreaterThanEqual(Double capacidadVolumen);

    // Buscar zonas de bodega con capacidad máxima de peso mayor o igual a un valor específico
    List<ZonaBodega> findByCapacidadMaximaPesoGreaterThanEqual(Double capacidadPeso);

    // Buscar zonas de bodega con volumen ocupado menor o igual a un valor específico (para ver si hay espacio disponible)
    List<ZonaBodega> findByCapacidadVolumenOcupadoLessThanEqual(Double volumenOcupado);

    // Buscar zonas de bodega con peso ocupado menor o igual a un valor específico (para ver si hay capacidad de peso disponible)
    List<ZonaBodega> findByCapacidadPesoOcupadoLessThanEqual(Double pesoOcupado);
}
