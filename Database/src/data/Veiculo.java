package data;

import java.io.Serializable;

public class Veiculo implements Serializable {
    private Integer codigo;
    private String placa;
    private Integer tipo;
    private Integer capacidade;
    private String unCapac;
    
    public Veiculo(){
        
    }
    
    public Veiculo(int c, String p, int t, int cap, String un) {
        this.codigo = c;
        this.placa = p;
        this.tipo = t;
        this.capacidade = cap;
        this.unCapac = un;
    }
    
    public Integer getCodigo() {
        return codigo;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public Integer getTipo() {
        return tipo;
    }

    public void setTipo(Integer tipo) {
        this.tipo = tipo;
    }

    public Integer getCapacidade() {
        return capacidade;
    }

    public void setCapacidade(Integer capacidade) {
        this.capacidade = capacidade;
    }

    public String getUnCapac() {
        return unCapac;
    }

    public void setUnCapac(String unCapac) {
        this.unCapac = unCapac;
    }  
}
