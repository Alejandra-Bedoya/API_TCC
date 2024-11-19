package com.example.BODEGASTCCAPI.modelos.mapas;

import com.example.BODEGASTCCAPI.modelos.Mercancia;
import com.example.BODEGASTCCAPI.modelos.dto.MercanciaDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "spring")
public interface IMapaMercancia {

    @Mappings({
            @Mapping(source = "iup", target = "iup"),
            @Mapping(source = "volumen", target = "volumen"),
            @Mapping(source = "peso", target = "peso"),
            @Mapping(source = "nombre", target = "nombre"),
            @Mapping(source = "tipoDestinatario", target = "tipoDestinatario"),
            @Mapping(source = "nombreDestinatario", target = "nombreDestinatario"),
            @Mapping(source = "departamento", target = "departamento"),
            @Mapping(source = "ciudad", target = "ciudad"),
            @Mapping(source = "direccion", target = "direccion"),
            @Mapping(source = "fechaIngreso", target = "fechaIngreso"),
            @Mapping(source = "fechaSalida", target = "fechaSalida"),
            @Mapping(source = "zonabodega.idZona", target = "idZonaBodega"),  // Asegúrate de que esta propiedad existe en ZonaBodega
            @Mapping(source = "zonabodega.nombreZona", target = "nombreZona")  // Asegúrate de que esta propiedad existe en ZonaBodega
    })
    MercanciaDTO mapearMercancia(Mercancia mercancia);

    List<MercanciaDTO> mapearListaMercancias(List<Mercancia> listaMercancia);

}
