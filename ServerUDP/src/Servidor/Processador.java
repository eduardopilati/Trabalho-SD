package servidor;

import data.Posicao;
import data.Veiculo;
import database.DatabaseHelper;
import java.net.DatagramPacket;
import java.util.concurrent.LinkedBlockingQueue;
import util.Utilidades;

public class Processador extends Thread {

    private LinkedBlockingQueue<DatagramPacket> queue;
    private LinkedBlockingQueue<Integer> respostas;

    public Processador() {
        super();
    }

    public Processador(LinkedBlockingQueue<DatagramPacket> q, LinkedBlockingQueue<Integer> r) {
        this();
        queue = q;
        respostas = r;
    }

    @Override
    public void run() {
        System.out.println("Processador iniciado");
        while (true) {
            try {
                DatagramPacket pacote;
                pacote = queue.take();
                Posicao posicao = Utilidades.desserializar(pacote.getData());

                Veiculo veiculo = DatabaseHelper.consultarVeiculo(posicao.getCodigo());
                if (veiculo != null) {
                    DatabaseHelper.adicionarPosicao(posicao);
                    respostas.put(posicao.getCodigo());
                    System.out.printf("Posicao inserida com sucesso Cod:%d", posicao.getCodigo());
                }
            } catch (Exception e) {
                System.err.println("Erro ao processar posicao");
                System.err.println(e);
            }
        }
    }

    public LinkedBlockingQueue<DatagramPacket> getQueue() {
        return queue;
    }

    public void setQueue(LinkedBlockingQueue<DatagramPacket> queue) {
        this.queue = queue;
    }

    public LinkedBlockingQueue<Integer> getRespostas() {
        return respostas;
    }

    public void setRespostas(LinkedBlockingQueue<Integer> respostas) {
        this.respostas = respostas;
    }
}
