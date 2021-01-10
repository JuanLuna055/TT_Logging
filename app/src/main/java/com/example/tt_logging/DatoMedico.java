package com.example.tt_logging;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.io.Serializable;
import java.util.Calendar;

public class DatoMedico implements Serializable {
    private String tipo;
    private float var;
    private String metrica;
    private String fecha;
    private String id_dato;

    public DatoMedico(String tipo, float var, String metrica, String fecha) {
        this.tipo = tipo;
        this.var = var;
        this.metrica = metrica;
        this.fecha = fecha;//get(Calendar.DAY_OF_MONTH)+"/"+fecha.get(Calendar.MONTH)+"/"+fecha.get(Calendar.YEAR);
        this.id_dato = tipo+"-"+var+"-"+metrica+"-"+fecha;

        // id_dato = peso-80-Kg-10-11-1999
    }

    public DatoMedico(String id_dato){
        String aux = id_dato;
        String[] parts = aux.split("-");
        this.tipo = parts[0];
        this.var = Float.parseFloat(parts[1]);
        this.metrica = parts[2];
        this.fecha = parts[3];
        this.id_dato = id_dato;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public float getVar() {
        return var;
    }

    public void setVar(float var) {
        this.var = var;
    }

    public String getMetrica() { return metrica; }

    public void setMetrica(String metrica){ this.metrica = metrica; }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;//.get(Calendar.DAY_OF_MONTH)+"/"+fecha.get(Calendar.MONTH)+"/"+fecha.get(Calendar.YEAR);
    }

    public String getId_dato() {
        return id_dato;
    }
}
