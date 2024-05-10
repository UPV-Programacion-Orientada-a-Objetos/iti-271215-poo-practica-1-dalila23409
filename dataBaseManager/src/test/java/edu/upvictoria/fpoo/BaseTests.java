package edu.upvictoria.fpoo;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
public class BaseTests {
    @Test
        public void testUsar() {
            BaseDeDatos baseDeDatos = new BaseDeDatos();
            baseDeDatos.usar("Equipo/home/dalila/documentos/iti-271215-poo-practica-1-dalila23409");
            assertEquals("Equipo/home/dalila/documentos/iti-271215-poo-practica-1-dalila23409", baseDeDatos.getDirTrabajo());
        }
        @Test
        public void testMostrarTablas() {
            BaseDeDatos baseDeDatos = new BaseDeDatos("directorio/prueba");

            File archivo1 = new File("directorio/prueba/tabla1.csv");
            File archivo2 = new File("directorio/prueba/tabla2.csv");
            try {
                archivo1.createNewFile();
                archivo2.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }

            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            PrintStream printStream = new PrintStream(outputStream);
            System.setOut(printStream);
            baseDeDatos.mostrarTablas();
            String expectedOutput = "tabla1\ntabla2\n";
            assertEquals(expectedOutput, outputStream.toString());

            archivo1.delete();
            archivo2.delete();
        }

        @Test
        public void testCrearTabla() {
            BaseDeDatos baseDeDatos = new BaseDeDatos("directorio/prueba");

            String nombreTabla = "nuevaTabla";
            String[] columnas = {"id INT", "nombre VARCHAR(50)", "edad INT"};

            baseDeDatos.crearTabla(nombreTabla, columnas);

            File archivoTabla = new File("directorio/prueba/nuevaTabla.csv");
            assertTrue(archivoTabla.exists());

            archivoTabla.delete();
        }

        @Test
        public void testEliminarTabla() {
            BaseDeDatos baseDeDatos = new BaseDeDatos("directorio/prueba");

            File archivoTabla = new File("directorio/prueba/tablaEliminar.csv");
            try {
                archivoTabla.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }

            baseDeDatos.eliminarTabla("tablaEliminar");

            assertFalse(archivoTabla.exists());
        }
    }

