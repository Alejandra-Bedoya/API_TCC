import { registrarZonaBodega } from "../services/servicioZonaBodega.js";

// Evento para registrar la zona de bodega
document.getElementById("botonRegistrarZona").addEventListener("click", async function (evento) {
    evento.preventDefault();

    // Captura y preparación de datos del formulario
    let datosZona = {
        nombreZona: document.getElementById("nombreZona").value.trim(),
        capacidadMaximaVolumen: parseFloat(document.getElementById("capacidadMaxVolumen").value),
        capacidadMaximaPeso: parseFloat(document.getElementById("capacidadMaxPeso").value),
    };

    // Validación básica de campos vacíos
    if (!datosZona.nombreZona || isNaN(datosZona.capacidadMaximaVolumen) || isNaN(datosZona.capacidadMaximaPeso)) {
        Swal.fire({
            title: "Campos incompletos",
            text: "Por favor, completa todos los campos.",
            icon: "warning"
        });
        return;
    }

    try {
        // Llamada al servicio para registrar la zona
        let respuesta = await registrarZonaBodega(datosZona);
        
        // Mostrar mensaje de éxito y limpiar el formulario
        Swal.fire({
            title: "¡Registro exitoso!",
            text: "Zona registrada con éxito.",
            icon: "success"
        });

        limpiarFormularioZona();

    } catch (error) {
        // Manejo de errores
        Swal.fire({
            title: "Error",
            text: "No se pudo registrar la zona: " + error.message,
            icon: "error"
        });
    }
});

// Función para limpiar los campos del formulario después del registro
function limpiarFormularioZona() {
    document.getElementById("nombreZona").value = "";
    document.getElementById("capacidadMaxVolumen").value = "";
    document.getElementById("capacidadMaxPeso").value = "";
}
