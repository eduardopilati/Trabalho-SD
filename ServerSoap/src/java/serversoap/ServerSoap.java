/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package serversoap;

import data.Veiculo;
import database.DatabaseException;
import database.DatabaseHelper;
import java.util.ArrayList;
import java.util.List;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;

/**
 *
 * @author Gustavo
 */
@WebService(serviceName = "ServerSoap")
public class ServerSoap {

    /**
     * Operação de Web service
     */
    @WebMethod(operationName = "adiciona")
    public Veiculo adiciona(@WebParam(name = "codigo") int codigo, @WebParam(name = "placa") String placa, @WebParam(name = "tipo") int tipo, @WebParam(name = "capacidade") int capacidade, @WebParam(name = "unCapacidade") String unCapac) throws DatabaseException {
        
        Veiculo vel = new Veiculo();
        vel.setCodigo(codigo);
        vel.setPlaca(placa);
        vel.setTipo(tipo);
        vel.setCapacidade(capacidade);
        vel.setUnCapac(unCapac);        
        DatabaseHelper.adicionarVeiculo(vel);
        return vel;
    }


    /**
     * Operação de Web service
     */
    @WebMethod(operationName = "consultar")
    public Veiculo consultar(@WebParam(name = "codigo") int codigo) throws DatabaseException {
        
        Veiculo vel = new Veiculo();
        vel = DatabaseHelper.consultarVeiculo(codigo);
        return vel;
    }


    /**
     * Web service operation
     */
    @WebMethod(operationName = "adiciona2")
    public Boolean adiciona2(@WebParam(name = "codigo") int codigo, @WebParam(name = "placa") String placa, @WebParam(name = "tipo") int tipo, @WebParam(name = "capacidade") int capacidade, @WebParam(name = "unCapac") String unCapac) throws DatabaseException {
        Veiculo vel = new Veiculo();
        vel.setCodigo(codigo);
        vel.setPlaca(placa);
        vel.setTipo(tipo);
        vel.setCapacidade(capacidade);
        vel.setUnCapac(unCapac);        
        DatabaseHelper.adicionarVeiculo(vel);
        return true;
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "alterar")
    public Veiculo alterar(@WebParam(name = "codigo") int codigo, @WebParam(name = "placa") String placa, @WebParam(name = "tipo") int tipo, @WebParam(name = "capacidade") int capacidade, @WebParam(name = "unCapacidade") String unCapacidade) throws DatabaseException {
        Veiculo vel = new Veiculo();
        vel.setCodigo(codigo);
        vel.setPlaca(placa);
        vel.setTipo(tipo);
        vel.setCapacidade(capacidade);
        vel.setUnCapac(unCapacidade);        
        DatabaseHelper.alterarVeiculo(vel);
        return vel;//TODO write your implementation code here:

    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "excluir")
    public Boolean excluir(@WebParam(name = "parameter") int parameter) throws DatabaseException {
        DatabaseHelper.removerVeiculo(parameter);
        //TODO write your implementation code here:
        return null;
    }


    


    /**
     * Web service operation
     */
    @WebMethod(operationName = "listar")
    public List listar(@WebParam(name = "tipo") int tipo) throws DatabaseException {

        ArrayList<Veiculo> veiculos = new ArrayList<>();
        veiculos = (ArrayList<Veiculo>) DatabaseHelper.veiculosPorTipo(tipo);
        //TODO write your implementation code here:
        return veiculos;
    }


}
