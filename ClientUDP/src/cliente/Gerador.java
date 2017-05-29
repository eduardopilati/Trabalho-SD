package cliente;

import config.ServidorUdp;
import data.Posicao;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Date;
import java.util.Random;
import util.Utilidades;

public class Gerador extends Thread{
    private int codigoVeiculo;
    private int qtdMensagens;
    
    public Gerador(){
        super();
    }
    
    public Gerador(int codigoVeiculo, int qtdMensagens){
        this();
        setCodigoVeiculo(codigoVeiculo);
        setQtdMensagens(qtdMensagens);
    }
    
    @Override
    public void run(){
        InetAddress servidor;
        Posicao posicao;
        Random random = new Random();
        ServidorUdp servidorUdp = new ServidorUdp();
        try{
            servidor = InetAddress.getByName(servidorUdp.getHost());
            for(int i=0; i<qtdMensagens; i++){
                posicao = new Posicao();
                posicao.setCodigo(getCodigoVeiculo());
                posicao.setData(new Date());
                posicao.setLatitude(random.nextFloat());
                posicao.setLongitude(random.nextFloat());

                try (DatagramSocket soc = new DatagramSocket()){
                    
                    byte mensagem[] = Utilidades.serializar(posicao);
                    System.out.printf("Enviando localização para o servidor, Cod: %d\n", getCodigoVeiculo());
                    soc.send(new DatagramPacket(mensagem, mensagem.length, servidor, servidorUdp.getPorta()));

                } catch (IOException e) {
                    System.err.println("Erro ao enviar localização na classe cliente, Cod:" + getCodigoVeiculo());
                    System.err.println(e);
                }

                Thread.sleep(Config.delayEntreMensagens);
            }
        } catch (UnknownHostException | InterruptedException e){
            System.err.println("Erro na classe Cliente, Cod: " + getCodigoVeiculo());
            System.err.println(e);
        }
    }

    public int getCodigoVeiculo() {
        return codigoVeiculo;
    }

    public void setCodigoVeiculo(int codigoVeiculo) {
        this.codigoVeiculo = codigoVeiculo;
    }

    public int getQtdMensagens() {
        return qtdMensagens;
    }

    public void setQtdMensagens(int qtdMensagens) {
        this.qtdMensagens = qtdMensagens;
    }
}
