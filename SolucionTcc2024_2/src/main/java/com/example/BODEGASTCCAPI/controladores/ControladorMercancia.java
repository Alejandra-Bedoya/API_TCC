package com.example.BODEGASTCCAPI.controladores;

import com.example.BODEGASTCCAPI.modelos.Mercancia;
import com.example.BODEGASTCCAPI.modelos.dto.MercanciaDTO;
import com.example.BODEGASTCCAPI.servicios.MercanciaServicio;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@RestController
@RequestMapping("/soluciontcc/v1/mercancias")
@Tag(name = "Servicios de Mercancía", description = "CRUD para gestionar mercancías almacenadas en el sistema.")
public class ControladorMercancia {

    @Autowired
    private MercanciaServicio mercanciaServicio;

    /**
     * Registra una nueva mercancía en la base de datos.
     *
     * @param mercancia objeto con los datos enviados desde el cliente.
     * @return ResponseEntity con la mercancía registrada o un mensaje de error.
     */
    @PostMapping
    @Operation(
            summary = "Registrar nueva mercancía",
            description = "Registra una mercancía en la base de datos utilizando el modelo Mercancia."
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "201",
                    description = "Mercancía registrada con éxito",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = MercanciaDTO.class),
                            examples = @ExampleObject(value = "{ \"volumen\": 20.5, \"peso\": 400, \"nombre\": \"Nevera LG\", \"direccion\": \"calle 100 sur 123\", \"fechaIngreso\": \"2024-10-8\" }")
                    )
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Error al registrar la mercancía",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = String.class),
                            examples = @ExampleObject(value = "{ \"mensaje\": \"El volumen no puede ser negativo\" }")
                    )
            )
    })
    public ResponseEntity<?> registrarMercancia(@RequestBody Mercancia mercancia) {
        try {
            // Validar datos básicos (ejemplo: volumen y peso no pueden ser negativos)
            if (mercancia.getVolumen() <= 0 || mercancia.getPeso() <= 0) {
                throw new IllegalArgumentException("El volumen y el peso deben ser mayores a cero.");
            }

            MercanciaDTO mercanciaGuardada = mercanciaServicio.almacenarMercanciaDTO(mercancia);
            return ResponseEntity.status(HttpStatus.CREATED).body(mercanciaGuardada);
        } catch (IllegalArgumentException e) {
            // Manejo de errores de validación
            HashMap<String, Object> mensajeError = new HashMap<>();
            mensajeError.put("mensaje", e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(mensajeError);
        } catch (Exception e) {
            // Manejo de errores generales
            HashMap<String, Object> mensajeError = new HashMap<>();
            mensajeError.put("mensaje", "Error al registrar la mercancía");
            mensajeError.put("error", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(mensajeError);
        }
    }

    /**
     * Devuelve todas las mercancías almacenadas.
     *
     * @return ResponseEntity con la lista de mercancías o un mensaje de error.
     */
    @GetMapping
    @Operation(
            summary = "Obtener todas las mercancías",
            description = "Devuelve una lista de todas las mercancías registradas en la base de datos."
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Mercancías obtenidas con éxito",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = MercanciaDTO.class),
                            examples = @ExampleObject(value = "[{ \"volumen\": 20.5, \"peso\": 400, \"nombre\": \"Nevera LG\", \"direccion\": \"calle 100 sur 123\", \"fechaIngreso\": \"2024-10-8\" }]")
                    )
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "Error al obtener las mercancías",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = String.class),
                            examples = @ExampleObject(value = "{ \"mensaje\": \"Error en la base de datos\" }")
                    )
            )
    })
    public ResponseEntity<?> obtenerTodasLasMercancias() {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(mercanciaServicio.buscarTodasMercancias());
        } catch (Exception e) {
            HashMap<String, Object> mensajeError = new HashMap<>();
            mensajeError.put("mensaje", "Error al obtener las mercancías");
            mensajeError.put("error", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(mensajeError);
        }
    }
}
