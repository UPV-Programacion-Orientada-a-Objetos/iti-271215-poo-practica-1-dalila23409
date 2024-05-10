package edu.upvictoria.fpoo;
import java.io.*;

public class BaseDeDatos {
    private String directorioDeTrabajo;
    public BaseDeDatos(String directorioDeTrabajo) {
        this.directorioDeTrabajo = directorioDeTrabajo;
    }
    public void usar(String ruta) {
        this.directorioDeTrabajo = ruta;
    }
    public void mostrarTablas() {
        Tabla.mostrarTablas(directorioDeTrabajo);
    }
    public void crearTabla(String nombreTabla, String[] columnas) {
        Tabla.crearTabla(directorioDeTrabajo, nombreTabla, columnas);
    }
    public void eliminarTabla(String nombreTabla) {
        Tabla.eliminarTabla(directorioDeTrabajo, nombreTabla);
    }
    public void insertar(String nombreTabla, String[] valores) {
        Tabla.insertar(directorioDeTrabajo, nombreTabla, valores);
    }
    public void eliminar(String nombreTabla, String condicion) {
        Tabla.eliminar(directorioDeTrabajo, nombreTabla, condicion);
    }
    public void actualizar(String nombreTabla, String[] columnas, String condicion) {
        Tabla.actualizar(directorioDeTrabajo, nombreTabla, columnas, condicion);
    }
    public void seleccionar(String nombreTabla, String columnas, String condicion) {
        Tabla.seleccionar(directorioDeTrabajo, nombreTabla, columnas, condicion);
    }
    public void (String nombreTabla, String columnas, String condicion) {

    }
