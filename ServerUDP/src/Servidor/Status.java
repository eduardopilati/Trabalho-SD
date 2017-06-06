package servidor;

public class Status {
    Integer tempoSemResposta;
    Boolean online;
    
    public Status(){
        online = false;
        tempoSemResposta = 0;
    }
}
