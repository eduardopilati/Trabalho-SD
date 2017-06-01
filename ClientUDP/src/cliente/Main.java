package cliente;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;
import util.Utilidades;

public class Main {
    static SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
    static Scanner sc = new Scanner(System.in);
    
    public static void main(String...args){
        menu();
        if(args.length > 1){
            novaThread(lerArgumentos(args));
        }
        while(true){
            novaThread(sc.nextLine());
        }
    }
    
    public static void novaThread(String params){
        Gerador gerador = null;
        try{
            gerador = montarGerador(params);
        }catch(Exception e){
                System.err.println("Erro ao ler as informações");
                System.err.println(e);
        }
        
        try{
            if(gerador != null){
                gerador.start();
            }
        }catch(Exception e){
            System.err.println("Erro ao iniciar gerador");
            System.err.println(e);
        }
    }
    
    public static Gerador montarGerador(String str) throws Exception{
        Gerador gerador = new Gerador();
        
        try (Scanner tempSc = new Scanner(str)){
            gerador.setCodigoVeiculo(tempSc.nextInt());
            gerador.setQtdMensagens(tempSc.nextInt());
            
            if(tempSc.hasNextFloat()){
                float lat = tempSc.nextFloat();
                if(!Utilidades.validarLatitude(lat)){
                    throw new Exception("Latitude inválida");
                }
                gerador.setLatitudeInicial(lat);
            }
            
            if(tempSc.hasNextFloat()){
                float lon = tempSc.nextFloat();
                if(!Utilidades.validarLongitude(lon)){
                    throw new Exception("Longitude inválida");
                }
                gerador.setLongitudeInicial(lon);
            }
            
            if(tempSc.hasNextLine()){
                String dt = tempSc.nextLine().trim();
                Date data = sdf.parse(dt);
                
                if(!dt.equals(sdf.format(data))){
                    throw new Exception("Data inválida");
                }
                gerador.setDataInicial(data);
            }
        }
        return gerador;
    }
    
    public static void menu(){
        System.out.printf("Para iniciar um novo envio digite as informações abaixo:\n"
                + " - *Código do veículo\n"
                + " - *Quantidade de localizações enviadas (0 para infinito)\n"
                + " - Latitude inicial\n"
                + " - Longitude inicial\n"
                + " - Data inicial no formato dd/MM/yyyy HH:mm:ss\n"
                + "Exemplos: '1 10\n"
                + "           1 5 -54.289 26.874\n"
                + "           1 20 01/01/2017 16:38:42\n"
                + "Cada inserção gera uma nova Thread de envio\n"
                + "Todas as informações devem ser digitadas em uma única linha\n\n");
    }
    
    public static String lerArgumentos(String...args){
        StringBuilder str = new StringBuilder();
        for(int i=0; i<args.length; i++){
            str.append(' ').append(args[i]);
        }
        return str.toString();
    } 
}
