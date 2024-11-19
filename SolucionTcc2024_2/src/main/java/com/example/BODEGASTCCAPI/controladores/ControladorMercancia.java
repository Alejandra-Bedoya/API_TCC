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
@Tag(name = "Servicios asociados a la entidad Mercancia", description = "CRUD completo para la tabla Mercancia, permitiendo lectura y escritura de datos.")
public class ControladorMercancia {

    @Autowired
    private MercanciaServicio mercanciaServicio;

    /**
     * Endpoint para registrar una nueva mercancía en la base de datos.
     *
     * @param datosMercanciaEnviadosCliente objeto Mercancia con los datos enviados desde el cliente
     * @return ResponseEntity con el objeto registrado o un mensaje de error en caso de fallo
     */
    @PostMapping()
    @Operation(
            summary = "Registra una nueva mercancía en la base de datos",
            description = "Permite el registro exitoso de una mercancía en la base de datos utilizando el modelo Mercancia."
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "201",
                    description = "Mercancía almacenada con éxito en la base de datos",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = MercanciaDTO.class),
                            examples = @ExampleObject(value = "{\"volumen\":\"20.5\",\"peso\":\"400\",\"nombre\":\"Nevera LG\",\"direccion\":\"calle 100 sur 123\",\"fechaIngreso\":\"2024-10-8\"}")
                    )
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Error al registrar la mercancía",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = String.class),
                            examples = @ExampleObject(value = "{\"mensaje\":\"El volumen no puede ser negativo\"}")
                    )
            )
    })
    public ResponseEntity<?> LlamadoGuardarMercanciaDTO(@RequestBody Mercancia datosMercanciaEnviadosCliente) {
        try {
            MercanciaDTO mercanciaGuardada = mercanciaServicio.almacenarMercanciaDTO(datosMercanciaEnviadosCliente);
            return ResponseEntity.status(HttpStatus.CREATED).body(mercanciaGuardada);
        } catch (Exception error) {
            HashMap<String, Object> mensajeRespuesta = new HashMap<>();
            mensajeRespuesta.put("mensaje", error.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(mensajeRespuesta);
        }
    }

    /**
     * Endpoint para obtener todas las mercancías almacenadas en la base de datos.
     *
     * @return ResponseEntity con la lista de mercancías o un mensaje de error en caso de fallo
     */
    @GetMapping()
    @Operation(
            summary = "Buscar todas las mercancías almacenadas en la base de datos",
            description = "Retorna todos los registros de mercancías en formato JSON."
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Mercancías encontradas con éxito en la base de datos",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = MercanciaDTO.class),
                            examples = @ExampleObject(value = "{\"volumen\":\"20.5\",\"peso\":\"400\",\"nombre\":\"Nevera LG\",\"direccion\":\"calle 100 sur 123\",\"fechaIngreso\":\"2024-10-8\"}")
                    )
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Error al buscar las mercancías",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = String.class),
                            examples = @ExampleObject(value = "{\"mensaje\":\"Error en la búsqueda de mercancías\"}")
                    )
            )
    })
    public ResponseEntity<?> LlamadoBuscarMercanciasDTO() {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(mercanciaServicio.buscarTodasMercancias());
        } catch (Exception error) {
            HashMap<String, Object> mensajeRespuesta = new HashMap<>();
            mensajeRespuesta.put("mensaje", error.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(mensajeRespuesta);
        }
    }
}
