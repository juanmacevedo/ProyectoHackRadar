package py.una.server.udp;

import java.io.*;
import java.net.*;

import py.una.entidad.Reporte;
import py.una.entidad.ReporteJSON;

public class ConsultaEstadoClient {

    public static void main(String a[]) throws Exception {

        String direccionServidor = "127.0.0.1";

        if (a.length > 0) {
            direccionServidor = a[0];
        }

        int puertoServidor = 9000;

        try {

            BufferedReader inFromUser =
                    new BufferedReader(new InputStreamReader(System.in));

            DatagramSocket clientSocket = new DatagramSocket();

            InetAddress IPAddress = InetAddress.getByName(direccionServidor);
            System.out.println("Intentando conectar a = " + IPAddress + ":" + puertoServidor +  " via UDP...");

            byte[] sendData = new byte[1024];
            byte[] receiveData = new byte[1024];

            System.out.print("Ingrese el id del reporte a consultar: ");
            String reportId = inFromUser.readLine();

            Reporte consulta = new Reporte();
            consulta.setReportId(reportId);

            String datoPaquete = ReporteJSON.objetoString(consulta);
            sendData = datoPaquete.getBytes();

            System.out.println("Enviar " + datoPaquete + " al servidor. ("+ sendData.length + " bytes)");
            DatagramPacket sendPacket =
                    new DatagramPacket(sendData, sendData.length, IPAddress, puertoServidor);

            clientSocket.send(sendPacket);

            DatagramPacket receivePacket =
                    new DatagramPacket(receiveData, receiveData.length);

            System.out.println("Esperamos si viene la respuesta.");

            clientSocket.setSoTimeout(10000);

            try {
                clientSocket.receive(receivePacket);

                String respuesta = new String(receivePacket.getData());
                Reporte rresp = ReporteJSON.stringObjeto(respuesta.trim());

                InetAddress returnIPAddress = receivePacket.getAddress();
                int port = receivePacket.getPort();

                System.out.println("Respuesta desde =  " + returnIPAddress + ":" + port);
                System.out.println("Estado: " + rresp.getEstado());
                System.out.println("Ultima actualizacion: " + rresp.getUltimaActualizacion());

                if (rresp.getComentarioRevisor() != null) {
                    System.out.println("Comentario del revisor: " + rresp.getComentarioRevisor());
                } else {
                    System.out.println("Comentario del revisor: (sin comentarios)");
                }

            } catch (SocketTimeoutException ste) {

                System.out.println("TimeOut: El paquete udp se asume perdido.");
            }
            clientSocket.close();
        } catch (UnknownHostException ex) {
            System.err.println(ex);
        } catch (IOException ex) {
            System.err.println(ex);
        }
    }
}