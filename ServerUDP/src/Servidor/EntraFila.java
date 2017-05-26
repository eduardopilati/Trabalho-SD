package Servidor;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

/**
 *
 * @author LeonardoBrum
 */
public class EntraFila extends Thread {

    private final Buffering queue;
    private final DatagramSocket socketserver;

    public EntraFila(Buffering q, DatagramSocket soc) {
        queue = q;
        socketserver = soc;
    }

    public void run() {
        byte[] dadosRecebido = new byte[5000];

        System.out.println("Servidor iniciado");
        while (true) {
            //Esperando requisição
            DatagramPacket receivePacket = new DatagramPacket(dadosRecebido, dadosRecebido.length);
            try {

                socketserver.receive(receivePacket);

                try {
                    queue.insere(receivePacket);
                    System.out.println("Dados adicionado no Banco ");
                } catch (InterruptedException ex) {
                    //
                }
            } catch (IOException ex) {
                //
            }
        }
    }
}
