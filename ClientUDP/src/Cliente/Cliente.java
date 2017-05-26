package Cliente;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Random;

/**
 *
 * @author LeonardoBrum
 */
public class Cliente {

    public static void main(String[] args) throws Exception {
        byte[] bMsgEnvia = new byte[4000];
        byte[] bMsgRecebe = new byte[4000];
        String sEnviarDados;
        String sResposta;
        int iPorta = 2010;
        //Ip do servidor / 
        String sIp = "localhost";
//        String sIp = "192.168.0.91";
        InetAddress servidor = null;
        servidor = InetAddress.getByName(sIp);
        //Pega a data e hora atual (Calendar)
        GregorianCalendar calendar = new GregorianCalendar();
        //Quantidade de vezes para criar numero randon (gerador de numero aleatorio)
        for (int i = 1; i <= 5; i++) {

            int seq = i;
            System.out.println("seq: " + seq);

            int codigo = i;
            System.out.println("codigo: " + codigo);

            Date data = calendar.getTime();
            System.out.println(data);

            Random radom = new Random();
            Float num1, num2;

            num1 = radom.nextFloat() * 90;
            num2 = radom.nextFloat() * 90;

            Float latitude = num1;
            System.out.println("latitude: " + latitude);
            Float longitude = num2;
            System.out.println("longitude: " + longitude);

            Integer acao = 1;

            DatagramSocket soc;
            DatagramPacket pct;

            sEnviarDados = acao + ";"
                    + codigo + ";"
                    + seq + ";"
                    + data + ";"
                    + latitude + ";"
                    + longitude;
            bMsgEnvia = sEnviarDados.getBytes();

            try {
                soc = new DatagramSocket();
                pct = new DatagramPacket(bMsgEnvia, bMsgEnvia.length, servidor, iPorta);
                soc.send(pct);
                System.out.println("Enviou conteudo para o servidor");

                //Resposta vinda do servidor                
                pct = new DatagramPacket(bMsgRecebe, bMsgRecebe.length);
                soc.receive(pct);
                sResposta = new String(pct.getData());
                System.out.println("Cliente recebeu do servidor: " + sResposta);

                soc.close();
            } catch (SocketException ex) {
                //
            }

            System.out.println("");
            Thread.sleep(2000);

        }
    }
}
