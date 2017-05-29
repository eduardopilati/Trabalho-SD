package cliente;

import java.util.Scanner;

public class Main {
    
    public static void main(String...args){
        Scanner sc = new Scanner(System.in);
        System.out.printf("Para iniciar um novo envio digite as informações abaixo:\n"
                        + " - Código do veículo\n"
                        + " - Quantidade de localizações enviadas (0 para infinito) \n"
                        + "Exemplo: '1 10'\n"
                        + "Cada inserção gera uma nova Thread de envio\n");
        while(true){
            int cod = sc.nextInt();
            int qtd = sc.nextInt();
            new Gerador(cod, qtd).start();
        }
    }
    
}
