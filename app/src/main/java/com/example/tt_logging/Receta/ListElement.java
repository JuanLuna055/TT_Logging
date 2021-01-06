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
    private int dosis_consumir;
    private Calendar inicio;
    private Calendar termina;
    private String id_medicamento;
    private int frecuencia;
    private String horas_rec="";

    public void setId_medicamento(String id_medicamento) {
        this.id_medicamento = id_medicamento;
    }
// private int id_img;

    public ListElement(ListElement item){
        color = item.getColor();
        medicamento = item.medicamento;
        recordatorio = item.recordatorio;
        status = item.status;
        cantidad = item.cantidad;
    }


    public ListElement(String color, String medicamento, String recordatorio, String status, int cantidad, Calendar inicio, Calendar terminal, ArrayList<String> hora,int frecuncia) {
        this.color = color;
        this.medicamento = medicamento;
        this.recordatorio = recordatorio;
        this.status = status;
        this.cantidad = cantidad;
        this.inicio = inicio;
        this.termina = terminal;
        this.frecuencia = frecuncia;
        for(int i=0;i<hora.size();i++){
            horas_rec = horas_rec+hora.get(i);
        }

        // termi es la cantidad de dias que dura el tratamiento para el medicamento dado.
        int termi = termina.get(Calendar.DAY_OF_MONTH) - inicio.get(Calendar.DAY_OF_MONTH);
        if(termi < 0){
            termi = -termi;
        }

        dosis_consumir =(int)(termi / (frecuncia+1)) * (hora.size()+1);

        id_medicamento = medicamento+"-"+recordatorio+"-"+status+"-"+cantidad+"-"+frecuncia+"-"+dosis_consumir+"-"+
                inicio.get(Calendar.YEAR)+"-"+inicio.get(Calendar.MONTH)+"-"+inicio.get(Calendar.DAY_OF_MONTH)+"-"
                +terminal.get(Calendar.YEAR)+"-"+terminal.get(Calendar.MONTH)+"-"+terminal.get(Calendar.DAY_OF_MONTH);

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
                id_medicamento=id_medicamento+hora.get(0)+hora.get(1)+hora.get(2)+hora.get(3)+hora.get(4);
                break;
            }
            case 6:{
                id_medicamento=id_medicamento+hora.get(0)+hora.get(1)+hora.get(2)+hora.get(3)+hora.get(4)+hora.get(5);
                break;
            }
            case 7:{
                id_medicamento=id_medicamento+hora.get(0)+hora.get(1)+hora.get(2)+hora.get(3)+hora.get(4)+hora.get(5)+hora.get(6);
                break;
            }
            case 8:{
                id_medicamento=id_medicamento+hora.get(0)+hora.get(1)+hora.get(2)+hora.get(3)+hora.get(4)+hora.get(5)+hora.get(6)+hora.get(7);
                break;
            }
            case 9:{
                id_medicamento=id_medicamento+hora.get(0)+hora.get(1)+hora.get(2)+hora.get(3)+hora.get(4)+hora.get(5)+hora.get(6)+hora.get(7)+hora.get(8);
                break;
            }
            case 10:{
                id_medicamento=id_medicamento+hora.get(0)+hora.get(1)+hora.get(2)+hora.get(3)+hora.get(4)+hora.get(5)+hora.get(6)+hora.get(7)+hora.get(8)+hora.get(9);
                break;
            }
            case 11:{
                id_medicamento=id_medicamento+hora.get(0)+hora.get(1)+hora.get(2)+hora.get(3)+hora.get(4)+hora.get(5)+hora.get(6)+hora.get(7)+hora.get(8)+hora.get(9)+hora.get(10);
                break;
            }
            case 12:{
                id_medicamento=id_medicamento+hora.get(0)+hora.get(1)+hora.get(2)+hora.get(3)+hora.get(4)+hora.get(5)+hora.get(6)+hora.get(7)+hora.get(8)+hora.get(9)+hora.get(10)+hora.get(11);
                break;
            }
            case 24:{
                id_medicamento=id_medicamento+hora.get(0)+hora.get(1)+hora.get(2)+hora.get(3)+hora.get(4)+hora.get(5)+hora.get(6)+hora.get(7)+hora.get(8)+hora.get(9)+hora.get(10)+hora.get(11)+hora.get(12);
                break;
            } default:{
                break;
            }

        }
        System.out.println("EL ID ES: "+id_medicamento);
    //  this.id_img = id_img;
    }

    public ListElement(String id_medi){
        int i=0;
        int j=0;
        color = "#000000";
        inicio = Calendar.getInstance();
        termina =Calendar.getInstance();
        String dato = "";
        int bandera = 0;
        int bandera2 = 0;
        do{
            bandera=0;
            do{

                if(id_medi.charAt(i) == '-'){
                    bandera=1;
                    i++;
                    j++;
                }else{
                    dato = dato + id_medi.charAt(i);
                    i++;
                }
                if (id_medi.charAt(i) == '('){
                    // System.out.println("Entramos a bandera");
                    bandera2=1;
                    bandera=1;
                    j++;
                }
            }while(bandera == 0);
            switch (j){
                case 1:{
                    //Caso variable medicamento
                    String medicina = dato;
                    medicamento = medicina;
                    //System.out.println("Medicina es: "+dato);
                    dato="";
                    break;
                }
                case 2:{
                    //Caso variable recordatorio
                    String record = dato;
                    recordatorio = record;
                    //System.out.println("recordatorio es: "+recordatorio);
                    dato="";
                    break;
                }
                case 3: {
                    //Caso variable status
                    status = dato;
                    //System.out.println("Estatus es: "+status);
                    dato="";
                    break;
                }
                case 4:{
                    //Caso variable repetir
                    String cantid = dato;
                    cantidad = Integer.parseInt(cantid);
                    //System.out.println("Cantidad es: "+ cantidad);
                    dato="";
                    break;
                }
                case 5:{
                    //Caso variable frecuncia
                    frecuencia = Integer.parseInt(dato);
                    //System.out.println("Frecuencia es: "+ frecuencia);
                    dato="";
                    break;
                }
                case 6:{
                    //Caso variable frecuncia
                    dosis_consumir = Integer.parseInt(dato);
                    //System.out.println("dosis a consumir es: "+ dosis_consumir);
                    dato="";
                    break;
                }
                case 7:{
                    //Caso variable Año Fecha Inicio
                    inicio.set(Calendar.YEAR,Integer.parseInt(dato));
                    //System.out.println("Año de inicio es: "+ inicio.get(Calendar.YEAR));
                    dato="";
                    break;
                }
                case 8:{
                    //Caso variable Año Fecha Inicio
                    inicio.set(Calendar.MONTH,Integer.parseInt(dato));
                    //System.out.println("Mes de inicio es: "+ inicio.get(Calendar.MONTH));
                    dato="";
                    break;
                }
                case 9:{
                    //Caso variable Año Fecha Inicio
                    inicio.set(Calendar.DAY_OF_MONTH,Integer.parseInt(dato));
                    //System.out.println("dia de inicio es: "+ inicio.get(Calendar.DAY_OF_MONTH));
                    dato="";
                    break;
                }

                case 10:{
                    //Caso variable Año Fecha Inicio
                    termina.set(Calendar.YEAR,Integer.parseInt(dato));
                    //System.out.println("Año de termino es: "+ termina.get(Calendar.YEAR));
                    dato="";
                    break;
                }
                case 11:{
                    //Caso variable Año Fecha Inicio
                    termina.set(Calendar.MONTH,Integer.parseInt(dato));
                      //System.out.println("mes de termino es: "+ termina.get(Calendar.MONTH));
                    dato="";
                    break;
                }
                case 12:{
                    //Caso variable Año Fecha Inicio
                    //System.out.println("Caso 11"+ dato);
                    termina.set(Calendar.DAY_OF_MONTH,Integer.parseInt(dato));
                     //System.out.println("dia de termino: "+ termina.get(Calendar.DAY_OF_MONTH));
                    dato="";
                    break;
                } default:{
              //      System.out.println("Default"+id_medi.charAt(i));
                    break;
                }
            }
        }while(bandera2 != 1);
        //System.out.println("El ultimo termino es: "+ id_medi.charAt(i));
        do{
            horas_rec = horas_rec + id_medi.charAt(i);
            //System.out.println(horas_rec);
            i++;
        }while (id_medi.charAt(i) != ']');
        horas_rec = horas_rec + ']';

        // termi es la cantidad de dias que dura el tratamiento para el medicamento dado.
        int termi = termina.get(Calendar.DAY_OF_MONTH) - inicio.get(Calendar.DAY_OF_MONTH);
        if(termi < 0){
            termi = -termi;
        }

        System.out.println("******************************************");
        id_medicamento = medicamento+"-"+recordatorio+"-"+status+"-"+cantidad+"-"+frecuencia+"-"+dosis_consumir+"-"+
                inicio.get(Calendar.YEAR)+"-"+inicio.get(Calendar.MONTH)+"-"+inicio.get(Calendar.DAY_OF_MONTH)+"-"
                +termina.get(Calendar.YEAR)+"-"+termina.get(Calendar.MONTH)+"-"+termina.get(Calendar.DAY_OF_MONTH);
        id_medicamento = id_medicamento + horas_rec;
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

    public int getFrecuencia() {
        return frecuencia;
    }

    public void setFrecuencia(int frecuencia) {
        this.frecuencia = frecuencia;
    }

    public int getDosis_consumir() {
        return dosis_consumir;
    }

    public void setDosis_consumir(int dosis_consumir) {
        this.dosis_consumir = dosis_consumir;
    }

    public String getHoras_rec() {
        return horas_rec;
    }

    public void setHoras_rec(String horas_rec) {
        this.horas_rec = horas_rec;
    }

    public String getId_medicamento() {
        id_medicamento="";
        id_medicamento = medicamento+"-"+recordatorio+"-"+status+"-"+cantidad+"-"+frecuencia+"-"+dosis_consumir+"-"+
                inicio.get(Calendar.YEAR)+"-"+inicio.get(Calendar.MONTH)+"-"+inicio.get(Calendar.DAY_OF_MONTH)+"-"
                +termina.get(Calendar.YEAR)+"-"+termina.get(Calendar.MONTH)+"-"+termina.get(Calendar.DAY_OF_MONTH);
        id_medicamento = id_medicamento + horas_rec;

        return id_medicamento;
    }
}
