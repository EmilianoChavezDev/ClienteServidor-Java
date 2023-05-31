package clienteservidor;

import java.net.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

import java.io.*;

public class Servidor {

    public static void main(String args[]) {
        try {
            boolean flag = true;
            DatagramSocket unSocket = new DatagramSocket(6790);
            byte[] bufer = new byte[1000];
            System.out.println("Corriendo servidor....");
            while (flag) {

                DatagramPacket peticion = new DatagramPacket(bufer, bufer.length);
                unSocket.receive(peticion);

                String msg = new String(peticion.getData()).trim();

                //se crea una lista con el contendi que va a tener el mensaje de respuesta
                List<String> listRespuesta = new ArrayList<String>();
                listRespuesta.add("enviaste el mensaje ");
                listRespuesta.add("\"");
                listRespuesta.add(msg);
                listRespuesta.add("\"");
                listRespuesta.add(" el dia ");
                listRespuesta.add("\"");
                listRespuesta.add(LocalDate.now().toString());
                listRespuesta.add("\"");
                listRespuesta.add(" a las ");
                listRespuesta.add("\"");
                listRespuesta.add(LocalTime.now().toString());
                listRespuesta.add("\"");
                listRespuesta.add(", su direccion IP es ");
                listRespuesta.add("\"");
                listRespuesta.add(peticion.getAddress().toString());
                listRespuesta.add("\"");
                listRespuesta.add(" y utilizo el puerto ");
                listRespuesta.add("\"");
                listRespuesta.add(Integer.toString(peticion.getPort()));
                listRespuesta.add("\"");
                listRespuesta.add(" para salir, la respuesta del servidor entro ");
                listRespuesta.add("por el puerto ");
                listRespuesta.add("\"");
                listRespuesta.add(Integer.toString(peticion.getPort()));
                listRespuesta.add("\"");

                String paraCliente = joinList(listRespuesta);

                DatagramPacket respuesta = new DatagramPacket(paraCliente.getBytes(), paraCliente.getBytes().length,
                        peticion.getAddress(), peticion.getPort());

                StringBuilder sb2 = new StringBuilder();
                sb2.append("La peticion vino de la direccion: ");
                sb2.append(peticion.getSocketAddress());
                sb2.append(" en el puerto: ");
                sb2.append(peticion.getPort());
                sb2.append(" y el mensaje fue: ");
                sb2.append(msg);
                sb2.append(" y la respuesta fue: ");

                sb2.append(paraCliente);

                unSocket.send(respuesta);

            }
        } catch (SocketException e) {
            System.out.println("Error: Socket: " + e.getMessage());

        } catch (IOException e) {
            System.out.println("Error: I0: " + e.getMessage());

        } catch (Exception e) {
            System.out.println("Error inesperado: " + e.getMessage());

        }

        System.out.println("Servidor finalizado.");

    }

    public static String joinList(List<String> l) {
        StringBuilder sb = new StringBuilder();
        l.forEach((a) -> sb.append(a));
        return sb.toString();
    }
}
