package edu.upvictoria.fpoo;

import java.io.*;
import java.util.*;

public class Tabla {
    public static void mostrar(String dirTrabajo) {
        File dir = new File(dirTrabajo);
        File[] archivos = dir.listFiles();
        if (archivos != null) {
            for (File archivo : archivos) {
                if (archivo.isFile()) {
                    System.out.println(archivo.getName().replace(".csv", ""));
                }
            }
        }
    }

    public static void crear(String dirTrabajo, String nombre, String[] cols) {
        try {
            FileWriter escritor = new FileWriter(dirTrabajo + "/" + nombre + ".csv");
            for (int i = 0; i < cols.length; i++) {
                escritor.append(cols[i]);
                if (i != cols.length - 1) {
                    escritor.append(",");
                }
            }
            escritor.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void eliminar(String dirTrabajo, String nombre) {
        File archivo = new File(dirTrabajo + "/" + nombre + ".csv");
        if (archivo.exists()) {
            System.out.println("¿Seguro que desea eliminar la tabla " + nombre + "? (S/N)");
            Scanner scanner = new Scanner(System.in);
            String confirmacion = scanner.nextLine().toUpperCase();
            if (confirmacion.equals("S")) {
                archivo.delete();
                System.out.println("La tabla " + nombre + " ha sido eliminada.");
            } else {
                System.out.println("Operación cancelada.");
            }
        } else {
            System.out.println("La tabla " + nombre + " no existe.");
        }
    }

    public static void insertar(String dirTrabajo, String nombre, String[] valores) {
        try {
            FileWriter escritor = new FileWriter(dirTrabajo + "/" + nombre + ".csv", true);
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

    public static void eliminar(String dirTrabajo, String nombre, String condicion) {
        try {
            File archEnt = new File(dirTrabajo + "/" + nombre + ".csv");
            File archTemp = new File(dirTrabajo + "/temp.csv");

            BufferedReader lector = new BufferedReader(new FileReader(archEnt));
            BufferedWriter escritor = new BufferedWriter(new FileWriter(archTemp));

            String linea;
            String[] headers = lector.readLine().split(",");
            escritor.write(String.join(",", headers));
            escritor.newLine();

            while ((linea = lector.readLine()) != null) {
                if (!cumpleCondicion(linea, condicion, headers)) {
                    escritor.write(linea);
                    escritor.newLine();
                }
            }

            escritor.close();
            lector.close();

            archEnt.delete();
            archTemp.renameTo(archEnt);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void actualizar(String dirTrabajo, String nombre, String[] cols, String condicion) {
        try {
            File archEnt = new File(dirTrabajo + "/" + nombre + ".csv");
            File archTemp = new File(dirTrabajo + "/temp.csv");

            BufferedReader lector = new BufferedReader(new FileReader(archEnt));
            BufferedWriter escritor = new BufferedWriter(new FileWriter(archTemp));

            String linea;
            String[] headers = lector.readLine().split(",");
            escritor.write(String.join(",", headers));
            escritor.newLine();

            while ((linea = lector.readLine()) != null) {
                if (cumpleCondicion(linea, condicion, headers)) {
                    String[] valores = linea.split(",");
                    for (String col : cols) {
                        String[] keyValue = col.split("=");
                        String nombreCol = keyValue[0].trim();
                        String valorCol = keyValue[1].trim();
                        for (int i = 0; i < headers.length; i++) {
                            if (headers[i].equals(nombreCol)) {
                                valores[i] = valorCol;
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

            archEnt.delete();
            archTemp.renameTo(archEnt);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void seleccionar(String dirTrabajo, String nombre, String columnas, String condicion) {
        try {
            File archivo = new File(dirTrabajo + "/" + nombre + ".csv");
            BufferedReader lector = new BufferedReader(new FileReader(archivo));

            String linea;
            String[] headers = lector.readLine().split(",");
            List<Integer> indicesColumnas = obtenerIndicesColumnas(headers, columnas);

            System.out.println(String.join(",", columnas.split(",")));

            while ((linea = lector.readLine()) != null) {
                if (cumpleCondicion(linea, condicion, headers)) {
                    String[] valores = linea.split(",");
                    List<String> valoresSeleccionados = new ArrayList<>();
                    for (int indice : indicesColumnas) {
                        valoresSeleccionados.add(valores[indice]);
                    }
                    System.out.println(String.join(",", valoresSeleccionados));
                }
            }

            lector.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static boolean cumpleCondicion(String fila, String condicion, String[] headers) {
        if (condicion == null || condicion.isEmpty()) {
            return true;
        }

        String[] partesCondicion = condicion.split("(?i) AND |(?i) OR ");

        for (String parte : partesCondicion) {
            if (parte.contains("=")) {
                String[] partes = parte.split("=");
                String nombreCol = partes[0].trim();
                String valorCol = partes[1].trim();
                for (int i = 0; i < headers.length; i++) {
                    if (headers[i].equals(nombreCol)) {
                        String[] valoresFila = fila.split(",");
                        if (valoresFila.length > i && valoresFila[i].equals(valorCol)) {
                            return true;
                        }
                    }
                }
            }
        }

        return false;
    }

    private static List<Integer> obtenerIndicesColumnas(String[] headers, String columnas) {
        List<Integer> indices = new ArrayList<>();
        String[] nombresColumnas = columnas.split(",");
        for (String nombreColumna : nombresColumnas) {
            String nombreCol = nombreColumna.trim();
            for (int i = 0; i < headers.length; i++) {
                if (headers[i].equals(nombreCol)) {
                    indices.add(i);
                    break;
                }
            }
        }
        return indices;
    }
}
