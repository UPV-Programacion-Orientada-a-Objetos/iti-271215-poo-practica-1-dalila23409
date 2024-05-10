package edu.upvictoria.fpoo;

public class InterpretarComandos {
    private BaseDeDatos baseDeDatos;

    public InterpretarComandos(BaseDeDatos baseDeDatos) {
        this.baseDeDatos = baseDeDatos;
    }

    public void interpretarComando(String comando) {
        String[] partes = comando.split(" ");
        String primerPalabra = partes[0].toLowerCase();

        switch (primerPalabra) {
            case "usar":
                if (partes.length >= 2) {
                    baseDeDatos.usar(partes[1]);
                } else {
                    System.out.println("Error: Falta especificar la ruta de la base de datos.");
                }
                break;
            case "mostrar":
                baseDeDatos.mostrarTablas();
                break;
            case "crear":
                if (partes.length >= 4 && partes[1].equalsIgnoreCase("tabla")) {
                    String nombreTabla = partes[2];
                    String[] columnas = partes[3].split(",");
                    baseDeDatos.crearTabla(nombreTabla, columnas);
                } else {
                    System.out.println("Error: Comando 'creartabla' mal formado.");
                }
                break;
            case "insertar":
                if (partes.length >= 4 && partes[1].equalsIgnoreCase("en")) {
                    String nombreTabla = partes[2];
                    String[] valores = partes[3].split(",");
                    baseDeDatos.insertar(nombreTabla, valores);
                } else {
                    System.out.println("Error: Comando 'insertarentabla' mal formado.");
                }
                break;
            case "eliminar":
                if (partes.length >= 3 && partes[1].equalsIgnoreCase("de")) {
                    String nombreTabla = partes[2];
                    String condicion = partes.length >= 4 ? partes[3] : "";
                    baseDeDatos.eliminar(nombreTabla, condicion);
                } else {
                    System.out.println("Error: Comando 'eliminar detabla' mal formado.");
                }
                break;
            case "actualizar":
                if (partes.length >= 3 && partes[1].equalsIgnoreCase("en")) {
                    String nombreTabla = partes[2];
                    String[] columnas = partes[3].split(",");
                    String condicion = partes.length >= 5 ? partes[4] : "";
                    baseDeDatos.actualizar(nombreTabla, columnas, condicion);
                } else {
                    System.out.println("Error: Comando 'actualizarentabla' mal formado.");
                }
                break;
            case "seleccionar":
                if (partes.length >= 3 && partes[1].equalsIgnoreCase("de")) {
                    String nombreTabla = partes[2];
                    String columnas = partes.length >= 4 ? partes[3] : "*";
                    String condicion = partes.length >= 5 ? partes[4] : "";
                    baseDeDatos.seleccionar(nombreTabla, columnas, condicion);
                } else {
                    System.out.println("Error: Comando 'seleccionardetabla' mal formado.");
                }
                break;
            default:
                System.out.println("Error: Comando no reconocido.");
                break;
        }
    }
}

