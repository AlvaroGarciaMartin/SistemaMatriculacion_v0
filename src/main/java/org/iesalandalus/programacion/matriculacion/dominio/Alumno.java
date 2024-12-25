package org.iesalandalus.programacion.matriculacion.dominio;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.Objects;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class Alumno {

  private static final String ER_TELEFONO= "^\\d{9}$";
  private static final String ER_CORREO="^\\w+([-+.']\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*$"; ;
  private static final String ER_DNI="^(\\d{8})([A-Za-z]{1})$";
  public static final String FORMATO_FECHA="dd/MM/yyyy";
  private static final String ER_NIA="^([A-Za-z]{4})(\\d{3})$";
  private static final int MIN_EDAD_ALUMNADO = 16;
  private String nombre;
  private String telefono;
  private String correo;
  private String dni;
  private LocalDate fechaNacimiento;
  private String nia;

//constructor Alumno CON PARAMETROS
  public Alumno(String nombre,String dni,String correo,String telefono,LocalDate fechaNacimiento){
    setNombre(nombre);
    setDni(dni);
    setCorreo(correo);
    setTelefono(telefono);
    setFechaNacimiento(fechaNacimiento);
    setNia();
  }
  //constructor copia de Alumno
  public Alumno(Alumno alumno){
    if (alumno==null){
      throw new NullPointerException("ERROR: No es posible copiar un alumno nulo.");
    }
    setNombre(alumno.getNombre());
    setDni(alumno.getDni());
    setCorreo(alumno.getCorreo());
    setTelefono(alumno.getTelefono());
    setFechaNacimiento(alumno.getFechaNacimiento());
    setNia(alumno.getNia());
  }

  //metodo para eliminar los espacios en blanco
  private String formateaNombre(String nombre) {
    String [] nombreNormalizado=nombre.split(" ");


    // StringBuilder para construir el nombre formateado.
    StringBuilder nombreFormateado = new StringBuilder();

    // Recorrer cada palabra.
    for (String palabra : nombreNormalizado) {
      // Convertir la primera letra a mayúsculas y el resto a minúsculas.
      if (palabra.length() > 0) {
        String palabraFormateada = palabra.substring(0, 1).toUpperCase() + palabra.substring(1).toLowerCase();
        nombreFormateado.append(palabraFormateada).append(" ");
      }
    }

    // Eliminar el último espacio adicional.
    return nombreFormateado.toString().trim();


  }
  private boolean comprobarLetraDni() {
    String udni=getDni();
    boolean resultado = false;
    int numero;
    char letra;
    Pattern patron=Pattern.compile("^(\\d{8})([A-Za-z]{1})$");
    Matcher comparador=patron.matcher(udni);
    char[] LETRAS_DNI = {'T', 'R', 'W', 'A', 'G', 'M', 'Y', 'F', 'P', 'D', 'X', 'B', 'N', 'J', 'Z', 'S', 'Q', 'V', 'H', 'L', 'C', 'K', 'E'};
    if (comparador.matches()) {
      numero = Integer.parseInt(comparador.group(1));
      letra = comparador.group(2).charAt(0);

    } else {

      return false;

    }

      int numeroDivido=numero/23;
      int numeroMultiplicado=numeroDivido*23;
      int posicionLetra=numero-numeroMultiplicado;

      if (letra==LETRAS_DNI[posicionLetra]) {
        resultado=true;

      }


    return resultado;
  }
  private String[] getIniciales() {
      String [] iniciales = nombre.split(" ");


    // StringBuilder para construir el nombre formateado.
    StringBuilder inicialesFormateado = new StringBuilder();

    // Recorrer cada palabra.
    for (String inicial : iniciales) {
      // Convertir la primera letra a mayúsculas y el resto a minúsculas.
      if (inicial.length() > 0) {
        String palabraFormateada = inicial.substring(0, 1).toUpperCase();
        inicialesFormateado.append(palabraFormateada).append("");

      }
    }

      return inicialesFormateado.toString().split("");
  }

//geters y seters
  public String getNia() {

    return nia;
  }
  private String setNia() {
    String nNombre;
    String nDni;
    nNombre=nombre.substring(0,4).toLowerCase();
    nDni=dni.substring(5,8);
    nia=nNombre+nDni;
    return nombre;
  }

  private void setNia(String nia) {

    if (!nia.matches(ER_NIA)) {
      throw new IllegalArgumentException("Nia incorrecto.");
    } else if (nia == null) {
      throw new NullPointerException("El nia no puede ser nulo");
    }  else if (!nia.substring(0,4).equals(nombre.substring(0,4).toLowerCase()) && !nia.substring(5,7).equals(dni.substring(5,7)) ) {
      throw new IllegalArgumentException("El nia no es correcto");
    }

    this.nia = nia;
  }

  public String getNombre() {

    return nombre;
  }

  public void setNombre(String nombre) {
    if (nombre==null){
      throw new NullPointerException("ERROR: El nombre de un alumno no puede ser nulo.");
    }
    if (nombre.isEmpty() || nombre.isBlank()){
      throw new IllegalArgumentException("ERROR: El nombre de un alumno no puede estar vacío.");
    }

    this.nombre = formateaNombre(nombre);
  }

  public String getTelefono() {

    return telefono;
  }

  public void setTelefono(String telefono) {
    if (!telefono.matches(ER_TELEFONO)) {
      throw new IllegalArgumentException("ERROR: El teléfono del alumno no tiene un formato válido.");
    }else if (telefono == null) {
      throw new NullPointerException("El telefono no puede ser nulo");
    } else if (telefono.isBlank()) {
      throw new IllegalArgumentException("El telefono no puede estar en blanco");
    } else if (telefono.isEmpty()) {
      throw new IllegalArgumentException("El telefono no puede estar vacio");
    } else if (telefono.length()!=9) {
      throw new IllegalArgumentException("El telefono no es correcto");
    }
    this.telefono = telefono;
  }


  public String getCorreo() {

    return correo;
  }

  public void setCorreo(String correo) {
    if (correo==null){
      throw new NullPointerException("ERROR: El correo de un alumno no puede ser nulo.");
    }
    if (correo.isEmpty() || correo.isBlank()){
      throw new IllegalArgumentException("ERROR: El correo del alumno no tiene un formato válido.");
    }
    if (!correo.matches(ER_CORREO)){
      throw new IllegalArgumentException("ERROR: El correo del alumno no tiene un formato válido.");
    }
    this.correo = correo;
  }

  public String getDni() {

    return dni;
  }

  private void setDni(String dni) {
    if (dni == null) {
      throw new NullPointerException("ERROR: El dni de un alumno no puede ser nulo.");
    } else if (comprobarLetraDni()==false) {
      throw new IllegalArgumentException("ERROR: El dni no es correcto");
    }
    this.dni = dni;
  }

  public LocalDate getFechaNacimiento() {

    return fechaNacimiento;
  }

  private void setFechaNacimiento(LocalDate fechaNacimiento) {
    if (fechaNacimiento == null) {
      throw new NullPointerException("ERROR: La fecha de nacimiento no puede ser nula");
    }else if (LocalDate.now().getYear() - fechaNacimiento.getYear()  < MIN_EDAD_ALUMNADO) {
      throw new IllegalArgumentException("ERROR: El Alumno no puede tener menos de 16 años");
    }
    this.fechaNacimiento = fechaNacimiento;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Alumno alumno = (Alumno) o;
    return Objects.equals(ER_DNI, alumno.ER_DNI) && Objects.equals(dni, alumno.dni);
  }

  @Override
  public int hashCode() {
    return Objects.hash(ER_DNI, dni);
  }

  public String imprimir(){
    return String.format("nombre=%s (%s), DNI=%s, correo=%s, teléfono=%s, fecha nacimiento=%s",this.getNombre(), this.getIniciales(), this.getDni(), this.getCorreo(), this.getTelefono(), this.getFechaNacimiento().format(DateTimeFormatter.ofPattern(FORMATO_FECHA)));
  }

  @Override
  public String toString() {

    return String.format("Número de Identificación del Alumnado (NIA)=%s " + "nombre=%s (%s), DNI=%s, correo=%s, teléfono=%s, fecha nacimiento=%s", this.getNia(), imprimir());
  }


}
