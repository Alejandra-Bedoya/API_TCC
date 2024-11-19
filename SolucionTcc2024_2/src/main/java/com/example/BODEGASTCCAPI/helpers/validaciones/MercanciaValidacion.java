package com.example.BODEGASTCCAPI.helpers.validaciones;

import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class MercanciaValidacion {

    /**
     * Valida que el volumen sea un valor positivo.
     *
     * @param volumen el volumen a validar
     * @return true si el volumen es mayor que 0, false en caso contrario
     */
    public boolean validarVolumen(Double volumen) {
        return volumen != null && volumen > 0;
    }

    /**
     * Valida que el peso sea un valor positivo.
     *
     * @param peso el peso a validar
     * @return true si el peso es mayor que 0, false en caso contrario
     */
    public boolean validarPeso(Double peso) {
        return peso != null && peso > 0;
    }

    /**
     * Valida que el nombre tenga un máximo de 50 caracteres
     * y solo contenga letras y espacios.
     *
     * @param nombre el nombre a validar
     * @return true si el nombre es válido, false en caso contrario
     */
    public boolean validarNombre(String nombre) {
        if (nombre == null || nombre.length() > 50) {
            return false;
        }
        return nombre.matches("^[a-zA-ZÀ-ÿ\\s]+$"); // Permite letras con acentos y espacios
    }

    /**
     * Valida que la fecha de ingreso sea anterior o igual a la fecha de salida.
     *
     * @param fechaIngreso la fecha de ingreso a validar
     * @param fechaSalida  la fecha de salida a validar
     * @return true si la fecha de ingreso es anterior o igual a la fecha de salida, false en caso contrario
     */
    public boolean validarFechas(LocalDate fechaIngreso, LocalDate fechaSalida) {
        if (fechaIngreso == null || fechaSalida == null) {
            return false; // Las fechas no deben ser nulas
        }
        return !fechaIngreso.isAfter(fechaSalida); // Verifica que fechaIngreso no sea posterior a fechaSalida
    }
}
