// src/services/servicioMercancia.js
import { API_URL } from "../config.js";

export async function registrarMercancia(datosMercancia) {

    // Construye la URL usando la configuración base
    const URL = `${API_URL}/mercancias`;

    // Configuración de la petición
    let peticion = {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify(datosMercancia)
    };

    try {
        // Llamado a la promesa fetch
        let respuestaServidor = await fetch(URL, peticion);

        // Verificar si la respuesta fue exitosa
        if (!respuestaServidor.ok) {
            // Si la respuesta no es exitosa, arrojar un error para manejarlo en el controlador
            throw new Error(`Error ${respuestaServidor.status}: ${respuestaServidor.statusText}`);
        }

        // Si todo está bien, retornar la respuesta JSON
        let respuestaFinal = await respuestaServidor.json();
        return respuestaFinal;

    } catch (error) {
        // En caso de error, loguear el error y retornar el error
        console.error("Error al registrar la mercancía:", error);
        throw error; // Re-lanza el error para que sea manejado por quien llame a esta función
    }
}
