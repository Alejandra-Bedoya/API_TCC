export async function registrarZonaBodega(datosZona) {
    const URL = "http://localhost:8080/soluciontcc/v1/zonas/registrar";

    let peticion = {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify(datosZona)
    };

    try {
        let respuesta = await fetch(URL, peticion);
        if (!respuesta.ok) {
            throw new Error(`Error ${respuesta.status}: ${respuesta.statusText}`);
        }
        let resultado = await respuesta.json();
        return resultado;
    } catch (error) {
        console.error("Error al registrar la zona de bodega:", error);
        throw error;
    }
}
// servicioZonaBodega.js

// servicioZonaBodega.js

export async function consultarZonas() {
    const URL = "http://localhost:8080/soluciontcc/v1/zonas/listar";
    let peticion = {
        method: "GET",
        headers: { "Content-Type": "application/json" }
    };

    try {
        let respuesta = await fetch(URL, peticion);
        if (!respuesta.ok) {
            throw new Error(`Error ${respuesta.status}: ${respuesta.statusText}`);
        }
        let resultado = await respuesta.json();
        return resultado;
    } catch (error) {
        console.error("Error al consultar las zonas de bodega:", error);
        return { error: "No se pudo obtener la lista de zonas de bodega" };  // Enviar un mensaje en lugar de lanzar error
    }
}

