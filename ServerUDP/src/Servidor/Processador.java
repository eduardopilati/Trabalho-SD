package servidor;

import data.Posicao;
import data.Veiculo;
import database.DatabaseHelper;
import java.net.DatagramPacket;
import util.Utilidades;

public class Processador extends Thread {

    private Buffer queue;

    public Processador() {
        super();
    }

    public Processador(Buffer q) {
        this();
        queue = q;
    }

    @Override
    public void run() {
        System.out.println("Processador iniciado");
        while (true) {
            try {
                DatagramPacket pacote;
                pacote = queue.retira();
                Posicao posicao = Utilidades.desserializar(pacote.getData());

                Veiculo veiculo = DatabaseHelper.consultarVeiculo(posicao.getCodigo());
                if (veiculo != null) {
                    DatabaseHelper.adicionarPosicao(posicao);
                    System.out.printf("Posicao inserida com sucesso Cod:%d", posicao.getCodigo());
                }
            } catch (Exception e) {
                System.err.println("Erro ao processar posicao");
                System.err.println(e);
            }
        }
    }

    public Buffer getQueue() {
        return queue;
    }

    public void setQueue(Buffer queue) {
        this.queue = queue;
    }
}
