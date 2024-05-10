package edu.upvictoria.fpoo;
import java.io.*;
import java.util.*;

public class Tabla {
    public static void mostrarTablas(String directorioDeTrabajo) {
        File directorio = new File(directorioDeTrabajo);
        File[] archivos = directorio.listFiles();
        if (archivos != null) {
            for (File archivo : archivos) {
                if (archivo.isFile()) {
                    System.out.println(archivo.getName().replace(".csv", ""));
                }
            }
        }
    }

    public static void crearTabla(String directorioDeTrabajo, String nombreTabla, String[] columnas) {
        try {
            FileWriter escritor = new FileWriter(directorioDeTrabajo + "/" + nombreTabla + ".csv");
            for (int i = 0; i < columnas.length; i++) {
                escritor.append(columnas[i]);
                if (i != columnas.length - 1) {
                    escritor.append(",");
                }
            }
            escritor.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void eliminarTabla(String directorioDeTrabajo, String nombreTabla) {
        File archivo = new File(directorioDeTrabajo + "/" + nombreTabla + ".csv");
        if (archivo.exists()) {
            System.out.println("¿Seguro que desea eliminar la tabla " + nombreTabla + "? (S/N)");
            Scanner scanner = new Scanner(System.in);
            String confirmacion = scanner.nextLine().toUpperCase();
            if (confirmacion.equals("S")) {
                archivo.delete();
                System.out.println("La tabla " + nombreTabla + " ha sido eliminada.");
            } else {
                System.out.println("Operación cancelada.");
            }
        } else {
            System.out.println("La tabla " + nombreTabla + " no existe.");
        }
    }

    public static void insertar(String directorioDeTrabajo, String nombreTabla, String[] valores) {
        try {
            FileWriter escritor = new FileWriter(directorioDeTrabajo + "/" + nombreTabla + ".csv", true);
            for (int i = 0; i < valores.length; i++) {
                escritor.append(valores[i]);
                if (i != valores.length - 1) {
                    escritor.append(",");
                }
            }
            escritor.append("\n");
            escritor.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void eliminar(String directorioDeTrabajo, String nombreTabla, String condicion) {
        try {
            File archivoEntrada = new File(directorioDeTrabajo + "/" + nombreTabla + ".csv");
            File archivoTemporal = new File(directorioDeTrabajo + "/temp.csv");

            BufferedReader lector = new BufferedReader(new FileReader(archivoEntrada));
            BufferedWriter escritor = new BufferedWriter(new FileWriter(archivoTemporal));

            String linea;
            String[] headers = lector.readLine().split(",");
            escritor.write(String.join(",", headers));
            escritor.newLine();

           } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void actualizar(String directorioDeTrabajo, String nombreTabla, String[] columnas, String condicion) {
        try {
            File archivoEntrada = new File(directorioDeTrabajo + "/" + nombreTabla + ".csv");
            File archivoTemporal = new File(directorioDeTrabajo + "/temp.csv");
            File archivorSalida = new File (directorioDeTrabajo+"/"+nombreTabla+".csv");
            BufferedReader lector = new BufferedReader(new FileReader(archivoEntrada));
            BufferedWriter escritor = new BufferedWriter(new FileWriter(archivoTemporal));

            String linea;
            String[] headers = lector.readLine().split(",");
            escritor.write(String.join(",", headers));
            escritor.newLine();

            while ((linea = lector.readLine()) != null) {
                if (evaluarCondicion(linea, condicion, headers)) {
                    String[] valores = linea.split(",");
                    for (String columna : columnas) {
                        String[] keyValue = columna.split("=");
                        String nombreColumna = keyValue[0].trim();
                        String valorColumna = keyValue[2].trim();
                        for (int i = 0; i < headers.length; i++) {
                            if (headers[i].equals(nombreColumna)) {
                                valores[i] = valorColumna;
                                break;
                            }
                        }
                    }
                    linea = String.join(",", valores);
                }
                escritor.write(linea);
                escritor.newLine();
            }
            escritor.close();
            lector.close();

            archivoEntrada.delete();
            archivoTemporal.renameTo(archivoEntrada);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
