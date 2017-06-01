package cliente;

import data.comunicacao.Posicao;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ClienteTcp {

    private static ObjectOutputStream envia;
    private static ObjectInputStream recebe;
    private static final Integer porta = 2006;
    private static String ip;
    private static String menu;
    private static Integer operacao;

    public static void main(String[] args) {
        Scanner lerTeclado = new Scanner(System.in);
        System.out.println("*************** Olá, informe o endereço IP do servidor ****************");
        pegaIp();
        conectaServidor();
        recebeMenu();

        do {

            System.out.println(menu);
            testaEnviaOperacao();

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
                    consultarAnoModelo();
                    break;
                }
                case 5: {
                    alterar();
                    break;
                }
                case 6: {
                    apagar();
                    break;
                }
                case 7: {
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
            Posicao carro = new Posicao();
            System.out.println("\n************ Adicionar Carro ************");
            System.out.print("\nDigite o código do Carro: ");
            carro.setCodigo(Integer.parseInt(lerTeclado.nextLine()));

            System.out.print("\nDigite a marca do Carro: ");
            carro.setMarca(lerTeclado.nextLine());

            System.out.print("\nDigite o modelo do Carro: ");
            carro.setModelo(lerTeclado.nextLine());

            System.out.print("\nDigite o ano do Carro: ");
            carro.setAno(Integer.parseInt(lerTeclado.nextLine()));

            System.out.print("\nDigite o potencia do Carro: ");
            carro.setPotencia(Float.parseFloat(lerTeclado.nextLine()));

            System.out.print("\nDigite o carga do Carro: ");
            carro.setCarga(Float.parseFloat(lerTeclado.nextLine()));

            System.out.print("\nDigite o complemento: ");
            carro.setComplemento(lerTeclado.nextLine());

            String enviarDados
                    = carro.getCodigo() + ":"
                    + carro.getMarca() + ":"
                    + carro.getModelo() + ":"
                    + carro.getAno() + ":"
                    + carro.getPotencia() + ":"
                    + carro.getCarga() + ":"
                    + carro.getComplemento();
///Passar objeto em vez de string
            envia.writeObject(enviarDados);
            envia.flush();
            System.out.println("Dados enviados para o servidor. Aguardando confirmação...");

            boolean verifica = recebe.readBoolean();
            if (verifica == true) {
                System.out.println("\n******************* Carro adicionado com sucesso! *********************");
            } else {
                System.out.println("\nxxxxxxxxxxxxxxxxxxxxx ERRO! Carro não adicionado xxxxxxxxxxxxxxxxxxxxxx");

            }
        } catch (IOException ex) {
            Logger.getLogger(ClienteTcp.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public static void listarTodos() {
        try {
///Consultar ao servidor retornando todos os objetos
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

            int codDig = lerTeclado.nextInt();
            envia.writeInt(codDig);
            envia.flush();
///consulta
            String dadosRecebidos = (String) recebe.readObject();
            String[] parts = dadosRecebidos.split(":");
            String testeExiste = null;

            if (!"null".equals(parts[0])) {
                testeExiste = (String) (parts[0]);
            }

            if ("naoencontrado".equals(testeExiste)) {
                System.out.println("\nxxxxxxxxxxxxx ERRO! O código informado não foi encontrado xxxxxxxxxxxxx");

            } else {

                Carro carro = new Carro();

                if (!"null".equals(parts[1])) {
                    carro.setCodigo(Integer.parseInt(parts[1]));
                }
                if (!"null".equals(parts[2])) {
                    carro.setMarca(parts[2]);
                }
                if (!"null".equals(parts[3])) {
                    carro.setModelo(parts[3]);
                }
                if (!"null".equals(parts[4])) {
                    carro.setAno(Integer.parseInt(parts[4]));
                }
                if (!"null".equals(parts[5])) {
                    carro.setPotencia(Float.parseFloat(parts[5]));
                }
                if (!"null".equals(parts[6])) {
                    carro.setCarga(Float.parseFloat(parts[6]));
                }
                if (!"null".equals(parts[7])) {
                    carro.setComplemento(parts[7]);
                }

                System.out.println("\n*************************** Carro recebido ****************************");

                System.out.println("Codigo:" + carro.getCodigo());
                System.out.println("Marca:" + carro.getMarca());
                System.out.println("Modelo:" + carro.getModelo());
                System.out.println("Ano:" + carro.getAno());
                System.out.println("Potencia:" + carro.getPotencia());
                System.out.println("Carga:" + carro.getCarga());
                System.out.println("Complemento:" + carro.getComplemento());
            }

        } catch (IOException ex) {
            Logger.getLogger(ClienteTcp.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ClienteTcp.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void consultarAnoModelo() {
        Scanner lerTeclado = new Scanner(System.in);
        try {
            Carro carro = new Carro();
            System.out.println("\n************************ Consultar Ano/Modelo *************************");

            System.out.print("\nDigite o modelo do Carro: ");
            carro.setModelo(lerTeclado.nextLine());

            System.out.print("\nDigite o ano do Carro: ");
            carro.setAno(Integer.parseInt(lerTeclado.nextLine()));

            String enviarDados = (carro.getModelo() + ":" + carro.getAno());

            envia.writeObject(enviarDados);
            envia.flush();
            System.out.println("\nDados enviados para o servidor. Aguardando resposta...");
            System.out.println("\n-----------------------------------------------------------------------");

            String dadosRecebidos = recebe.readObject().toString();
            if ("[]".equals(dadosRecebidos)) {
                System.out.println("\nNão foram encontrados registros de carros com esse modelo e ano!");

            } else {
                System.out.println(dadosRecebidos);
                System.out.println("\n-----------------------------------------------------------------------\n");
            }

        } catch (IOException ex) {
            Logger.getLogger(ClienteTcp.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ClienteTcp.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public static void alterar() {
        Scanner lerTeclado = new Scanner(System.in);
        Scanner lerTeclado2 = new Scanner(System.in);

        try {
            System.out.println("\n**************************** Alterar Carro ****************************\n");
            System.out.print("Informe o código do carro: ");

            int codDig = lerTeclado2.nextInt();
            envia.writeInt(codDig);
            envia.flush();

            String dadosRecebidos = (String) recebe.readObject();
            String[] parts = dadosRecebidos.split(":");
            String testeExiste = null;

            if (!"null".equals(parts[0])) {
                testeExiste = (String) (parts[0]);
            }
            
            
            if ("naoencontrado".equals(testeExiste)) {
                System.out.println("\nxxxxxxxxxxxxx ERRO! O código informado não foi encontrado xxxxxxxxxxxxx");
            } else {
                Carro carro = new Carro();
                if (!"null".equals(parts[1])) {
                    carro.setCodigo(Integer.parseInt(parts[1]));
                }
                if (!"null".equals(parts[2])) {
                    carro.setMarca(parts[2]);
                }
                if (!"null".equals(parts[3])) {
                    carro.setModelo(parts[3]);
                }
                if (!"null".equals(parts[4])) {
                    carro.setAno(Integer.parseInt(parts[4]));
                }
                if (!"null".equals(parts[5])) {
                    carro.setPotencia(Float.parseFloat(parts[5]));
                }
                if (!"null".equals(parts[6])) {
                    carro.setCarga(Float.parseFloat(parts[6]));
                }
                if (!"null".equals(parts[7])) {
                    carro.setComplemento(parts[7]);
                }
                
                
                System.out.println("\n****************** Dados atuais do carro solicitado *******************");
                System.out.println("Codigo:" + carro.getCodigo());
                System.out.println("Marca:" + carro.getMarca());
                System.out.println("Modelo:" + carro.getModelo());
                System.out.println("Ano:" + carro.getAno());
                System.out.println("Potencia:" + carro.getPotencia());
                System.out.println("Carga:" + carro.getCarga());
                System.out.println("Complemento:" + carro.getComplemento());
                System.out.println("-----------------------------------------------------------------------");

                
                System.out.println("\n*********************** Informe os novos dados ************************");
                System.out.print("\nDigite a marca do Carro: ");
                carro.setMarca(lerTeclado.nextLine());

                System.out.print("\nDigite o modelo do Carro: ");
                carro.setModelo(lerTeclado.nextLine());

                System.out.print("\nDigite o ano do Carro: ");
                carro.setAno(Integer.parseInt(lerTeclado.nextLine()));

                System.out.print("\nDigite o potencia do Carro: ");
                carro.setPotencia(Float.parseFloat(lerTeclado.nextLine()));

                System.out.print("\nDigite o carga do Carro: ");
                carro.setCarga(Float.parseFloat(lerTeclado.nextLine()));

                System.out.print("\nDigite o complemento: ");
                carro.setComplemento(lerTeclado.nextLine());

                String enviarDados
                        = carro.getCodigo() + ":"
                        + carro.getMarca() + ":"
                        + carro.getModelo() + ":"
                        + carro.getAno() + ":"
                        + carro.getPotencia() + ":"
                        + carro.getCarga() + ":"
                        + carro.getComplemento();

                envia.writeObject(enviarDados);
                envia.flush();
                System.out.println("Dados enviados para o servidor. Aguardando confirmação...");

                boolean verifica = recebe.readBoolean();
                if (verifica == true) {
                    System.out.println("\n********************* Carro alterado com sucesso! *********************");

                } else {
                    System.out.println("\nxxxxxxxxxxxxxxxxxxxxxx ERRO! Carro não alterado xxxxxxxxxxxxxxxxxxxxxxx");

                }

            }
        } catch (IOException ex) {
            Logger.getLogger(ClienteTcp.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ClienteTcp.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public static void apagar() {
        Scanner lerTeclado = new Scanner(System.in);
        try {
            System.out.println("\n**************************** Excluir Carro ****************************");
            System.out.print("Informe o código do carro: ");

            envia.writeInt(Integer.parseInt(lerTeclado.nextLine()));
            envia.flush();

            System.out.println("Solicitação enviada para o servidor. Aguardando confirmação...");

            boolean verifica = recebe.readBoolean();
            if (verifica == true) {
                System.out.println("\n********************* Carro apagado com sucesso! **********************");
            } else {
                System.out.println("\nxxxxxxxxxxxxxxxxxxxxxxx ERRO! Carro não apagado xxxxxxxxxxxxxxxxxxxxxxx");
            }
        } catch (IOException ex) {
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
        /// try (Socket servidor = new Socket(ip, porta)) {
        try {
            Socket servidor = new Socket(ip, porta);
            recebe = new ObjectInputStream(servidor.getInputStream());
            envia = new ObjectOutputStream(servidor.getOutputStream());
        } catch (IOException ex) {
            Logger.getLogger(ClienteTcp.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void pegaIp() {
        Scanner lerTeclado = new Scanner(System.in);
        boolean testeIP;
        do {
            System.out.print("--> ");
            ip = lerTeclado.nextLine();
            testeIP = validaIP(ip);
            if (testeIP == false) {
                System.out.println("\nxxxxxxxxxxxxxxxxxxxxxxxxxxx IP inválido!!! xxxxxxxxxxxxxxxxxxxxxxxxxxxx");
                System.out.println("Por favor, digite novamente o endereço do servidor");
                System.out.println("Ele deverá seguir esse formato: 000.000.000.000");
            }
        } while (testeIP == false);
    }
//exibirMenu(): quando chamado printa o menu
    public static void recebeMenu() {
        try {
            menu = (String) recebe.readObject();
        } catch (IOException ex) {
            Logger.getLogger(ClienteTcp.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ClienteTcp.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    ///operação vai ser enviada junto com o objeto
    public static void testaEnviaOperacao() {
        Scanner lerTeclado = new Scanner(System.in);
        boolean testeOperacao;
        try {
            do {
                System.out.print("--> ");
                operacao = lerTeclado.nextInt();
                envia.writeInt(operacao);
                envia.flush();

                //vai enviar a operacao para o servidor e aguardar resposta se é válida ou não
                testeOperacao = recebe.readBoolean();
                if (testeOperacao == false) {
                    System.out.println("\nxxxxxxxxxxxxxxxxxxxxxxxxx Operação inválida!! xxxxxxxxxxxxxxxxxxxxxxxxx");
                    System.out.println("Por favor, digite novamente..");

                } //else {
                //System.out.println("\nProcessando...");
                // }
            } while (testeOperacao == false);
        } catch (IOException ex) {
            Logger.getLogger(ClienteTcp.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Verifica se um endereço IP é válido
     *
     * @param ipAddress String contendo o endereço IP a ser testado
     * @return TRUE caso o IP seja válido e FALSE caso seja inválido
     */
//Nao precisa validar, só tenta conectar direto
    public static boolean validaIP(String ipAddress) {
        boolean retorno = true;
        //Minimo 1.1.1.1 Máximo 255.255.255.255
        //if (ipAddress.length() < 7 || ipAddress.length() > 15) {
        //    retorno = false;
        //}
        //Quebrando a string em array pelo símbolo . deve ser gerado um array 
        //com 4 itens
        String[] itens = ipAddress.split(".");
        if (itens.length > 4) {
            retorno = false;
        }
        try {
            for (String item : itens) {
                if (Integer.parseInt(item) < 1 || Integer.parseInt(item) > 255) {
                    retorno = false;
                }
            }
            //Exception capsulada pois o objetivo do método é só testar o endereço    
        } catch (NumberFormatException ex) {
            retorno = false;
        }
        return retorno;
    }

}