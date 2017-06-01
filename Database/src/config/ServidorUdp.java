package config;

public class ServidorUdp {
    private String host = "localhost";
    private int porta = 2010;
    private int delayEntreMensagens = 1000;

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public int getPorta() {
        return porta;
    }

    public void setPorta(int porta) {
        this.porta = porta;
    }

    public int getDelayEntreMensagens() {
        return delayEntreMensagens;
    }

    public void setDelayEntreMensagens(int delayEntreMensagens) {
        this.delayEntreMensagens = delayEntreMensagens;
    }
    
}
