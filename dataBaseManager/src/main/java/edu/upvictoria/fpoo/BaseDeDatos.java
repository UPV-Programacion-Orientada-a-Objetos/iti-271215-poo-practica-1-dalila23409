package edu.upvictoria.fpoo;

public class BaseDeDatos {
    private String dirTrabajo;

    public BaseDeDatos(String dirTrabajo) {
        this.dirTrabajo = dirTrabajo;
    }

    public void usar(String ruta) {
        this.dirTrabajo = ruta;
    }

    public void mostrarTablas() {
        Tabla.mostrar(dirTrabajo);
    }

    public void crearTabla(String nombreTabla, String[] columnas) {
        Tabla.crear(dirTrabajo, nombreTabla, columnas);
    }

    public void eliminarTabla(String nombreTabla) {
        Tabla.eliminar(dirTrabajo, nombreTabla);
    }

    public void insertar(String nombreTabla, String[] valores) {
        Tabla.insertar(dirTrabajo, nombreTabla, valores);
    }

    public void eliminar(String nombreTabla, String condicion) {
        Tabla.eliminar(dirTrabajo, nombreTabla, condicion);
    }

    public void actualizar(String nombreTabla, String[] columnas, String condicion) {
        Tabla.actualizar(dirTrabajo, nombreTabla, columnas, condicion);
    }

    public void seleccionar(String nombreTabla, String columnas, String condicion) {
        Tabla.seleccionar(dirTrabajo, nombreTabla, columnas, condicion);
    }
}
