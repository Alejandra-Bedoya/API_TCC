package com.example.BODEGASTCCAPI.servicios;

import com.example.BODEGASTCCAPI.helpers.mensajes.Mensaje;
import com.example.BODEGASTCCAPI.helpers.validaciones.MercanciaValidacion;
import com.example.BODEGASTCCAPI.modelos.Mercancia;
import com.example.BODEGASTCCAPI.modelos.ZonaBodega;
import com.example.BODEGASTCCAPI.modelos.dto.MercanciaDTO;
import com.example.BODEGASTCCAPI.modelos.mapas.IMapaMercancia;
import com.example.BODEGASTCCAPI.repositorios.IMercanciaRepositorio;
import com.example.BODEGASTCCAPI.repositorios.IZonaBodegaRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class MercanciaServicio {

    private final IMercanciaRepositorio repositorio;
    private final IZonaBodegaRepositorio zonaBodegaRepositorio;
    private final MercanciaValidacion validacion;
    private final IMapaMercancia mapaMercancia;

    @Autowired
    public MercanciaServicio(IMercanciaRepositorio repositorio,
                             IZonaBodegaRepositorio zonaBodegaRepositorio,
                             MercanciaValidacion validacion,
                             IMapaMercancia mapaMercancia) {
        this.repositorio = repositorio;
        this.zonaBodegaRepositorio = zonaBodegaRepositorio;
        this.validacion = validacion;
        this.mapaMercancia = mapaMercancia;
    }

    /**
     * Verifica si la zona tiene capacidad suficiente en términos de volumen y peso.
     * Lanza una excepción si la capacidad es insuficiente.
     * @param zona Zona de bodega donde se registrará la mercancía.
     * @param mercancia Mercancía a registrar.
     * @throws Exception si no hay suficiente capacidad de volumen o peso.
     */
    private void verificarCapacidadZona(ZonaBodega zona, Mercancia mercancia) throws Exception {
        if (zona.getCapacidadVolumenOcupado() + mercancia.getVolumen() > zona.getCapacidadMaximaVolumen()) {
            throw new Exception("La zona no tiene suficiente capacidad de volumen.");
        }
        if (zona.getCapacidadPesoOcupado() + mercancia.getPeso() > zona.getCapacidadMaximaPeso()) {
            throw new Exception("La zona no tiene suficiente capacidad de peso.");
        }
    }

    /**
     * Guarda una mercancía en la base de datos, verificando que la zona tenga capacidad suficiente.
     * Si la validación falla, lanza una excepción con el motivo del fallo.
     * @param datosMercancia Objeto Mercancia con los datos de la mercancía a guardar.
     * @return MercanciaDTO con la mercancía guardada.
     * @throws Exception si ocurre un error de validación o de almacenamiento.
     */
    public MercanciaDTO almacenarMercanciaDTO(Mercancia datosMercancia) throws Exception {
        try {
            // Validaciones de peso, volumen y fechas
            if (!validacion.validarPeso(datosMercancia.getPeso())) {
                throw new Exception(Mensaje.PESO_NEGATIVO.getMensaje());
            }
            if (!validacion.validarVolumen(datosMercancia.getVolumen())) {
                throw new Exception(Mensaje.VOLUMEN_NEGATIVO.getMensaje());
            }
            if (!validacion.validarFechas(datosMercancia.getFechaIngreso(), LocalDate.now())) {
                throw new Exception(Mensaje.FECHA_INVALIDA.getMensaje());
            }

            // Verificar si la zona especificada tiene suficiente capacidad antes de registrar la mercancía
            Optional<ZonaBodega> zonaOpt = zonaBodegaRepositorio.findById(datosMercancia.getZonabodega().getIdZona());
            if (zonaOpt.isPresent()) {
                ZonaBodega zonaBodega = zonaOpt.get();
                verificarCapacidadZona(zonaBodega, datosMercancia);

                // Actualizar el volumen y peso ocupado en la zona después de validar la capacidad
                zonaBodega.setCapacidadVolumenOcupado(zonaBodega.getCapacidadVolumenOcupado() + datosMercancia.getVolumen());
                zonaBodega.setCapacidadPesoOcupado(zonaBodega.getCapacidadPesoOcupado() + datosMercancia.getPeso());
                zonaBodegaRepositorio.save(zonaBodega);

                // Guardar la mercancía y devolverla como DTO
                Mercancia mercanciaGuardada = repositorio.save(datosMercancia);
                return mapaMercancia.mapearMercancia(mercanciaGuardada);
            } else {
                throw new Exception("Zona no encontrada.");
            }

        } catch (Exception error) {
            throw new Exception("Error al almacenar la mercancía: " + error.getMessage());
        }
    }

    /**
     * Busca todas las mercancías registradas y las devuelve como una lista de DTOs.
     * @return List<MercanciaDTO> con todas las mercancías.
     * @throws Exception si ocurre algún error al buscar las mercancías.
     */
    public List<MercanciaDTO> buscarTodasMercancias() throws Exception {
        try {
            return mapaMercancia.mapearListaMercancias(repositorio.findAll());
        } catch (Exception error) {
            throw new Exception("Error al buscar mercancías: " + error.getMessage());
        }
    }

    /**
     * Busca una mercancía por su ID.
     * @param idMercancia ID de la mercancía a buscar.
     * @return MercanciaDTO de la mercancía encontrada.
     * @throws Exception si no se encuentra la mercancía.
     */
    public MercanciaDTO buscarMercanciaPorId(Long idMercancia) throws Exception {
        Optional<Mercancia> mercanciaOpt = repositorio.findById(idMercancia);
        if (mercanciaOpt.isPresent()) {
            return mapaMercancia.mapearMercancia(mercanciaOpt.get());
        } else {
            throw new Exception("Mercancía no encontrada con ID: " + idMercancia);
        }
    }

    /**
     * Elimina una mercancía por su ID.
     * @param idMercancia ID de la mercancía a eliminar.
     * @return true si se eliminó con éxito, de lo contrario lanza excepción.
     * @throws Exception si no se encuentra la mercancía o hay algún problema al eliminarla.
     */
    public boolean eliminarMercancia(Long idMercancia) throws Exception {
        Optional<Mercancia> mercanciaOpt = repositorio.findById(idMercancia);
        if (mercanciaOpt.isPresent()) {
            repositorio.deleteById(idMercancia);
            return true;
        } else {
            throw new Exception("Mercancía no encontrada con ID: " + idMercancia);
        }
    }
}
