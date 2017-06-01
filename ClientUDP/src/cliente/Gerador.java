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
import java.util.logging.Level;
import java.util.logging.Logger;
import util.Utilidades;

public class Gerador extends Thread {

    private int codigoVeiculo;
    private int qtdMensagens;
    private Float latitudeInicial;
    private Float longitudeInicial;
    private Date dataInicial;

    private Random random;
    InetAddress servidor;
    ServidorUdp servidorUdp;

    public Gerador() {
        super();
        random = new Random();
        servidorUdp = new ServidorUdp();
    }

    public Gerador(int codigoVeiculo, int qtdMensagens) {
        this();
        setCodigoVeiculo(codigoVeiculo);
        setQtdMensagens(qtdMensagens);
    }

    @Override
    public void run() {
        try {
            servidor = InetAddress.getByName(servidorUdp.getHost());
        } catch (UnknownHostException e) {
            System.err.println("Erro na classe Cliente, Cod: " + getCodigoVeiculo());
            System.err.println(e);
            return;
        }
        
        enviarMensagem(montarPosicao());

        if (qtdMensagens > 0) {
            for (int i = 1; i < qtdMensagens; i++) {
                enviarMensagem(montarPosicaoAleatorio());
            }
        } else {
            while (true) {
                enviarMensagem(montarPosicaoAleatorio());
            }
        }
    }

    private void enviarMensagem(Posicao posicao) {
        try (DatagramSocket soc = new DatagramSocket()) {

            byte mensagem[] = Utilidades.serializar(posicao);
            System.out.printf("Enviando localização para o servidor, Cod: %d\n", getCodigoVeiculo());
            soc.send(new DatagramPacket(mensagem, mensagem.length, servidor, servidorUdp.getPorta()));
            
            Thread.sleep(servidorUdp.getDelayEntreMensagens());

        } catch (Exception e) {
            System.err.println("Erro ao enviar localização na classe cliente, Cod:" + getCodigoVeiculo());
            System.err.println(e);
        }
    }

    private Posicao montarPosicao() {
        Posicao posicao = new Posicao();
        posicao.setCodigo(getCodigoVeiculo());

        if (getDataInicial() != null) {
            posicao.setData(getDataInicial());
        } else {
            posicao.setData(new Date());
        }

        if (getLatitudeInicial() != null) {
            posicao.setLatitude(getLatitudeInicial());
        } else {
            posicao.setLatitude(gerarLatitude());
        }

        if (getLongitudeInicial() != null) {
            posicao.setLongitude(getLongitudeInicial());
        } else {
            posicao.setLongitude(gerarLongitude());
        }

        return posicao;
    }

    private Posicao montarPosicaoAleatorio() {
        Posicao posicao = new Posicao();
        posicao.setCodigo(getCodigoVeiculo());
        posicao.setData(new Date());
        posicao.setLatitude(gerarLatitude());
        posicao.setLongitude(gerarLongitude());
        return posicao;
    }

    public float gerarLatitude() {
        return (random.nextFloat() * 180) - 90;
    }

    public float gerarLongitude() {
        return (random.nextFloat() * 360) - 180;
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

    public Random getRandom() {
        return random;
    }

    public void setRandom(Random random) {
        this.random = random;
    }

    public Date getDataInicial() {
        return dataInicial;
    }

    public void setDataInicial(Date dataInicial) {
        this.dataInicial = dataInicial;
    }

    public Float getLatitudeInicial() {
        return latitudeInicial;
    }

    public void setLatitudeInicial(Float latitudeInicial) {
        this.latitudeInicial = latitudeInicial;
    }

    public Float getLongitudeInicial() {
        return longitudeInicial;
    }

    public void setLongitudeInicial(Float longitudeInicial) {
        this.longitudeInicial = longitudeInicial;
    }
}
