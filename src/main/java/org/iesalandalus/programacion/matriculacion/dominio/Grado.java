package org.iesalandalus.programacion.matriculacion.dominio;

public enum Grado {
    GDCFGB("GDCFGB"),GDCFGM("GDCFGM"),GDCFGS("GDCFGS");
    private String cadenaAMostrar;
    private Grado(String cadenaAMostrar)
    {
        this.cadenaAMostrar=cadenaAMostrar;
    }
    public String imprimir() {
        int digito=0;
        if (cadenaAMostrar == GDCFGB.cadenaAMostrar) {
            digito=0;
        } else if (cadenaAMostrar == GDCFGM.cadenaAMostrar){
            digito=1;
        } else {
            digito=2;
        }
        return digito+".-"+cadenaAMostrar;
    }
    //Crear cadenaAMostrar//

    @Override
    public String toString() {
        return "Grado seleccionado:"  + cadenaAMostrar;
    }
}
