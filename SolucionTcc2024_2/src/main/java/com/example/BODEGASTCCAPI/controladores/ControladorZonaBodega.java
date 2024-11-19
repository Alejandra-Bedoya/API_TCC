package com.example.BODEGASTCCAPI.controladores;

import com.example.BODEGASTCCAPI.modelos.ZonaBodega;
import com.example.BODEGASTCCAPI.repositorios.IZonaBodegaRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/soluciontcc/v1/zonas")
public class ControladorZonaBodega {

    private final IZonaBodegaRepositorio zonaBodegaRepositorio;

    @Autowired
    public ControladorZonaBodega(IZonaBodegaRepositorio zonaBodegaRepositorio) {
        this.zonaBodegaRepositorio = zonaBodegaRepositorio;
    }

    /**
     * Endpoint para registrar una nueva zona de bodega.
     * @param nuevaZona Objeto ZonaBodega con los datos de la nueva zona.
     * @return ResponseEntity con mensaje de confirmación y datos de la zona registrada.
     */
    @PostMapping("/registrar")
    public ResponseEntity<?> registrarZona(@RequestBody ZonaBodega nuevaZona) {
        try {
            ZonaBodega zonaGuardada = zonaBodegaRepositorio.save(nuevaZona);
            HashMap<String, Object> respuesta = new HashMap<>();
            respuesta.put("mensaje", "Zona registrada con éxito");
            respuesta.put("zona", zonaGuardada);
            return ResponseEntity.status(HttpStatus.CREATED).body(respuesta);
        } catch (Exception e) {
            HashMap<String, String> errorRespuesta = new HashMap<>();
            errorRespuesta.put("mensaje", "Error al registrar la zona");
            errorRespuesta.put("error", e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorRespuesta);
        }
    }

    /**
     * Endpoint para listar todas las zonas de bodega.
     * @return ResponseEntity con la lista de todas las zonas de bodega.
     */
    @GetMapping("/listar")
    public ResponseEntity<?> listarZonas() {
        try {
            List<ZonaBodega> zonas = zonaBodegaRepositorio.findAll();
            return ResponseEntity.ok(zonas);
        } catch (Exception e) {
            HashMap<String, String> errorRespuesta = new HashMap<>();
            errorRespuesta.put("mensaje", "Error al listar las zonas");
            errorRespuesta.put("error", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorRespuesta);
        }
    }
}
