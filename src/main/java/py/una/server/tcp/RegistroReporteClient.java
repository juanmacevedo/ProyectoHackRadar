package py.una.server.tcp;

import java.net.*;
import java.io.*;

import py.una.entidad.Reporte;
import py.una.entidad.ReporteJSON;

public class RegistroReporteClient {

    public static void main(String[] args) throws Exception {

        String hostServidor = "localhost";
        int puertoServidor = 5000;

        Socket unSocket = null;
        PrintWriter out = null;
        BufferedReader in = null;

        try {
            unSocket = new Socket(hostServidor, puertoServidor);
            out = new PrintWriter(unSocket.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(unSocket.getInputStream()));
        } catch (UnknownHostException e) {
            System.err.println("Host desconocido");
            System.exit(1);
        } catch (IOException e) {
            System.err.println("Error de I/O en la conexion al host");
            System.exit(1);
        }

        BufferedReader inFromUser = new BufferedReader(new InputStreamReader(System.in));
        String continuar = "s";

        while (continuar.equalsIgnoreCase("s")) {

            System.out.print("Hunter id: ");
            String hunterId = inFromUser.readLine();
            System.out.print("Programa id: ");
            String programId = inFromUser.readLine();
            System.out.print("Titulo: ");
            String titulo = inFromUser.readLine();
            System.out.print("Descripcion: ");
            String descripcion = inFromUser.readLine();
            System.out.print("Severidad (Baja, Media, Alta, Critica): ");
            String severidad = inFromUser.readLine();

            Reporte r = new Reporte();
            r.setHunterId(hunterId);
            r.setProgramId(programId);
            r.setTitulo(titulo);
            r.setDescripcion(descripcion);
            r.setSeveridad(severidad);

            String mensaje = ReporteJSON.objetoString(r);
            out.println(mensaje);
            System.out.println("Reporte enviado, esperando confirmacion...");

            String respuesta = in.readLine();
            Reporte confirmacion = ReporteJSON.stringObjeto(respuesta);

            System.out.println("Reporte recibido. Id: " + confirmacion.getReportId() + " - Estado: " + confirmacion.getEstado());

            System.out.print("¿Registrar otro reporte? (s/n): ");
            continuar = inFromUser.readLine();
        }

        out.println("Bye");

        out.close();
        in.close();
        unSocket.close();
    }
}