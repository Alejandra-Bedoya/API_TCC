package com.example.BODEGASTCCAPI.modelos.mapas;

import com.example.BODEGASTCCAPI.modelos.Mercancia;
import com.example.BODEGASTCCAPI.modelos.ZonaBodega;
import com.example.BODEGASTCCAPI.modelos.dto.MercanciaDTO;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-11-11T22:25:29-0500",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 22.0.1 (Oracle Corporation)"
)
@Component
public class IMapaMercanciaImpl implements IMapaMercancia {

    @Override
    public MercanciaDTO mapearMercancia(Mercancia mercancia) {
        if ( mercancia == null ) {
            return null;
        }

        MercanciaDTO mercanciaDTO = new MercanciaDTO();

        mercanciaDTO.setIup( mercancia.getIup() );
        mercanciaDTO.setVolumen( mercancia.getVolumen() );
        mercanciaDTO.setPeso( mercancia.getPeso() );
        mercanciaDTO.setNombre( mercancia.getNombre() );
        mercanciaDTO.setTipoDestinatario( mercancia.getTipoDestinatario() );
        mercanciaDTO.setNombreDestinatario( mercancia.getNombreDestinatario() );
        mercanciaDTO.setDepartamento( mercancia.getDepartamento() );
        mercanciaDTO.setCiudad( mercancia.getCiudad() );
        mercanciaDTO.setDireccion( mercancia.getDireccion() );
        mercanciaDTO.setFechaIngreso( mercancia.getFechaIngreso() );
        mercanciaDTO.setFechaSalida( mercancia.getFechaSalida() );
        mercanciaDTO.setIdZonaBodega( mercanciaZonabodegaIdZona( mercancia ) );
        mercanciaDTO.setNombreZona( mercanciaZonabodegaNombreZona( mercancia ) );

        return mercanciaDTO;
    }

    @Override
    public List<MercanciaDTO> mapearListaMercancias(List<Mercancia> listaMercancia) {
        if ( listaMercancia == null ) {
            return null;
        }

        List<MercanciaDTO> list = new ArrayList<MercanciaDTO>( listaMercancia.size() );
        for ( Mercancia mercancia : listaMercancia ) {
            list.add( mapearMercancia( mercancia ) );
        }

        return list;
    }

    private Long mercanciaZonabodegaIdZona(Mercancia mercancia) {
        if ( mercancia == null ) {
            return null;
        }
        ZonaBodega zonabodega = mercancia.getZonabodega();
        if ( zonabodega == null ) {
            return null;
        }
        Long idZona = zonabodega.getIdZona();
        if ( idZona == null ) {
            return null;
        }
        return idZona;
    }

    private String mercanciaZonabodegaNombreZona(Mercancia mercancia) {
        if ( mercancia == null ) {
            return null;
        }
        ZonaBodega zonabodega = mercancia.getZonabodega();
        if ( zonabodega == null ) {
            return null;
        }
        String nombreZona = zonabodega.getNombreZona();
        if ( nombreZona == null ) {
            return null;
        }
        return nombreZona;
    }
}
