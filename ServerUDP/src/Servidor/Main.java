package servidor;

import java.net.DatagramPacket;
import java.util.LinkedList;
import java.util.concurrent.LinkedBlockingQueue;

public class Main {

    public static void main(String[] args) {
        LinkedBlockingQueue<DatagramPacket> fila = new LinkedBlockingQueue<>(5000);
        LinkedList<Integer> respostas = new LinkedList<>();
        new Receptor(fila).start();
        new Processador(fila, respostas).start();
        new Detector(respostas).start();
    }
}
