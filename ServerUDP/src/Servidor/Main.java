package servidor;

public class Main {

    public static void main(String[] args) throws Exception {
        Buffer fila = new Buffer();
        new Receptor(fila).start();
        new Processador(fila).start();
    }
}
