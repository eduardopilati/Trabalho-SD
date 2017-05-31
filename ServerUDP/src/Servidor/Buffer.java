package servidor;

import java.net.DatagramPacket;
import java.util.concurrent.LinkedBlockingQueue;

public class Buffer {

    private final LinkedBlockingQueue<DatagramPacket> buffer;
    private final int tamMaximo = 5000;

    public Buffer() {
        buffer = new LinkedBlockingQueue<>(tamMaximo);
    }

    public void insere(DatagramPacket pacote) throws InterruptedException {
        synchronized (buffer){
            buffer.put(pacote);
        }
    }

    public DatagramPacket retira() throws InterruptedException {
        DatagramPacket pacote;
        synchronized (buffer){
            pacote = buffer.take();
        }
        return pacote;
    }
}
