// src/services/servicioConsultaZona.js
import { API_URL } from "../config.js";

export async function consultarMercancias() {
    const URL = `${API_URL}/mercancias`;
    let peticion = {
        method: "GET",
        headers: {
            "Content-Type": "application/json",
        },
    };

    try {
        let respuestaServidor = await fetch(URL, peticion);

        // Verificar si la respuesta es exitosa
        if (!respuestaServidor.ok) {
            console.warn(`La respuesta no fue exitosa: ${respuestaServidor.status} - ${respuestaServidor.statusText}`);
            return []; // Retornar una lista vacía si la solicitud falla
        }

        let respuestaFinal = await respuestaServidor.json();
        
        // Verificar si la respuesta contiene datos válidos
        if (!Array.isArray(respuestaFinal)) {
            console.warn("La respuesta del servidor no es un arreglo válido.");
            return []; // Retornar una lista vacía si la respuesta no es un arreglo
        }

        return respuestaFinal;
    } catch (error) {
        console.error("Error al consultar las mercancías:", error);
        return []; // Retornar una lista vacía en caso de error
    }
}
