package servidor;

import java.net.DatagramPacket;
import java.util.concurrent.LinkedBlockingQueue;

public class Buffering {

    private final LinkedBlockingQueue<DatagramPacket> buffering;

    public Buffering(int t) {
        buffering = new LinkedBlockingQueue<>(t);
    }

    public void insere(DatagramPacket pacote) throws InterruptedException {
        buffering.put(pacote);
    }

    public DatagramPacket retira() throws InterruptedException {
        DatagramPacket pacote = buffering.take();

        return pacote;
    }
}
