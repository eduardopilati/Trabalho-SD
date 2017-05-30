package servidor;

import config.ServidorUdp;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

public class Receptor extends Thread {

    private final Buffering queue;

    public Receptor(Buffering q) {
        queue = q;
    }

    @Override
    public void run() {
        int porta = new ServidorUdp().getPorta();
        try(DatagramSocket serverSocket = new DatagramSocket(porta)){
            while (true) {
                byte[] dadosRecebido = new byte[5000];
                DatagramPacket receivePacket = new DatagramPacket(dadosRecebido, dadosRecebido.length);
                try {
                    serverSocket.receive(receivePacket);
                    synchronized(queue){
                        queue.insere(receivePacket);
                    }
                    
                } catch (IOException | InterruptedException e) {
                    System.err.println("Erro ao receber mensagem");
                    System.err.println(e);
                }
            }
        } catch (SocketException e) {
            System.err.println("Erro ao abrir socket");
            System.err.println(e);
        }
    }
}
