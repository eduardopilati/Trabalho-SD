package cliente;

import data.Veiculo;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ClienteTcp {

    private static final Integer PORTA = 2006;
    private static final String IP = "localhost:2006";
    
    private static ObjectOutputStream envia;
    private static ObjectInputStream recebe;
    
    private static String menu;
    private static Integer operacao;

    public static void main(String[] args) {
        Scanner lerTeclado = new Scanner(System.in);
        System.out.println("*************** Olá, informe o endereço IP do servidor ****************");
        conectaServidor();
        
        do {

            exibirMenu();
                        
            do {
                System.out.println("Digite a opcao -> ");
                operacao = Integer.parseInt(lerTeclado.nextLine());
            } while (testaEnviaOperacao() == false);
           
            switch (operacao) {
                case 1: {
                    adicionar();
                    break;
                }
                case 2: {
                    listarTodos();
                    break;
                }
                case 3: {
                    consultar();
                    break;
                }
                case 4: {
                    alterar();
                    break;
                }
                case 5: {
                    apagar();
                    break;
                }
                case 6: {
                    sair();
                    break;
                }
                default:
                    ///opçao invalida
            }
        } while (operacao != 7);
    }

    public static void adicionar() {
        Scanner lerTeclado = new Scanner(System.in);
        try {
            
            System.out.println("\n************ Adicionar veiculo ************");
            System.out.print("\nDigite o código do veiculo: ");
            int cod = Integer.parseInt(lerTeclado.nextLine());

            System.out.print("\nDigite a placa do veiculo: ");
            String placa = lerTeclado.nextLine();

            System.out.print("\nDigite o tipo do veiculo: ");
            int tipo = Integer.parseInt(lerTeclado.nextLine());

            System.out.print("\nDigite a capacidade do veiculo: ");
            int cap = Integer.parseInt(lerTeclado.nextLine());

            System.out.print("\nDigite a un do veiculo: ");
            String un = lerTeclado.nextLine();
            
            Veiculo veiculo = new Veiculo(cod, placa, tipo, cap, un);
            
             String enviarDados = "ADICIONAR" + ";"
                    + veiculo.getCodigo() + ";"
                    + veiculo.getTipo() + ";"
                    + veiculo.getPlaca() + ";"
                    + veiculo.getCapacidade()+ ";"
                    + veiculo.getUnCapac();
            
            envia.writeObject(veiculo);
            envia.flush();
            
            System.out.println("Dados enviados para o servidor. Aguardando confirmação...");

            boolean verifica = recebe.readBoolean();
            if (verifica == true) {
                System.out.println("\n******************* veiculo adicionado com sucesso! *********************");
            } else {
                System.out.println("\nxxxxxxxxxxxxxxxxxxxxx ERRO! veiculo não adicionado xxxxxxxxxxxxxxxxxxxxxx");
            }
        } catch (IOException ex) {
            Logger.getLogger(ClienteTcp.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void listarTodos() {
        try {
            envia.writeObject("LISTAR;");
            // combinar como serão retornados os dados do servidor para efetuar a leitura aqui.
            String dadosRecebidos = recebe.readObject().toString();
            
            System.out.println("\n**************************** Listar Todos *****************************\n");
            System.out.println(dadosRecebidos);
            
        } catch (IOException ex) {
            Logger.getLogger(ClienteTcp.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ClienteTcp.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public static void consultar() {
        
        Scanner lerTeclado = new Scanner(System.in);
        try {
            System.out.println("\n************************** Consultar Carro ****************************");
            System.out.print("Informe o código do carro: ");
            
            String codVeic = lerTeclado.nextLine();
            envia.writeObject("CONSULTAR;" + codVeic);
            envia.flush();

            String dadosRecebidos = (String) recebe.readObject();
            String[] parts = dadosRecebidos.split(";");
            // COMBINAR EM QUAL POSICAO ESTARÁ CADA ATRIBUTO DO VEICULO
            
            if (parts[0].equals("NAO_ENCONTRATO")) {
                System.out.println("\nxxxxxxxxxxxxx ERRO! O código informado não foi encontrado xxxxxxxxxxxxx");

            } else {

                Veiculo veiculo = new Veiculo(Integer.parseInt(parts[1]), parts[2], Integer.parseInt(parts[3]), Integer.parseInt(parts[4]), parts[5]);
                
                System.out.println("\n*************************** veiculo recebido ****************************");
                System.out.println("Codigo:" + veiculo.getCodigo());
                System.out.println("Placa:" + veiculo.getPlaca());
                System.out.println("Tipo:" + veiculo.getTipo());
                System.out.println("Capacidade:" + veiculo.getCapacidade());
                System.out.println("UN:" + veiculo.getUnCapac());
            }
        } catch (ClassNotFoundException | IOException ex) {
            Logger.getLogger(ClienteTcp.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void alterar() {
        
        Scanner lerTeclado = new Scanner(System.in);
        try {
            System.out.println("\n**************************** Alterar veiculo ****************************\n");
            System.out.print("Informe o código do veiculo: ");

            int codDig = lerTeclado.nextInt();
            envia.writeInt(codDig);
            envia.flush();

            String dadosRecebidos = (String) recebe.readObject();
            String[] parts = dadosRecebidos.split(";");
 
            if (parts[0].equals("NAO_ENCONTRATO")) {
                System.out.println("\nxxxxxxxxxxxxx ERRO! O código informado não foi encontrado xxxxxxxxxxxxx");

            } else {
                
                Veiculo veiculo = new Veiculo(Integer.parseInt(parts[1]), parts[2], Integer.parseInt(parts[3]), Integer.parseInt(parts[4]), parts[5]);              
                
                System.out.println("\n****************** Dados atuais do carro solicitado *******************");
                System.out.println("Codigo:" + veiculo.getCodigo());
                System.out.println("Placa:" + veiculo.getPlaca());
                System.out.println("Tipo:" + veiculo.getTipo());
                System.out.println("Capacidade:" + veiculo.getCapacidade());
                System.out.println("UN:" + veiculo.getUnCapac());
                System.out.println("-----------------------------------------------------------------------");

                
                System.out.println("\n*********************** Informe os novos dados ************************");
                System.out.print("\nDigite a placa do Carro: ");
                veiculo.setPlaca(lerTeclado.nextLine());

                System.out.print("\nDigite o tipo do Carro: ");
                veiculo.setTipo(Integer.parseInt(lerTeclado.nextLine()));

                System.out.print("\nDigite a capacidade do Carro: ");
                veiculo.setCapacidade(Integer.parseInt(lerTeclado.nextLine()));

                System.out.print("\nDigite a un do Carro: ");
                veiculo.setUnCapac(lerTeclado.nextLine());
       
                String enviarDados = "ALTERAR;" + codDig
                        + veiculo.getPlaca() + ";"
                        + veiculo.getTipo() + ";"
                        + veiculo.getCapacidade() + ";"
                        + veiculo.getUnCapac();

                envia.writeObject(enviarDados);
                envia.flush();
                System.out.println("Dados enviados para o servidor. Aguardando confirmação...");

                boolean verifica = recebe.readBoolean();
                if (verifica == true) {
                    System.out.println("\n********************* veiculo alterado com sucesso! *********************");
                } else {
                    System.out.println("\nxxxxxxxxxxxxxxxxxxxxxx ERRO! veiculo não alterado xxxxxxxxxxxxxxxxxxxxxxx");
                }
            }
        } catch (IOException | ClassNotFoundException ex) {
            Logger.getLogger(ClienteTcp.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void apagar() {
        Scanner lerTeclado = new Scanner(System.in);
        try {
            System.out.println("\n**************************** Excluir veiculo ****************************");
            System.out.print("Informe o código do veiculo: ");

            int codDig = lerTeclado.nextInt();
            envia.writeInt(codDig);
            envia.flush();

            String dadosRecebidos = (String) recebe.readObject();
            String[] parts = dadosRecebidos.split(";");
 
            if (parts[0].equals("NAO_ENCONTRATO")) {
                System.out.println("\nxxxxxxxxxxxxx ERRO! O código informado não foi encontrado xxxxxxxxxxxxx");
            } else {
             
                String enviarDados = "EXCLUIR;" + codDig;
                envia.writeObject(enviarDados);
                envia.flush();
                System.out.println("Solicitação enviada para o servidor. Aguardando confirmação...");

                boolean verifica = recebe.readBoolean();
                if (verifica == true) {
                    System.out.println("\n********************* veiculo apagado com sucesso! **********************");
                } else {
                    System.out.println("\nxxxxxxxxxxxxxxxxxxxxxxx ERRO! veiculo não apagado xxxxxxxxxxxxxxxxxxxxxxx");
                }      
            }
        } catch (IOException | ClassNotFoundException ex) {
            Logger.getLogger(ClienteTcp.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void sair() {
        try {
            recebe.close();
            envia.close();
            System.out.println("\n****************** Conexão com o servidor finalizada ******************\n");
        } catch (IOException ex) {
            Logger.getLogger(ClienteTcp.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void conectaServidor() {
        try {
            Socket servidor = new Socket(IP, PORTA);
            recebe = new ObjectInputStream(servidor.getInputStream());
            envia = new ObjectOutputStream(servidor.getOutputStream());
        } catch (IOException ex) {
            Logger.getLogger(ClienteTcp.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void exibirMenu() {
        System.out.println("\n ====================   ");
        System.out.println("   |  1 - ADICIONAR   |   ");
        System.out.println("   |  2 - LISTAR      |   ");
        System.out.println("   |  3 - CONSULTAR   |   ");
        System.out.println("   |  4 - ALTERAR     |   ");
        System.out.println("   |  5 - APAGAR      |   ");
        System.out.println("   |  0 - SAIR        |   ");
        System.out.println("   ==================== \n");
        System.out.println("\n");
    }

    public static boolean testaEnviaOperacao() {
        try {
            Scanner lerTeclado = new Scanner(System.in);        
            envia.writeObject("TESTAR;"+operacao);
            envia.flush();
            if (recebe.readBoolean() == false) {
                System.out.println("\nxxxxxxxxxxxxxxxxxxxxxxxxx Operação inválida!! xxxxxxxxxxxxxxxxxxxxxxxxx");
                System.out.println("Por favor, digite novamente..");
                return false;
            } else return true;
        } catch (IOException ex) {
            Logger.getLogger(ClienteTcp.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
}