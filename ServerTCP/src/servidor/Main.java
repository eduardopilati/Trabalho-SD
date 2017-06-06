package servidor;

import config.ServidorTcp;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Main {
    public static void main(String...args){
        ServidorTcp servidorTcp = new ServidorTcp();
        
        try (ServerSocket servidor = new ServerSocket (servidorTcp.getPorta())){
            
            while(true){
                try{
                    Socket cliente = servidor.accept();
                    new Conexao(cliente).start();
                } catch (Exception e) {
                    System.err.println("Erro ao abrir conexão com o servidor");
                    System.err.println(e);
                }
            }
        } catch (IOException e) {
            System.err.println("Erro ao abrir conexão do servidor");
            System.err.println(e);
        }
    }
}
