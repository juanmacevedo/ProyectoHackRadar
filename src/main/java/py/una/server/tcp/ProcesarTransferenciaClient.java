package py.una.server.tcp;

import java.net.*;
import java.io.*;

import py.una.entidad.Transaccion;
import py.una.entidad.TransaccionJSON;

public class ProcesarTransferenciaClient {

    public static void main(String[] args) throws Exception {

        String hostServidor = "localhost";
        int puertoServidor = 5001;

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

            System.out.print("Cuenta origen: ");
            String cuentaOrigen = inFromUser.readLine();
            System.out.print("Cuenta destino: ");
            String cuentaDestino = inFromUser.readLine();
            System.out.print("Monto: ");
            double monto = Double.parseDouble(inFromUser.readLine());
            System.out.print("Moneda: ");
            String moneda = inFromUser.readLine();

            Transaccion t = new Transaccion();
            t.setCuentaOrigen(cuentaOrigen);
            t.setCuentaDestino(cuentaDestino);
            t.setMonto(monto);
            t.setMoneda(moneda);

            String mensaje = TransaccionJSON.objetoString(t);
            out.println(mensaje);
            System.out.println("Solicitud de pago enviada, esperando confirmacion...");

            String respuesta = in.readLine();
            Transaccion confirmacion = TransaccionJSON.stringObjeto(respuesta);

            System.out.println("El usuario " + confirmacion.getCuentaDestino() + " fue compensado. Transaccion: " + confirmacion.getTransaccionId() + " - Estado: " + confirmacion.getEstado());

            System.out.print("¿Realizar otra transferencia? (s/n): ");
            continuar = inFromUser.readLine();
        }

        out.println("Bye");

        out.close();
        in.close();
        unSocket.close();
    }
}