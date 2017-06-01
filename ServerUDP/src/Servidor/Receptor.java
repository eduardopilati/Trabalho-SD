package servidor;

import config.ServidorUdp;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

public class Receptor extends Thread {

    private Buffer queue;

    public Receptor() {
        super();
    }

    public Receptor(Buffer q) {
        this();
        queue = q;
    }

    @Override
    public void run() {
        int porta = new ServidorUdp().getPorta();
        try (DatagramSocket serverSocket = new DatagramSocket(porta)) {
            System.out.println("Receptor iniciado");
            while (true) {
                byte[] dadosRecebido = new byte[5000];
                DatagramPacket receivePacket = new DatagramPacket(dadosRecebido, dadosRecebido.length);
                try {
                    serverSocket.receive(receivePacket);
                    System.out.println("recebendo");
                    queue.insere(receivePacket);

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

    public Buffer getQueue() {
        return queue;
    }

    public void setQueue(Buffer queue) {
        this.queue = queue;
    }
}
