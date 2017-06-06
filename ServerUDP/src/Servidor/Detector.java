package Servidor;

import config.ServidorUdp;
import database.DatabaseException;
import database.DatabaseHelper;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class Detector extends Thread{
    private ServidorUdp servidorUdp;
    private HashMap<Integer, Status> status;
    private LinkedList<Integer> respostas;
    
    public Detector(){
        super();
        servidorUdp = new ServidorUdp();
    }
    
    public Detector(LinkedList<Integer> r){
        this();
        respostas = r;
    }
    
    @Override
    public void run(){
        while(true){
            try{
                
                try{
                    atualizarLista();
                }catch(Exception e){
                    System.err.println("Erro ao atualizar lista");
                    System.err.println(e);
                    continue;
                }
                
                limparStatus();
                verificarVeiculosOnline();
                atualizarTempoSemResposta();
                mostrarVeiculos();
                
                Thread.sleep(servidorUdp.getDelayEntreMensagens());
            }catch(Exception e){
                System.err.println("Erro no detector de falhas");
                System.err.println(e);
            }
        }
    }
    
    public void atualizarLista() throws DatabaseException{
        List<Integer> codigos = DatabaseHelper.codigoTodosVeiculos();
        
        for(Integer cod : codigos){
            if(!status.containsKey(cod)){
                status.put(cod, new Status());
            }
        }
        
        for(Integer cod : status.keySet()){
            if(!codigos.contains(cod)){
                status.remove(cod);
            }
        }
    }
    
    public void limparStatus(){
        for(Integer cod : status.keySet()){
            status.get(cod).online = false;
        }
    }
    
    public void verificarVeiculosOnline(){
        synchronized(respostas){
            while(!respostas.isEmpty()){
                int cod = respostas.pop();
                status.get(cod).online = true;
            }
        }
    }
    
    public void atualizarTempoSemResposta(){
        for(Integer cod : status.keySet()){
            Status v = status.get(cod);
            if(v.online){
                v.tempoSemResposta = 0;
            }else{
                v.tempoSemResposta++;
            }
        }
    }
    
    private void mostrarVeiculos() {
        for(Integer cod : status.keySet()){
            Status v = status.get(cod);
            
            if(v.tempoSemResposta <= 1){
                System.out.printf("Veículo %d está online\n", cod);
            }else if(v.tempoSemResposta <= 5){
                System.out.printf("Veículo %d suspeito de estar fora de área de cobertura\n", cod);
            }else{
                System.out.printf("Veículo %d está fora de área de cobertura\n", cod);
            }
        }
    }
}
