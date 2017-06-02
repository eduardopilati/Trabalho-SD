import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;


public class Server implements Runnable{
    public Socket cliente;

    public Server(Socket cliente){
        this.cliente = cliente;
    }

    public static void main(String[] args)  throws IOException{     

        ServerSocket servidor = new ServerSocket (2010);
        System.out.println("Porta 2010 aberta!");
        System.out.println("Aguardando conexÃƒÂ£o do cliente...");   
        
        while (true) {
          Socket cliente = servidor.accept();
         //criaÃ§Ã£o da thread
          Server tratamento = new Server(cliente);
          Thread t = new Thread(tratamento);
          // Inicia a thread para o cliente conectado
          t.start();
        }
    }
    public void run(){
        System.out.println("Nova conexao com o cliente " + this.cliente.getInetAddress().getHostAddress());

        try {
            Scanner s = null;
            s = new Scanner(this.cliente.getInputStream());

            //Exibe mensagem no console
            while(s.hasNextLine()){
                System.out.println(s.nextLine());
            }

            //Finaliza objetos
            s.close();
            this.cliente.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
