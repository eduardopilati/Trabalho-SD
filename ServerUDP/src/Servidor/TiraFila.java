package Servidor;

import data.Posicao;
import database.DatabaseException;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.util.Date;

/**
 *
 * @author LeonardoBrum
 */
public class TiraFila extends Thread {

    private final Buffering queue;
    private final DatagramSocket socketserver;

    public TiraFila(Buffering q, DatagramSocket soc) {
        queue = q;
        socketserver = soc;
    }

    @Override
    public void run() {
        String dadosvemcliente;
        DatagramPacket pacotes;
        byte[] msg;
        int acao;
        String retornadadoscliente = null;

        while (true) {
            try {

                pacotes = queue.retira();
//                System.out.println("Pacote retirado da fila " + ServidorUDP.getDataHora());

                dadosvemcliente = new String(pacotes.getData());
                dadosvemcliente = dadosvemcliente.replaceAll("\0", "");
                String[] parts = dadosvemcliente.split(";");

                //pega o codigo da acao vindo do cliente
                acao = Integer.parseInt(parts[0]);

                //pega dados do carro enviados do cliente
                Posicao posicao = new Posicao();

                if (!"null".equals(parts[1])) {
                    posicao.setCodigo(Integer.parseInt(parts[1]));
                }
                if (!"null".equals(parts[2])) {
                    posicao.setSeq(Integer.parseInt(parts[2]));
                }
                if (!"null".equals(parts[3])) {
                    posicao.setData(new Date());
//                    posicao.setData(parts[3]);
                }
                if (!"null".equals(parts[4])) {
                    posicao.setLatitude(Float.parseFloat(parts[4]));
                }
                if (!"null".equals(parts[5])) {
                    posicao.setLongitude(Float.parseFloat(parts[5]));
                }
                System.out.println(posicao.toString());
                //Faz a ação comforme o que cliente solicitou
                switch (acao) {
                    case 1:
                        try {
                            //Chama o metodo do banco
                            database.DatabaseHelper.adicionarPosicao(posicao);
                            retornadadoscliente = "Posicao inserida com sucesso!";
                        } catch (DatabaseException ex) {
                            //
                        }
                        break;
                }

                msg = retornadadoscliente.getBytes();
                pacotes = new DatagramPacket(msg, msg.length, pacotes.getAddress(), pacotes.getPort());
                try {
                    socketserver.send(pacotes);
                } catch (IOException ex) {
                    //
                }
            } catch (InterruptedException ex) {
                //
            }
        }
    }
}
