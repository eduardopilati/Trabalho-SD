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
    ObjectInputStream entrada;
    ObjectOutputStream saida;
    
    public Conexao(Socket cliente){
        this.cliente = cliente;
    }
    
    @Override
    public void run(){
        try{
            try{
                entrada = new ObjectInputStream(cliente.getInputStream());
                saida = new ObjectOutputStream(cliente.getOutputStream()); 
                processa();
            } finally {
                entrada.close();
                saida.close();
            }
        }catch(Exception e){
            System.err.println("Erro na comunicação com o cliente");
            System.err.println(e);
        }
    }

    private void processa() throws Exception {
        
        while(true){           
            
            switch (entrada.readInt()){
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
                    consultarPosicoes();
                    break;
                default:
                    throw new IOException("Código Inválido");
            }
        }
    }

    private void adicionar() throws IOException{
        try{
            Veiculo veiculo = (Veiculo) entrada.readObject();
            DatabaseHelper.adicionarVeiculo(veiculo);

            String msg = "O Veículo foi adicionado";
            saida.writeObject(msg);
            saida.flush();
            
        } catch (Exception e){
            String msg = "Erro ao processar veículo";
            saida.writeObject(msg);
            saida.flush();
            
            System.err.println(msg);
            System.err.println(e);
        }
        
    }

    private void listarTipo() throws IOException {
        try{
            int tipo = entrada.readInt();
            List<Veiculo> veiculosPorTipo = DatabaseHelper.veiculosPorTipo(tipo);
            saida.writeObject(veiculosPorTipo);
            saida.flush();
            
        } catch (Exception e){
            saida.writeObject(new ArrayList<>());
            saida.flush();
            
            System.err.println("Erro ao processar listaTipo");
            System.err.println(e);
        }
    }

    private void consultar() throws IOException {
        try{
            int cod = entrada.readInt();
            Veiculo veiculo = DatabaseHelper.consultarVeiculo(cod);
            saida.writeObject(veiculo);
            saida.flush();
            
        } catch (Exception e){
            saida.writeObject(new Veiculo());
            saida.flush();
            
            System.err.println("Erro ao processar veículo");
            System.err.println(e);
        }
    }

    private void alterar() throws IOException {
        try{
            Veiculo veiculo = (Veiculo) entrada.readObject();
            DatabaseHelper.alterarVeiculo(veiculo);
            
            String msg = "O veículo foi alterado";
            saida.writeObject(msg);
            saida.flush();
            
        } catch (Exception e){
            String msg = "Erro ao alterar veículo";
            saida.writeObject(msg);
            saida.flush();
            
            System.err.println(msg);
            System.err.println(e);
        }
    }

    private void excluir() throws IOException {
        try{
            int cod = entrada.readInt();
            DatabaseHelper.removerPosicoesDeVeiculo(cod);
            DatabaseHelper.removerVeiculo(cod);
            
            String msg = "O veículo foi excluído";
            saida.writeObject(msg);
            saida.flush();
            
        } catch (Exception e){
            String msg = "Erro ao excluir veículo";
            saida.writeObject(msg);
            saida.flush();
            
            System.err.println(msg);
            System.err.println(e);
        }
    }

    private void consultarPosicoes() throws IOException {
        try{
            List<Posicao> lista;
            Veiculo veiculo = (Veiculo) entrada.readObject();
            Date data = (Date) entrada.readObject();
            
            if(data == null){
                lista = DatabaseHelper.consultarPosicoes(veiculo);
            }else{
                lista = DatabaseHelper.consultarPosicoes(veiculo, data);
            }
            
            saida.writeObject(lista);
            saida.flush();
        } catch (Exception e){
            saida.writeObject(new ArrayList<>());
            saida.flush();
            
            System.err.println("Erro ao consultar posições");
            System.err.println(e);
        }
    }
}
