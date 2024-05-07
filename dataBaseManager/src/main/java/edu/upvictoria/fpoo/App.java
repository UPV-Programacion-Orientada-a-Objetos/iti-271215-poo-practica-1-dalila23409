package edu.upvictoria.fpoo;
import java.io.*;

public class App {
    public static void App(String[] args) {
        SimpleBaseDeDatos db = new SimpleBaseDeDatos("ruta/por/defecto");
        db.usar("nueva/ruta/de/trabajo");
        db.mostrarTablas();
    }
}
