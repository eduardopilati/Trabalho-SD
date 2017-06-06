package cliente;

import data.Posicao;
import data.Veiculo;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Scanner;

public class Cliente {
    Socket socket;
    Scanner scanner;
    ObjectOutputStream saida;
    ObjectInputStream entrada;
    
    Cliente(){
        
    }
    
    Cliente(Socket socket){
        this.socket = socket;
    }

    public void main() {
        try{
            try{
                scanner = new Scanner(System.in);
                saida = new ObjectOutputStream(socket.getOutputStream());
                entrada = new ObjectInputStream(socket.getInputStream());
                while (true){

                    exibirMenu();
                    switch (scanner.nextInt()) {
                        case 0:
                            return;
                        case 1:
                            adicionar();
                            break;
                        case 2:
                            listarTipo();
                            break;
                        case 3:
                            consultar();
                            break;
                        case 4:
                            alterar();
                            break;
                        case 5:
                            excluir();
                            break;
                        case 6:
                            consultarLocalizacoes();
                            break;
                        default:
                            System.out.println("Opção inválida");
                    }
                }
            } finally {
                entrada.close();
                saida.close();
                scanner.close();
            }
        } catch (Exception e) {
            System.err.println("Erro na comunicação com o servidor");
            System.err.println(e);
        }
    }

    private void adicionar() throws Exception {
        Veiculo veiculo = new Veiculo();

        System.out.print("Digite a placa do veículo: ");
        veiculo.setPlaca(scanner.next());

        System.out.print("Digite o tipo do veículo: ");
        veiculo.setTipo(scanner.nextInt());

        System.out.print("Digite a capacidade do veículo: ");
        veiculo.setCapacidade(scanner.nextInt());

        System.out.print("Digite a unidade de capacidade do veículo: ");
        veiculo.setUnCapac(scanner.next());

        saida.writeInt(1);
        saida.writeObject(veiculo);
        saida.flush();
        
        System.out.println(entrada.readObject());
    }

    private void listarTipo() throws Exception {
        System.out.println("Digite o tipo dos veículos:");

        saida.writeInt(2);
        saida.writeInt(scanner.nextInt());
        saida.flush();

        List<Veiculo> veiculos = (List<Veiculo>) entrada.readObject();
        if(veiculos.isEmpty()){
            System.out.println("Nenhum veículo foi retornado");
            return;
        }

        System.out.println("| Código |   Placa | Capacidade | Un. Capac. |");
        System.out.println("|--------|---------|------------|------------|");
        veiculos.forEach(v -> {
            System.out.printf("| %6d | %7s | %10d | %10s |\n",
                    v.getCodigo(), v.getPlaca(), v.getCapacidade(), v.getUnCapac());
        });
    }

    private void consultar() throws Exception {
        System.out.print("Informe o código do veículo: ");

        saida.writeInt(3);
        saida.writeInt(scanner.nextInt());
        saida.flush();
        

        Veiculo veiculo = (Veiculo) entrada.readObject();
        if(veiculo == null){
            System.out.println("Veículo não encontrado");
            return;
        }

        System.out.println("Código:" + veiculo.getCodigo());
        System.out.println("Placa:" + veiculo.getPlaca());
        System.out.println("Tipo:" + veiculo.getTipo());
        System.out.println("Capacidade:" + veiculo.getCapacidade());
        System.out.println("Un. Capac.:" + veiculo.getUnCapac());
    }

    private void alterar() throws Exception {
        Veiculo veiculo = new Veiculo();

        System.out.print("Digite o código do veículo: ");
        veiculo.setCodigo(scanner.nextInt());

        System.out.print("Digite a placa do veículo: ");
        veiculo.setPlaca(scanner.next());

        System.out.print("Digite o tipo do veículo: ");
        veiculo.setTipo(scanner.nextInt());

        System.out.print("Digite a capacidade do veículo: ");
        veiculo.setCapacidade(scanner.nextInt());

        System.out.print("Digite a unidade de capacidade do veículo: ");
        veiculo.setUnCapac(scanner.next());

        saida.writeInt(4);
        saida.writeObject(veiculo);
        saida.flush();

        System.out.println(entrada.readObject());
    }

    private void excluir() throws Exception {
        System.out.print("Informe o código do veículo: ");

        saida.writeInt(5);
        saida.writeInt(scanner.nextInt());
        saida.flush();

        System.out.println(entrada.readObject());
    }
    
    private void consultarLocalizacoes() throws Exception {
        Veiculo veiculo = new Veiculo();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        
        saida.writeInt(6);

        System.out.print("Informe o código do veiculo: ");
        veiculo.setCodigo(scanner.nextInt());
        saida.writeObject(veiculo);
        
        System.out.print("Deseja informar uma data inicial? (s/n) ");
        String d = scanner.next();
        
        if("s".equals(d)){
            if(scanner.hasNextLine()){
                scanner.nextLine();
            }
            
            System.out.print("Digite a data inicial no formato dd/MM/yyyy HH:mm:ss: ");
            String data = scanner.nextLine().trim();
            saida.writeObject(sdf.parse(data));
        }else{
            saida.writeObject(null);
        }
        saida.flush();

        List<Posicao> posicoes = (List<Posicao>) entrada.readObject();
        if(posicoes.isEmpty()){
            System.out.println("Nenhuma posição foi retornada");
            return;
        }

        System.out.println("| Sequência | Latitude    | Longitude    | Data                 |");
        System.out.println("|-----------|-------------|--------------|----------------------|");
        posicoes.forEach(p -> {
            System.out.printf("| %9d | %11.7f | %12.7f | %20s |\n",
                    p.getSeq(), p.getLatitude(), p.getLongitude(), sdf.format(p.getData()));
        });
    }

    private void exibirMenu() {
        System.out.println();
        System.out.println("=========================");
        System.out.println("|  1 - ADICIONAR        |");
        System.out.println("|  2 - LISTAR POR TIPO  |");
        System.out.println("|  3 - CONSULTAR        |");
        System.out.println("|  4 - ALTERAR          |");
        System.out.println("|  5 - EXCLUIR          |");
        System.out.println("|  6 - LISTAR POSIÇÕES  |");
        System.out.println("|  0 - SAIR             |");
        System.out.println("=========================");
        System.out.println();
        System.out.print  ("Digite a opção: ");
    }
}