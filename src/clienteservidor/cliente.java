package clienteservidor;

import java.net.*;
import java.util.Scanner;
import java.io.*;

public abstract class cliente {

	/*
	 * Leer la entrada del usuario y retorna lo leido como un arreglo de bytes
	 */
	public static byte[] leerMensaje() {
		Scanner sc = new Scanner(System.in);
		System.out.println("Ingrese el mensaje a enviar");
		byte[] m = sc.next().getBytes();
		return m;
	}

	/*
	 * Envia un mensaje al servidor indicado
	 */
	public static void enviar(DatagramSocket unSocket, InetAddress unHost, byte[] msg)
			throws SocketException, IOException {
		DatagramPacket peticion = new DatagramPacket(msg, msg.length, unHost, 6790);

		unSocket.send(peticion);

		System.out.println("Enviando...");
	}

	/*
	 * Recibe la respuesta del servidor y lo imprime por consola
	 */
	public static String recibir(DatagramSocket unSocket) throws IOException {
		byte[] bufer = new byte[1000];
		DatagramPacket respuesta = new DatagramPacket(bufer, bufer.length);
		unSocket.receive(respuesta);

		return new String(respuesta.getData());
	}
	
	


}
