import { registrarZonaBodega, consultarZonas } from "../services/servicioZonaBodega.js";

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

        // Actualizar el select de zonas después del registro
        cargarZonas();

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

// Referencia al select de zonas (si existe en la página)
let selectZona = document.getElementById("idzona");

// Función para cargar zonas desde el backend y llenar el select
export async function cargarZonas() {
    try {
        // Consultar las zonas desde el backend
        const zonas = await consultarZonas();

        // Verificar si el elemento select existe en el DOM
        if (!selectZona) {
            console.warn("El elemento con id 'idzona' no existe en el DOM.");
            return;
        }

        // Limpiar las opciones existentes
        selectZona.innerHTML = "";

        // Agregar opciones al select
        zonas.forEach((zona) => {
            let opcion = document.createElement("option");
            opcion.value = zona.idZona; // Asignar el idZona como valor de la opción
            opcion.textContent = `${zona.nombreZona} (ID: ${zona.idZona})`;
            selectZona.appendChild(opcion);
        });

        console.log("Zonas cargadas exitosamente en el select.");
    } catch (error) {
        console.error("Error al cargar las zonas desde el backend:", error);

        // Mostrar un mensaje de error al usuario
        Swal.fire({
            title: "Error",
            text: "No se pudieron cargar las zonas de almacenamiento.",
            icon: "error"
        });
    }
}

// Llamar a la función para cargar las zonas al cargar la página
cargarZonas();
