package py.una.server.udp;

import java.io.*;
import java.net.*;

import py.una.bd.ReporteDAO;
import py.una.entidad.Reporte;
import py.una.entidad.ReporteJSON;

public class ConsultaEstadoServer {

    public static void main(String[] a){

        int puertoServidor = 9000;
        ReporteDAO rdao = new ReporteDAO();

        try {
            DatagramSocket serverSocket = new DatagramSocket(puertoServidor);
            System.out.println("Servidor HackRadar - UDP - Puerto " + puertoServidor);

            byte[] receiveData = new byte[1024];
            byte[] sendData = new byte[1024];

            while (true) {

                receiveData = new byte[1024];

                DatagramPacket receivePacket =
                        new DatagramPacket(receiveData, receiveData.length);

                System.out.println("Esperando a algun cliente... ");

                serverSocket.receive(receivePacket);

                String datoRecibido = new String(receivePacket.getData());
                datoRecibido = datoRecibido.trim();
                System.out.println("DatoRecibido: " + datoRecibido );
                Reporte consulta = ReporteJSON.stringObjeto(datoRecibido);

                InetAddress IPAddress = receivePacket.getAddress();
                int port = receivePacket.getPort();

                System.out.println("De : " + IPAddress + ":" + port);

                Reporte respuesta = rdao.seleccionarPorId(consulta.getReportId());

                if (respuesta == null) {
                    respuesta = new Reporte();
                    respuesta.setEstado("No encontrado");
                }

                sendData = ReporteJSON.objetoString(respuesta).getBytes();
                DatagramPacket sendPacket =
                        new DatagramPacket(sendData, sendData.length, IPAddress, port);

                serverSocket.send(sendPacket);
                System.out.println("Respuesta enviada: " + new String(sendData));
            }

        } catch (Exception ex) {
            ex.printStackTrace();
            System.exit(1);
        }

    }
}