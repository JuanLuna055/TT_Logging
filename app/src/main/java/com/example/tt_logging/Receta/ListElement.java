package com.example.tt_logging.Receta;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class ListElement implements Serializable {
    private String color;
    private String medicamento;
    private String recordatorio;
    private String status;
    private int cantidad;
    private Calendar inicio;
    private Calendar termina;
    private String id_medicamento;

// private int id_img;

    ///Hola perro como estas

    public ListElement(ListElement item){
        color = item.getColor();
        medicamento = item.medicamento;
        recordatorio = item.recordatorio;
        status = item.status;
        cantidad = item.cantidad;
    }

    public ListElement(String color, String medicamento, String recordatorio, String status, int cantidad, Calendar inicio, Calendar terminal, ArrayList<String> hora) {
        this.color = color;
        this.medicamento = medicamento;
        this.recordatorio = recordatorio;
        this.status = status;
        this.cantidad = cantidad;
        this.inicio = inicio;
        this.termina = terminal;

        id_medicamento = medicamento+"-r"+recordatorio+"-s"+status+"-c"+cantidad+"-i"+"a-"+
                inicio.get(Calendar.YEAR)+"m-"+inicio.get(Calendar.MONTH)+"-d"+inicio.get(Calendar.DAY_OF_MONTH)+"-h"+inicio.get(Calendar.HOUR_OF_DAY)+
                "t-"+"a-"+terminal.get(Calendar.YEAR)+"m-"+terminal.get(Calendar.MONTH)+"-d"+terminal.get(Calendar.DAY_OF_MONTH);

        switch (hora.size()){
            case 1:{
                id_medicamento=id_medicamento+hora.get(0);
                break;
            }
            case 2:{
                id_medicamento=id_medicamento+hora.get(0)+hora.get(1);
                break;
            }
            case 3:{
                id_medicamento=id_medicamento+hora.get(0)+hora.get(1)+hora.get(2);
                break;
            }
            case 4:{
                id_medicamento=id_medicamento+hora.get(0)+hora.get(1)+hora.get(2)+hora.get(3);
                break;
            }
            case 5:{
                id_medicamento=id_medicamento+hora.get(0)+hora.get(0)+hora.get(0)+hora.get(0)+hora.get(0);
                break;
            }
            case 6:{
                id_medicamento=id_medicamento+hora.get(0)+hora.get(0)+hora.get(0)+hora.get(0)+hora.get(0)+hora.get(0);
                break;
            }
            case 7:{
                id_medicamento=id_medicamento+hora.get(0)+hora.get(0)+hora.get(0)+hora.get(0)+hora.get(0)+hora.get(0)+hora.get(0);
                break;
            }
            case 8:{
                id_medicamento=id_medicamento+hora.get(0)+hora.get(0)+hora.get(0)+hora.get(0)+hora.get(0)+hora.get(0)+hora.get(0)+hora.get(0);
                break;
            }
            case 9:{
                id_medicamento=id_medicamento+hora.get(0)+hora.get(0)+hora.get(0)+hora.get(0)+hora.get(0)+hora.get(0)+hora.get(0)+hora.get(0)+hora.get(0);
                break;
            }
            case 10:{
                id_medicamento=id_medicamento+hora.get(0)+hora.get(0)+hora.get(0)+hora.get(0)+hora.get(0)+hora.get(0)+hora.get(0)+hora.get(0)+hora.get(0)+hora.get(0);
                break;
            }
            case 11:{
                id_medicamento=id_medicamento+hora.get(0)+hora.get(0)+hora.get(0)+hora.get(0)+hora.get(0)+hora.get(0)+hora.get(0)+hora.get(0)+hora.get(0)+hora.get(0)+hora.get(0);
                break;
            }
            case 12:{
                id_medicamento=id_medicamento+hora.get(0)+hora.get(0)+hora.get(0)+hora.get(0)+hora.get(0)+hora.get(0)+hora.get(0)+hora.get(0)+hora.get(0)+hora.get(0)+hora.get(0)+hora.get(0);
                break;
            }
            case 24:{
                id_medicamento=id_medicamento+hora.get(0)+hora.get(0)+hora.get(0)+hora.get(0)+hora.get(0)+hora.get(0)+hora.get(0)+hora.get(0)+hora.get(0)+hora.get(0)+hora.get(0)+hora.get(0)+hora.get(0);
                break;
            } default:{
                break;
            }

        }
    //  this.id_img = id_img;
    }
/*
    public int getId_img() {
        return id_img;
    }

    public void setId_img(int id_img) {
        this.id_img = id_img;
    }

 */
    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getMedicamento() {
        return medicamento;
    }

    public void setMedicamento(String medicamento) {
        this.medicamento = medicamento;
    }

    public String getRecordatorio() {
        return recordatorio;
    }

    public void setRecordatorio(String recordatorio) {
        this.recordatorio = recordatorio;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getCantidad() {
        return cantidad;
    }

    public Calendar getTermina() {
        return termina;
    }

    public void setTermina(Calendar termina) {
        this.termina = termina;
    }

    public Calendar getInicio() {
        return inicio;
    }

    public void setInicio(Calendar inicio) {
        this.inicio = inicio;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }
}
