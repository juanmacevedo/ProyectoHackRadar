package py.una.server.tcp;

import java.net.*;
import java.io.*;
import java.util.UUID;

import py.una.bd.ReporteDAO;
import py.una.entidad.Reporte;
import py.una.entidad.ReporteJSON;

public class RegistroReporteServer {

    public static void main(String[] args) throws Exception {

        int puertoServidor = 5000;

        ServerSocket serverSocket = null;
        try {
            serverSocket = new ServerSocket(puertoServidor);
        } catch (IOException e) {
            System.err.println("No se puede abrir el puerto: " +puertoServidor+ ".");
            System.exit(1);
        }
        System.out.println("Puerto abierto: "+puertoServidor+".");

        Socket clientSocket = null;
        try {
            clientSocket = serverSocket.accept();
        } catch (IOException e) {
            System.err.println("Fallo el accept().");
            System.exit(1);
        }

        PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
        BufferedReader in = new BufferedReader(
                new InputStreamReader(
                clientSocket.getInputStream()));

        String inputLine;
        while ((inputLine = in.readLine()) != null) {

            if (inputLine.equals("Bye")) {
                System.out.println("Cliente finalizo la conexion.");
                break;
            }

            System.out.println("Mensaje recibido: " + inputLine);

            Reporte r = ReporteJSON.stringObjeto(inputLine);
            r.setReportId(UUID.randomUUID().toString());
            r.setEstado("Recibido");
            r.setFechaReporte(java.time.LocalDateTime.now().toString());
            r.setUltimaActualizacion(r.getFechaReporte());

            ReporteDAO dao = new ReporteDAO();
            dao.insertar(r);
            System.out.println("Reporte registrado.");

            Reporte respuesta = new Reporte();
            respuesta.setReportId(r.getReportId());
            respuesta.setEstado(r.getEstado());
            respuesta.setFechaReporte(r.getFechaReporte());

            String outputLine = ReporteJSON.objetoString(respuesta);
            out.println(outputLine);
            System.out.println("Respuesta enviada: " + outputLine);
        }

        out.close();
        in.close();
        clientSocket.close();
        serverSocket.close();
    }
}