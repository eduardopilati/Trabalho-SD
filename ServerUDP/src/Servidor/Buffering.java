package Servidor;

import java.net.DatagramPacket;
import java.util.concurrent.ArrayBlockingQueue;

/**
 *
 * @author LeonardoBrum
 */
public class Buffering {

    private final ArrayBlockingQueue<DatagramPacket> buffering;

    public Buffering(int t) {
        buffering = new ArrayBlockingQueue<DatagramPacket>(t);
    }

    //Insere pacote na fila
    public void insere(DatagramPacket pacote) throws InterruptedException {
        buffering.put(pacote);
    }

    //Retira pacote da fila
    public DatagramPacket retira() throws InterruptedException {
        DatagramPacket pacote = buffering.take();

        return pacote;
    }
}
