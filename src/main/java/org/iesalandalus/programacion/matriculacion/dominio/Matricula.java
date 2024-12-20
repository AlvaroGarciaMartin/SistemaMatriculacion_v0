package org.iesalandalus.programacion.matriculacion.dominio;

import javax.naming.OperationNotSupportedException;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Matricula {
    public static int MAXIMO_MESES_ANTERIOR_ANULACION=6;
    public static int MAXIMO_DIAS_ANTERIOR_MATRICULA=15;
    public static int MAXIMO_NUMERO_HORAS_MATRICULA=1000;
    public static int MAXIMO_NUMERO_ASIGNATURAS_POR_MATRICULA=10;
    private static final String ER_CURSO_ACADEMICO="\\d{2}-\\d{2}";
    public static final String FORMATO_FECHA= "dd/MM/YYYY";
    private int idMatricula;
    private String cursoAcademico;
    private LocalDate fechaMatriculacion;
    private LocalDate fechaAnulacion;
    private Alumno alumno;
    private Asignatura[] coleccionAsignaturas;

    //constructor con parametros
    public Matricula(int idMatricula, String cursoAcademico, LocalDate fechaMatriculacion, Alumno alumno, Asignatura[] coleccionAsignaturas) {
        setIdMatricula(idMatricula);
        setCursoAcademico(cursoAcademico);
        setFechaMatriculacion(fechaMatriculacion);
        setAlumno(alumno);
        setColeccionAsignaturas(coleccionAsignaturas);
    }
    //constructor copia
    public Matricula(Matricula matricula) {
        setIdMatricula(idMatricula);
        setCursoAcademico(cursoAcademico);
        setFechaMatriculacion(fechaMatriculacion);
        setAlumno(alumno);
        setColeccionAsignaturas(coleccionAsignaturas);
    }

    //metodo para comprobar si la matricula supera el maximo de horas
    private boolean superaMaximoNumeroHorasMatricula(Asignatura[] asignaturasMatricula) {
        boolean resultado=false;
        if (getMAXIMO_NUMERO_ASIGNATURAS_POR_MATRICULA()<10){
          resultado=true;

        }

        return resultado;
    }
    private String asignaturasMatricula() {

        return Arrays.toString(coleccionAsignaturas);
    }

    public int getMAXIMO_MESES_ANTERIOR_ANULACION() {
        return MAXIMO_MESES_ANTERIOR_ANULACION;
    }

    public void setMAXIMO_MESES_ANTERIOR_ANULACION(int MAXIMO_MESES_ANTERIOR_ANULACION) {
        if (MAXIMO_MESES_ANTERIOR_ANULACION > 6) {
            throw new RuntimeException("ERROR: No se pueden anular en menos de 6 meses.");
        }
        this.MAXIMO_MESES_ANTERIOR_ANULACION = MAXIMO_MESES_ANTERIOR_ANULACION;
    }

    public int getMAXIMO_DIAS_ANTERIOR_MATRICULA() {
        return MAXIMO_DIAS_ANTERIOR_MATRICULA;
    }

    public void setMAXIMO_DIAS_ANTERIOR_MATRICULA(int MAXIMO_DIAS_ANTERIOR_MATRICULA) {
        if (MAXIMO_DIAS_ANTERIOR_MATRICULA > 15) {
            throw new RuntimeException("ERROR: No se pueden matricular en menos de 15 dias.");
        }
        this.MAXIMO_DIAS_ANTERIOR_MATRICULA = MAXIMO_DIAS_ANTERIOR_MATRICULA;
    }

    public int getMAXIMO_NUMERO_HORAS_MATRICULA() {
        return MAXIMO_NUMERO_HORAS_MATRICULA;
    }

    public void setMAXIMO_NUMERO_HORAS_MATRICULA(int MAXIMO_NUMERO_HORAS_MATRICULA) throws OperationNotSupportedException {
        if (MAXIMO_NUMERO_HORAS_MATRICULA > 1000) {
            throw new OperationNotSupportedException("ERROR: No se pueden matricular más de 1000 horas.");
        }
        this.MAXIMO_NUMERO_HORAS_MATRICULA = MAXIMO_NUMERO_HORAS_MATRICULA;
    }

    public int getMAXIMO_NUMERO_ASIGNATURAS_POR_MATRICULA() {
        return MAXIMO_NUMERO_ASIGNATURAS_POR_MATRICULA;
    }

    public void setMAXIMO_NUMERO_ASIGNATURAS_POR_MATRICULA(int MAXIMO_NUMERO_ASIGNATURAS_POR_MATRICULA) {
        if (MAXIMO_NUMERO_ASIGNATURAS_POR_MATRICULA > 10) {
            throw new RuntimeException("ERROR: No se pueden matricular más de 10 asignaturas.");

        }
        this.MAXIMO_NUMERO_ASIGNATURAS_POR_MATRICULA = MAXIMO_NUMERO_ASIGNATURAS_POR_MATRICULA;
    }


    public String getFORMATO_FECHA() {
        return FORMATO_FECHA;
    }

    public String getER_CURSO_ACADEMICO() {
        return ER_CURSO_ACADEMICO;
    }


    public int getIdMatricula() {
        return idMatricula;
    }

    public void setIdMatricula(int idMatricula) {
        if (idMatricula < 0) {
            throw new RuntimeException("ERROR: El identificador de una matricula no puede ser negativo.");
        }
        this.idMatricula = idMatricula;
    }

    public String getCursoAcademico() {
        return cursoAcademico;
    }

    public void setCursoAcademico(String cursoAcademico) {
        String regex = "^(\\d{2})-(\\d{2})$";
        Pattern patron = Pattern.compile(regex);
        Matcher coincidencia = patron.matcher(cursoAcademico);
        if (!coincidencia.matches()) {
            throw new RuntimeException("ERROR: El curso académico introducido no es correcto.");
        }
        this.cursoAcademico = cursoAcademico;
    }

    public LocalDate getFechaMatriculacion() {
        return fechaMatriculacion;
    }

    public void setFechaMatriculacion(LocalDate fechaMatriculacion) {
        this.fechaMatriculacion = fechaMatriculacion;
    }

    public LocalDate getFechaAnulacion() {
        return fechaAnulacion;
    }

    public void setFechaAnulacion(LocalDate fechaAnulacion) {
        this.fechaAnulacion = fechaAnulacion;
    }

    public Alumno getAlumno() {
        return alumno;
    }

    public void setAlumno(Alumno alumno) {
        this.alumno = alumno;
    }

    public Asignatura[] getColeccionAsignaturas() {
        return coleccionAsignaturas;
    }

    public void setColeccionAsignaturas(Asignatura[] coleccionAsignaturas) {
        this.coleccionAsignaturas = coleccionAsignaturas;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Matricula matricula = (Matricula) o;
        return idMatricula == matricula.idMatricula;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(idMatricula);
    }

    //crear metodo imprimir
    public String imprimir() {
        return "numero de matricula: "+idMatricula+" curso academico: "+cursoAcademico+" fecha matriculacion: "+fechaMatriculacion+" alumno: "+alumno+" asignaturas matriculadas: "+asignaturasMatricula();
    }
    
    @Override
    public String toString() {
        return "Matricula{" +
                "MAXIMO_MESES_ANTERIOR_ANULACION=" + MAXIMO_MESES_ANTERIOR_ANULACION +
                ", MAXIMO_DIAS_ANTERIOR_MATRICULA=" + MAXIMO_DIAS_ANTERIOR_MATRICULA +
                ", MAXIMO_NUMERO_HORAS_MATRICULA=" + MAXIMO_NUMERO_HORAS_MATRICULA +
                ", MAXIMO_NUMERO_ASIGNATURAS_POR_MATRICULA=" + MAXIMO_NUMERO_ASIGNATURAS_POR_MATRICULA +
                ", ER_CURSO_ACADEMICO='" + ER_CURSO_ACADEMICO + '\'' +
                ", FORMATO_FECHA='" + FORMATO_FECHA + '\'' +
                ", idMatricula=" + idMatricula +
                ", cursoAcademico='" + cursoAcademico + '\'' +
                ", fechaMatriculacion=" + fechaMatriculacion +
                ", fechaAnulacion=" + fechaAnulacion +
                ", alumno=" + alumno +
                ", coleccionAsignaturas=" + Arrays.toString(coleccionAsignaturas) +
                '}';
    }
}
