package servidor;

import data.Posicao;
import data.Veiculo;
import database.DatabaseHelper;
import java.net.DatagramPacket;
import util.Utilidades;

public class Processador extends Thread{
    private final Buffering queue;
    
    public Processador(Buffering q) {
        queue = q;
    }
    
    @Override
    public void run(){
        while(true){
            try {
                DatagramPacket pacote;
                synchronized(queue){
                    pacote = queue.retira();
                }
                Posicao posicao = Utilidades.desserializar(pacote.getData());
                
                Veiculo veiculo = DatabaseHelper.consultarVeiculo(posicao.getCodigo());
                if(veiculo != null){
                    DatabaseHelper.adicionarPosicao(posicao);
                    System.out.printf("Posicao inserida com sucesso Cod:%d", posicao.getCodigo());
                }
            } catch (Exception e) {
                System.err.println("Erro ao processar posicao");
                System.err.println(e);
            }
            
        }
        
    }

    public Buffering getQueue() {
        return queue;
    }
}
