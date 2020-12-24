package com.example.tt_logging.Persona_Cuidado;

public class ListPersonaCuidado {

    private String color;
    private String name_persona;
    private String parentezco;
    private String fecha_cuidado;
    private String telefono;
    private String id_personahelp;

    public ListPersonaCuidado(String color, String name_persona,String parentezco, String fecha_cuidado, String telefono) {
        this.color = color;
        this.name_persona = name_persona;
        this.fecha_cuidado = fecha_cuidado;
        this.telefono = telefono;
        this.parentezco = parentezco;
        id_personahelp = name_persona+"-"+parentezco+"-"+fecha_cuidado+"-"+telefono+"/";
    }

    public ListPersonaCuidado(String id_persona){
        color = "#000000";
        id_personahelp = id_persona+"/";
        String dato = "";
        int i=0;
        int j=0;
        int bandera = 0;
        int bandera2 =0;
        do{
            bandera = 0;
            do {
                if (id_persona.charAt(i) == '-'){
                    i++;
                    System.out.println("valor de J"+j);
                    j++;
                    System.out.println("valor de J aumento"+j);
                    bandera=1;
                }else {
                    dato = dato + id_persona.charAt(i);
                    i++;
                }
                if (id_persona.charAt(i) == '/') {
                    bandera2 = 1;
                    bandera = 1;
                }
            }while (bandera == 0);

            switch (j){
                case 1:{
                    //Nombre
                    name_persona = dato;
                    System.out.println("Persona: "+name_persona);
                    dato="";
                    break;
                }
                case 2:{
                    //parentezco
                    parentezco = dato;
                    System.out.println("parentezco: "+parentezco);
                    dato="";
                    break;
                }
                case 3:{
                    //fecha
                    fecha_cuidado = dato;
                    System.out.println("Fecha: "+fecha_cuidado);
                    dato="";
                    break;
                }
                case 4:{
                    //telefono
                    telefono = dato;
                    System.out.println("Telefono: "+telefono);
                    dato="";
                    break;
                }
                default:{
                    break;
                }
            }
        }while(bandera2 != 1);

    }

    public String getParentezco() {
        return parentezco;
    }

    public void setParentezco(String parentezco) {
        this.parentezco = parentezco;
    }

    public String getId_personahelp() {
        return id_personahelp;
    }

    public void setId_personahelp(String id_personahelp) {
        this.id_personahelp = id_personahelp;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getName_persona() {
        return name_persona;
    }

    public void setName_persona(String name_persona) {
        this.name_persona = name_persona;
    }

    public String getFecha_cuidado() {
        return fecha_cuidado;
    }

    public void setFecha_cuidado(String fecha_cuidado) {
        this.fecha_cuidado = fecha_cuidado;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }
}
