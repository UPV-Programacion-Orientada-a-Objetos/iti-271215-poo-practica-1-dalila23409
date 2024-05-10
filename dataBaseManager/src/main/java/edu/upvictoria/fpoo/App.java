package edu.upvictoria.fpoo;
import java.io.*;

public class App {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        BaseDeDatos baseDeDatos = new BaseDeDatos();
        InterpreteComandos interpreteComandos = new InterpreteComandos(baseDeDatos);

        while (true) {
            System.out.print("SQL> ");
            String comando = scanner.nextLine();
            if (comando.equalsIgnoreCase("salir")) {
                System.out.println("Saliendo...");
                break;
            }
            interpreteComandos.interpretarComando(comando);
        }
        scanner.close();
    }

