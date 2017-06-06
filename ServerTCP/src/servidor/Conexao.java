package servidor;

import data.Posicao;
import data.Veiculo;
import database.DatabaseHelper;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Conexao extends Thread{
    Socket cliente;
    
    public Conexao(Socket cliente){
        this.cliente = cliente;
    }
    
    @Override
    public void run(){
        try (ObjectInputStream ois = new ObjectInputStream(cliente.getInputStream())) {
            try (ObjectOutputStream oos = new ObjectOutputStream(cliente.getOutputStream())) {
                processa(ois, oos);
            }
        } catch (Exception e) {
            System.err.println("Erro na comunicação");
            System.err.println(e);
        }
    }

    private void processa(ObjectInputStream ois, ObjectOutputStream oos) throws Exception {
        while(true){
            int codigo = ois.readInt();
            switch (codigo){
                case 1:
                    adicionar(ois, oos);
                    break;
                case 2:
                    listarTipo(ois, oos);
                    break;
                case 3:
                    consultar(ois, oos);
                    break;
                case 4:
                    alterar(ois, oos);
                    break;
                case 5:
                    excluir(ois, oos);
                    break;
                case 6:
                    consultarPosicoes(ois, oos);
                default:
                    throw new IOException("Código Inválido");
            }
        }
    }

    private void adicionar(ObjectInputStream ois, ObjectOutputStream oos) throws IOException{
        try{
           
            Veiculo veiculo = (Veiculo) ois.readObject();
            DatabaseHelper.adicionarVeiculo(veiculo);
            
            String msg = "O Veículo foi adicionado";
            oos.writeObject(msg);
            
        } catch (Exception e){
            String msg = "Erro ao processar veículo";
            oos.writeObject(msg);
            
            System.err.println(msg);
            System.err.println(e);
        }
        
    }

    private void listarTipo(ObjectInputStream ois, ObjectOutputStream oos) throws IOException {
        try{
           
            int tipo = ois.readInt();
            List<Veiculo> veiculosPorTipo = DatabaseHelper.veiculosPorTipo(tipo);
            oos.writeObject(veiculosPorTipo);
            
        } catch (Exception e){
            oos.writeObject(new ArrayList<Veiculo>());
            
            System.err.println("Erro ao processar listaTipo");
            System.err.println(e);
        }
    }

    private void consultar(ObjectInputStream ois, ObjectOutputStream oos) throws IOException {
        try{
           
            int cod = ois.readInt();
            Veiculo veiculo = DatabaseHelper.consultarVeiculo(cod);
            oos.writeObject(veiculo);
            
        } catch (Exception e){
            oos.writeObject(new Veiculo());
            
            System.err.println("Erro ao processar veículo");
            System.err.println(e);
        }
    }

    private void alterar(ObjectInputStream ois, ObjectOutputStream oos) throws IOException {
        try{
           
            Veiculo veiculo = (Veiculo) ois.readObject();
            DatabaseHelper.alterarVeiculo(veiculo);
            
            String msg = "O veículo foi alterado";
            oos.writeObject(msg);
            
        } catch (Exception e){
            String msg = "Erro ao alterar veículo";
            oos.writeObject(msg);
            
            System.err.println(msg);
            System.err.println(e);
        }
    }

    private void excluir(ObjectInputStream ois, ObjectOutputStream oos) throws IOException {
        try{
           
            int cod = ois.readInt();
            DatabaseHelper.removerVeiculo(cod);
            
            String msg = "O veículo foi excluído";
            oos.writeObject(msg);
            
        } catch (Exception e){
            String msg = "Erro ao excluir veículo";
            oos.writeObject(msg);
            
            System.err.println(msg);
            System.err.println(e);
        }
    }

    private void consultarPosicoes(ObjectInputStream ois, ObjectOutputStream oos) throws IOException {
        try{
            List<Posicao> lista;
            Veiculo veiculo = (Veiculo) ois.readObject();
            Date data = (Date) ois.readObject();
            
            if(data == null){
                lista = DatabaseHelper.consultarPosicoes(veiculo);
            }else{
                lista = DatabaseHelper.consultarPosicoes(veiculo, data);
            }
            
            oos.writeObject(lista);
            
        } catch (Exception e){
            oos.writeObject(new ArrayList<Posicao>());
            
            System.err.println("Erro ao consultar posições");
            System.err.println(e);
        }
    }
}
