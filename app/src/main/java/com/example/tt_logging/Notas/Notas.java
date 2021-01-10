package com.example.tt_logging.Notas;

public class Notas {
    private String id_nota;
    private String color;
    private String nota;
    private String Fecha;

    public Notas(String nota, String fecha) {
        this.nota = nota;
        Fecha = fecha;
        color = "#000000";
        id_nota = nota+'-'+fecha+'|';

    }
    public String getId_nota() {
        return id_nota;
    }

    public Notas(String id_nota) {
        this.id_nota = id_nota+'|';
        String dato = "";
        int i = 0;
        color = "#000000";
        int j = 0;
        int bandera = 0;
        int bandera2 = 0;
        //nota, fecha
        do {
            bandera = 0;
            do {
                if (id_nota.charAt(i) == '-') {
                    i++;
                    System.out.println("valor de J" + j);
                    j++;
                    System.out.println("valor de J aumento" + j);
                    bandera = 1;
                } else {
                    dato = dato + id_nota.charAt(i);
                    i++;
                }
                if (id_nota.charAt(i) == '|') {
                    bandera2 = 1;
                    bandera = 1;
                    j++;
                }
            } while (bandera == 0);

            switch (j) {
                case 1: {
                    //Nombre
                    nota = dato;
                    System.out.println("La nota es: " + nota);
                    dato = "";
                    break;
                }
                case 2: {
                    //parentezco
                    Fecha = dato;
                    System.out.println("Fecha: " + Fecha);
                    dato = "";
                    break;
                }
                default:
                    break;
            }
        } while (bandera2 != 1);
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
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
