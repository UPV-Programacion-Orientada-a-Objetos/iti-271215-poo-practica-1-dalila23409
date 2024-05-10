package edu.upvictoria.fpoo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

class CSV {
    public void crearTabla(String rutaArchivo, String[] columnas) {
        try {
            FileWriter escritor = new FileWriter(rutaArchivo + ".csv");
            StringBuilder encabezado = new StringBuilder();
            for (String columna : columnas) {
                encabezado.append(columna).append(",");
            }
            encabezado.deleteCharAt(encabezado.length() - 1);
            escritor.write(encabezado.toString() + "\n");
            escritor.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void insertar(String rutaArchivo, String[] valores) {
        try {
            FileWriter escritor = new FileWriter(rutaArchivo + ".csv", true);
            StringBuilder fila = new StringBuilder();
            for (String valor : valores) {
                fila.append(valor).append(",");
            }
            fila.deleteCharAt(fila.length() - 1);
            escritor.write("\n" + fila.toString());
            escritor.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void eliminar(String rutaArchivo, String condicion) {
        try {
            List<String[]> filasMantener = new ArrayList<>();
            BufferedReader lector = new BufferedReader(new FileReader(rutaArchivo + ".csv"));
            String linea;
            while ((linea = lector.readLine()) != null) {
                if (!cumpleCondicion(linea, condicion)) {
                    filasMantener.add(linea.split(","));
                }
            }
            lector.close();

            BufferedWriter escritor = new BufferedWriter(new FileWriter(rutaArchivo + ".csv"));
            for (String[] fila : filasMantener) {
                StringBuilder filaString = new StringBuilder();
                for (String valor : fila) {
                    filaString.append(valor).append(",");
                }
                filaString.deleteCharAt(filaString.length() - 1);
                escritor.write(filaString.toString() + "\n");
            }
            escritor.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void actualizar(String rutaArchivo, String[] valores, String condicion) {
        try {
            List<String[]> filasActualizadas = new ArrayList<>();
            BufferedReader lector = new BufferedReader(new FileReader(rutaArchivo + ".csv"));
            String linea;
            while ((linea = lector.readLine()) != null) {
                if (cumpleCondicion(linea, condicion)) {
                    String[] valoresFila = linea.split(",");
                    for (String valor : valores) {
                        String[] partesValor = valor.split("=");
                        int indiceColumna = Integer.parseInt(partesValor[0].trim()) - 1;
                        valoresFila[indiceColumna] = partesValor[1].trim();
                    }
                    filasActualizadas.add(valoresFila);
                } else {
                    filasActualizadas.add(linea.split(","));
                }
            }
            lector.close();

            BufferedWriter escritor = new BufferedWriter(new FileWriter(rutaArchivo + ".csv"));
            for (String[] fila : filasActualizadas) {
                StringBuilder filaString = new StringBuilder();
                for (String valor : fila) {
                    filaString.append(valor).append(",");
                }
                filaString.deleteCharAt(filaString.length() - 1);
                escritor.write(filaString.toString() + "\n");
            }
            escritor.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private boolean cumpleCondicion(String fila, String condicion) {
        if (condicion == null || condicion.isEmpty()) {
            return true;
        }

        String[] partesCondicion = condicion.split("(?i) AND |(?i) OR ");

        for (String parte : partesCondicion) {
            if (parte.contains("=")) {
                String[] partes = parte.split("=");
                int indiceColumna = Integer.parseInt(partes[0].trim()) - 1;
                String valorColumna = partes[1].trim();
                String[] valoresFila = fila.split(",");
                if (valoresFila.length > indiceColumna && valoresFila[indiceColumna].equals(valorColumna)) {
                    return true;
                }
            }
        }

        return false;
    }
}
