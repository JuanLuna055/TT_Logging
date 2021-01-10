package com.example.tt_logging;

public class Medicamento {
    private String nombre;
    private String efecto;


    public Medicamento(String nombre, String efecto) {
        this.nombre = nombre;
        this.efecto = efecto;
    }
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEfecto() {
        return efecto;
    }

    public void setEfecto(String efecto) {
        this.efecto = efecto;
    }
}
