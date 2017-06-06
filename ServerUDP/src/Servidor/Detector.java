package Servidor;

import java.util.HashMap;
import java.util.concurrent.LinkedBlockingQueue;
import javafx.util.Pair;

public class Detector extends Thread{
    private HashMap<Integer, Status> status;
    private LinkedBlockingQueue<Integer> respostas;
    
    public Detector(){
        super();
    }
    
    public Detector(LinkedBlockingQueue<Integer> r){
        this();
        respostas = r;
    }
    
    public void run(){
        while(true){
            for(Integer cod : status.keySet()){
                Status v = status.get(cod);
                v.online = false;
            }
            
        }
    }
    //ter um Map<int, boolean> com todos os codigos dos veiculos
    
    //while(true)
    //  setar todos os veiculos do map como false
    //  //ler do buffer de codigos e setar true no map
    
    //mostrar o map
}
