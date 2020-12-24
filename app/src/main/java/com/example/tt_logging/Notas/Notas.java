package com.example.tt_logging.Notas;

public class Notas {
    private String id_nota;
    private String color;
    private String nota;
    private String Fecha;


    public String getId_nota() {
        return id_nota;
    }

    public void setId_nota(String id_nota) {
        this.id_nota = id_nota;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public Notas(String nota, String fecha) {
        this.nota = nota;
        Fecha = fecha;
        color = "#000000";
    }
    public String getNota() {
        return nota;
    }

    public void setNota(String nota) {
        this.nota = nota;
    }

    public String getFecha() {
        return Fecha;
    }

    public void setFecha(String fecha) {
        Fecha = fecha;
    }


}
