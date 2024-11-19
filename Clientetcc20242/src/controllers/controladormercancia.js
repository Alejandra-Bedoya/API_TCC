import { registrarMercancia } from "../services/servicioMercancia.js";
import { consultarZonas } from "../services/servicioZonaBodega.js";

// Referencias a los elementos del formulario
let botonRegistroMercancia = document.getElementById("botonmercancia");
let pesoMercancia = document.getElementById("pesomercancia");
let volumenMercancia = document.getElementById("volumenmercancia");
let nombreMercancia = document.getElementById("nombremercancia");
let tipoDestinatarioMercancia = document.getElementById("tipodestinatariomercancia");
let departamentoMercancia = document.getElementById("departamentomercancia");
let ciudadMercancia = document.getElementById("ciudadmercancia");
let nombreDestinatarioMercancia = document.getElementById("nombredestinatariomercancia");
let direccionMercancia = document.getElementById("direccionmercancia");
let fechaIngresoMercancia = document.getElementById("fechaingresomercancia");
let fechaSalidaMercancia = document.getElementById("fechasalidamercancia");
let selectZona = document.getElementById("idzona"); // Selector dinámico para idZona

// Cargar zonas dinámicamente en el select
async function cargarZonas() {
    try {
        const zonas = await consultarZonas();
        selectZona.innerHTML = ""; // Limpia las opciones existentes
        zonas.forEach((zona) => {
            let opcion = document.createElement("option");
            opcion.value = zona.idZona;
            opcion.textContent = `${zona.nombreZona} (ID: ${zona.idZona})`;
            selectZona.appendChild(opcion);
        });
    } catch (error) {
        console.error("Error al cargar las zonas:", error);
        Swal.fire({
            title: "Error",
            text: "No se pudieron cargar las zonas de bodega.",
            icon: "error"
        });
    }
}

// Detectar evento de clic en el botón de registro
botonRegistroMercancia.addEventListener("click", async function (evento) {
    evento.preventDefault();

    // Obtener el idZona seleccionado
    let idZonaSeleccionada = parseInt(selectZona.value);

    // Crear objeto con los datos de la mercancía
    let objetoMercancia = {
        volumen: parseFloat(volumenMercancia.value),
        peso: parseFloat(pesoMercancia.value),
        nombre: nombreMercancia.value,
        tipoDestinatario: tipoDestinatarioMercancia.value,
        nombreDestinatario: nombreDestinatarioMercancia.value,
        departamento: departamentoMercancia.value,
        ciudad: ciudadMercancia.value,
        direccion: direccionMercancia.value,
        fechaIngreso: fechaIngresoMercancia.value,
        fechaSalida: fechaSalidaMercancia.value,
        zonabodega: {
            idZona: idZonaSeleccionada
        }
    };

    try {
        // Llamado al API para registrar mercancía
        const respuesta = await registrarMercancia(objetoMercancia);
        Swal.fire({
            title: "¡Registro exitoso!",
            text: "Mercancía registrada con éxito.",
            icon: "success"
        });

        // Limpiar el formulario después de un registro exitoso
        limpiarFormulario();

        // Actualizar la lista de mercancías
        actualizarListaMercancias();
    } catch (error) {
        Swal.fire({
            title: "Error",
            text: "No se pudo registrar la mercancía: " + error.message,
            icon: "error"
        });
    }
});

// Función para limpiar el formulario después de un registro exitoso
function limpiarFormulario() {
    pesoMercancia.value = "";
    volumenMercancia.value = "";
    nombreMercancia.value = "";
    tipoDestinatarioMercancia.value = "";
    departamentoMercancia.value = "";
    ciudadMercancia.value = "";
    nombreDestinatarioMercancia.value = "";
    direccionMercancia.value = "";
    fechaIngresoMercancia.value = "";
    fechaSalidaMercancia.value = "";
    selectZona.selectedIndex = 0; // Restablecer el select al valor por defecto
}

// Función para consultar y actualizar la lista de mercancías
async function actualizarListaMercancias() {
    try {
        // Limpia la lista de mercancías antes de cargar nuevas
        fila.innerHTML = "";

        const respuesta = await consultarMercancias();
        respuesta.forEach(function (mercancia) {
            let columna = document.createElement("div");
            columna.classList.add("col");

            let tarjeta = document.createElement("div");
            tarjeta.classList.add("card", "h-100", "shadow", "p-5");

            let nombreMercancia = document.createElement("h3");
            nombreMercancia.textContent = mercancia.nombre;

            let fechaIngresoMercancia = document.createElement("h4");
            fechaIngresoMercancia.textContent = "Fecha ingreso: " + mercancia.fechaIngreso;

            // Añadir más detalles de la mercancía
            let pesoMercancia = document.createElement("p");
            pesoMercancia.textContent = "Peso: " + mercancia.peso + " kg";

            let volumenMercancia = document.createElement("p");
            volumenMercancia.textContent = "Volumen: " + mercancia.volumen + " m³";

            let nombreZona = document.createElement("p");
            nombreZona.textContent = "Zona: " + (mercancia.nombreZona || "No especificada");

            // Agregar los elementos a la tarjeta
            tarjeta.appendChild(nombreMercancia);
            tarjeta.appendChild(fechaIngresoMercancia);
            tarjeta.appendChild(pesoMercancia);
            tarjeta.appendChild(volumenMercancia);
            tarjeta.appendChild(nombreZona);

            // Añadir tarjeta a la columna y luego a la fila
            columna.appendChild(tarjeta);
            fila.appendChild(columna);
        });
    } catch (error) {
        console.error("Error al consultar mercancías:", error);
        Swal.fire({
            title: "Error",
            text: "No se pudo cargar la lista de mercancías.",
            icon: "error"
        });
    }
}

// Llamar a la función para cargar la lista de mercancías al cargar la página
let fila = document.getElementById("fila");
actualizarListaMercancias();

// Llamar a la función para cargar las zonas disponibles
cargarZonas();
