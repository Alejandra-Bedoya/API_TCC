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
            throw new Error(`Error ${respuestaServidor.status}: ${respuestaServidor.statusText}`);
        }
        
        let respuestaFinal = await respuestaServidor.json();
        return respuestaFinal;
        
    } catch (error) {
        console.error("Error al consultar las mercancías:", error);
        return null; // O puedes devolver un mensaje de error o lanzar el error
    }
}
