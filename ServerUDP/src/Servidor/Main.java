package servidor;

import java.net.DatagramPacket;
import java.util.concurrent.LinkedBlockingQueue;

public class Main {

    public static void main(String[] args) throws Exception {
        LinkedBlockingQueue<DatagramPacket> fila = new LinkedBlockingQueue<>(5000);
        LinkedBlockingQueue<Integer> respostas = new LinkedBlockingQueue<>();
        new Receptor(fila).start();
        new Processador(fila, respostas).start();
        new Detector(respostas).start();
    }
}
