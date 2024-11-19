package com.example.BODEGASTCCAPI.helpers.mensajes;

public enum Mensaje {

    PESO_NEGATIVO("El peso no puede ser negativo"),
    VOLUMEN_NEGATIVO("El volumen no puede ser negativo"),
    FECHA_INVALIDA("Revisa la fecha ingresada"),
    NOMBRE_INVALIDO("El nombre es inválido, debe contener solo letras y tener máximo 50 caracteres"),
    FECHA_INGRESO_POSTERIOR("La fecha de ingreso no puede ser posterior a la fecha de salida");

    private final String mensaje;

    // Constructor del enum
    Mensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    // Getter para obtener el mensaje
    public String getMensaje() {
        return mensaje;
    }
}
