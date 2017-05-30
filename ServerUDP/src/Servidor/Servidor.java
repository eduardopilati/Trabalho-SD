package servidor;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Servidor {

    public static void main(String[] args) throws Exception {
        int iTamanho = 10;

        ExecutorService application = Executors.newCachedThreadPool();

        Buffering fila = new Buffering(iTamanho);

        application.execute(
                new Receptor(fila));

        application.execute(
                new Processador(fila));

        application.shutdown();
    }
}
