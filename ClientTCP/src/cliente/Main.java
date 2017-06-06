package cliente;

import config.ServidorTcp;
import java.io.IOException;
import java.net.Socket;

public class Main {
    public static void main(String...args){
        ServidorTcp servidorTcp = new ServidorTcp();
        
        try (Socket socket = new Socket(servidorTcp.getHost(), servidorTcp.getPorta())){
            
            new Cliente(socket).main();
            
        } catch (IOException e) {
            System.err.println("Erro ao conectar ao servidor");
            System.err.println(e);
        }
    }
}
