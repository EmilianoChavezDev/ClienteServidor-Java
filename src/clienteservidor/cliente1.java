package clienteservidor;

import java.io.IOException;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

public class cliente1 extends cliente {

    public static void main(String args[]) {
        try {
            DatagramSocket unSocket = new DatagramSocket();
            InetAddress unHost = InetAddress.getByAddress("localhost",
                    new byte[]{127, 0, 0, 1});
            unSocket.setSoTimeout(5000);

            byte[] msg = cliente.leerMensaje();

            enviar(unSocket, unHost, msg);

            String respuesta = recibir(unSocket);

   
            System.out.println("La respuesta del servidor es: " + respuesta);

            unSocket.close();
        } catch (SocketException e) {
            System.out.println("Socket: " + e.getMessage());
            
        } catch (IOException e) {
            System.out.println("IO: " + e.getMessage());
            //LogRegister.error(Cliente1.class, "Timeout Error: ", e);
        } catch (Exception e) {
            System.out.println("Error inespeerado: " + e.getMessage());

        }

        System.out.println("Cliente1 Finalizado");
    }
}
