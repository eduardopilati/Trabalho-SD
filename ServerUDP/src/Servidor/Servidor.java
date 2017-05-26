package Servidor;

import java.net.DatagramSocket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 *
 * @author LeonardoBrum
 */
public class Servidor {

    public static void main(String[] args) throws Exception {
        int iServidorPorta = 2010;
        int iTamanho = 10;
        DatagramSocket serverSocket;
        
        ExecutorService application = Executors.newCachedThreadPool();
        
        Buffering fila = new Buffering(iTamanho);
        
        serverSocket = new DatagramSocket(iServidorPorta);

        application.execute(new EntraFila(fila, serverSocket));

        application.execute(new TiraFila(fila, serverSocket));

        application.shutdown();
    }    
}
