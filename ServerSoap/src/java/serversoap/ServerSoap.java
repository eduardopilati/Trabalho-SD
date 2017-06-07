/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package serversoap;

import data.Veiculo;
import database.DatabaseException;
import database.DatabaseHelper;
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
    @WebMethod(operationName = "listarTodos")
    public Veiculo listarTodos(@WebParam(name = "tipo") int tipo) {
        
        Veiculo vel = new Veiculo();
        vel.setTipo(tipo);
        vel.getTipo();
        
        
        return vel;
    }

    /**
     * Operação de Web service
     */
    @WebMethod(operationName = "consultar")
    public Veiculo consultar(@WebParam(name = "codigo") int codigo) {
        
        Veiculo vel = new Veiculo();
        vel.setCodigo(codigo);
        vel.getCodigo();
        return vel;
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "alterar")
    public Boolean alterar(@WebParam(name = "codigo") int codigo, @WebParam(name = "placa") String placa, @WebParam(name = "tipo") int tipo, @WebParam(name = "capacidade") int capacidade, @WebParam(name = "unCapac") String unCapac) {
        //TODO write your implementation code here:
        return null;
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



}
